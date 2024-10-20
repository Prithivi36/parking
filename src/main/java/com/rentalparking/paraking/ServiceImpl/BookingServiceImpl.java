package com.rentalparking.paraking.ServiceImpl;

import com.rentalparking.paraking.Entity.Booking;
import com.rentalparking.paraking.Exception.ApiException;
import com.rentalparking.paraking.Repository.BookingRepository;
import com.rentalparking.paraking.Service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {
    BookingRepository bookingRepository;
    @Override
    public String createBooking(Booking booking) {
        return bookingRepository.save(booking).get_id() + " as booking Id placed Successfully";
    }

    @Override
    public Booking viewBooking(String id) {
        return bookingRepository.findById(id).orElseThrow(()->new ApiException(HttpStatus.NOT_FOUND,"No Booking found"));
    }

    @Override
    public String cancelBooking(String id) {
        bookingRepository.deleteById(id);
        return "Cancelled";
    }

    @Override
    public List<Booking> listUserBooking(String id) {
        return bookingRepository.findByUserId(id).orElseThrow(()->new ApiException(HttpStatus.NOT_FOUND,"No booking found"));
    }

    @Override
    public List<Booking> placeBooking(String id) {
        return bookingRepository.findBySpaceId(id).orElseThrow(()->new ApiException(HttpStatus.NOT_FOUND,"No booking found"));
    }

    @Override
    public String acceptOrReject(String id) {
        Booking booking = viewBooking(id);
        boolean sts=booking.isStatus();
        booking.setStatus(!sts);
        bookingRepository.save(booking);
        return !sts?"Accepted":"Rejected";
    }
}
