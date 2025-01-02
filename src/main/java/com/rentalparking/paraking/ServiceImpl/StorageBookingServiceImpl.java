package com.rentalparking.paraking.ServiceImpl;

import com.rentalparking.paraking.Entity.Storage;
import com.rentalparking.paraking.Entity.StorageBooking;
import com.rentalparking.paraking.Entity.User;
import com.rentalparking.paraking.Exception.ApiException;
import com.rentalparking.paraking.Repository.StorageBookingRepository;
import com.rentalparking.paraking.Repository.StorageRepository;
import com.rentalparking.paraking.Repository.UserRepository;
import com.rentalparking.paraking.Service.StorageBookingService;
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
