package com.rentalparking.paraking.Service;

import com.rentalparking.paraking.Entity.Booking;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingService {
    String createBooking(Booking booking);
    Booking viewBooking(String id);
    String cancelBooking(String id);
    List<Booking> listUserBooking(String id);
    List<Booking> placeBooking(String id);
    String accept(String id);
    String reject(String id);
    List<Booking> getByOwner(String id);
}
