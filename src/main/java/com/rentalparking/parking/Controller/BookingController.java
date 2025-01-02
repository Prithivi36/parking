package com.rentalparking.parking.Controller;

import com.rentalparking.parking.Entity.Booking;
import com.rentalparking.parking.Service.BookingService;
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
    public String accept(@PathVariable String id){
        return bookingService.accept(id);
    }@PatchMapping("/r/{id}")
    public String reject(@PathVariable String id){
        return bookingService.reject(id);
    }
    @GetMapping("/my/{s}")
    public List<Booking> getByOwner(@PathVariable String s ){
        return bookingService.getByOwner(s);
    }
    @GetMapping("/my/p/{s}")
    public List<Booking> getByOwnerPending(@PathVariable String s ){
        return bookingService.requestsPending(s);
    }
    @PatchMapping("/c/{id}")
    public String completed(@PathVariable String id){
        return bookingService.completed(id);
    }

}
