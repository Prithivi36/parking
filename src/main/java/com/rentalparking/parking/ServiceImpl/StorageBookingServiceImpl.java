package com.rentalparking.parking.ServiceImpl;

import com.rentalparking.parking.Entity.*;
import com.rentalparking.parking.Exception.ApiException;
import com.rentalparking.parking.Repository.StorageBookingRepository;
import com.rentalparking.parking.Repository.StorageRepository;
import com.rentalparking.parking.Repository.UserRepository;
import com.rentalparking.parking.Service.StorageBookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@AllArgsConstructor
public class StorageBookingServiceImpl implements StorageBookingService {
    StorageBookingRepository sbr;
    StorageRepository sr;
    UserRepository ur;
    @Override
    public String createBooking(StorageBooking booking) {
        String storeId = booking.getSpaceId();
        booking.setStatus("processing");
        Storage storage = sr.findById(storeId).orElseThrow(
                ()-> new ApiException(HttpStatus.NOT_FOUND,"Space is invalid")
        );
        booking.setAddress(storage.getAddress());
        User usr = ur.findById(booking.getUserId()).orElseThrow(
                ()->new ApiException(HttpStatus.NOT_FOUND,"user is Invalid")
        );
        User usrOwn = ur.findById(storage.getUserId()).orElseThrow(
                ()-> new ApiException(HttpStatus.NOT_FOUND,"User Id Not found")
        );
        booking.setOwnerName(usrOwn.getName());
        booking.setUserName(usr.getName());

        Duration duration = Duration.between(booking.getStartTime(),booking.getEndTime());
        double hour=duration.toHours()*storage.getPricePerHour();
        booking.setTotalCost(hour+"");
        booking.setOwner(storage.getUserId());
        return sbr.save(booking).get_id()+"Successfully Booked";
    }

    @Override
    public StorageBooking viewBooking(String id) {
        return sbr.findById(id).orElseThrow(()->new ApiException(HttpStatus.NOT_FOUND,"id is invalid"));
    }

    @Override
    public String cancelBooking(String id) {
        StorageBooking booking = sbr.findById(id).orElseThrow(
                ()->new ApiException(HttpStatus.NOT_FOUND,"No booking found")
        );
        User user = ur.findById(booking.getOwner()).orElseThrow(
                ()->new ApiException(HttpStatus.NOT_FOUND,"User Not Found")
        );
        User userBooked = ur.findById(booking.getUserId()).orElseThrow(
                ()->new ApiException(HttpStatus.NOT_FOUND,"User Not Found")
        );
        List<Notification> msg =user.getInbox();
        Notification cancel = new Notification();
        cancel.setViewed(false);
        cancel.setMessage("Your booking from user "+booking.getUserName()+" cancelled the booking");
        msg.addFirst(cancel);
        ur.save(user);
        cancel.setMessage("Successfully cancelled booking with "+booking.getOwnerName());
        msg=userBooked.getInbox();
        msg.addFirst(cancel);
        user.setInbox(msg);
        ur.save(userBooked);
        sbr.deleteById(id);
        return "cancelled";
    }

    @Override
    public List<StorageBooking> listUserBooking(String id) {
        return sbr.findByUserId(id);
    }

    @Override
    public List<StorageBooking> placeBooking(String id) {
        return List.of();
    }

    @Override
    public String accept(String id) {
        StorageBooking booking = viewBooking(id);
        booking.setStatus("accepted");
        sbr.save(booking);
        return "Accepted";
    }

    @Override
    public String reject(String id) {
        StorageBooking booking = viewBooking(id);
        booking.setStatus("rejected");
        sbr.save(booking);
        return "Rejected";
    }

    @Override
    public List<StorageBooking> getByOwenr(String id) {
        return sbr.findByOwner(id);
    }
}
