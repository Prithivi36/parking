package com.rentalparking.paraking.Controller;

import com.rentalparking.paraking.Entity.Booking;
import com.rentalparking.paraking.Service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/book")
@CrossOrigin("*")
@AllArgsConstructor
public class BookingController {
    BookingService bookingService;
    @PostMapping
    public String bookNew(@RequestBody Booking booking){
        return bookingService.createBooking(booking);
    }
    @GetMapping("/{id}")
    public Booking viewBooking(@PathVariable String id){
        return bookingService.viewBooking(id);
    }
    @DeleteMapping("/{id}")
    public String cancelBooking(@PathVariable String id){
        return bookingService.cancelBooking(id);
    }
    @GetMapping("/all/{uid}")
    public List<Booking> listUserBooking(@PathVariable String uid){
        return bookingService.listUserBooking(uid);
    }
    @GetMapping("all/p/{pid}")
    public List<Booking> placeBooking(@PathVariable String pid){
        return bookingService.placeBooking(pid);
    }
    @PatchMapping("/{id}")
    public String acceptOrReject(@PathVariable String id){
        return bookingService.acceptOrReject(id);
    }
    @GetMapping("/my/{s}")
    public List<Booking> getByOwner(@PathVariable String s ){
        return bookingService.getByOwenr(s);
    }
}
