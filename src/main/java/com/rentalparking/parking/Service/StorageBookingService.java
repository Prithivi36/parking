package com.rentalparking.parking.Service;

import com.rentalparking.parking.Entity.StorageBooking;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StorageBookingService {
    String createBooking(StorageBooking booking);
    StorageBooking viewBooking(String id);
    String cancelBooking(String id);
    List<StorageBooking> listUserBooking(String id);
    List<StorageBooking> placeBooking(String id);
    String accept(String id);
    String reject(String id);
    List<StorageBooking> getByOwenr(String id);
}
