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
    public void sendMessage(String message,User user){
        List<Notification> msg =user.getInbox();
        Notification info = new Notification();
        info.setViewed(false);
        info.setMessage(message);
        msg.addFirst(info);
        user.setInbox(msg);
        ur.save(user);
    }
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
        sendMessage("You have a new Booking from "+usr.getName(),usrOwn);
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
        sendMessage("Your booking from user "+booking.getUserName()+" cancelled the booking",user);
        sendMessage("Successfully cancelled booking with "+booking.getOwnerName(),userBooked);
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
        User user = ur.findById(booking.getUserId()).orElseThrow(()->new ApiException(HttpStatus.NOT_FOUND,
                "User Not Found"));
        sendMessage("Your Booking Request Has Been Accepted",user);
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
