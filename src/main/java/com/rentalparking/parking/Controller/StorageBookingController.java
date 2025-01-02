package com.rentalparking.parking.Controller;

import com.rentalparking.parking.Entity.StorageBooking;
import com.rentalparking.parking.Service.StorageBookingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sbook")
@CrossOrigin("*")
@AllArgsConstructor
public class StorageBookingController {
    StorageBookingService storageBookingService;
    @PostMapping("/")
    public String bookNew(@RequestBody StorageBooking booking){
        return storageBookingService.createBooking(booking);
    }
    @GetMapping("/{id}")
    public StorageBooking viewBooking(@PathVariable String id){
        return storageBookingService.viewBooking(id);
    }
    @DeleteMapping("/{id}")
    public String cancelBooking(@PathVariable String id){
        return storageBookingService.cancelBooking(id);
    }
    @GetMapping("/all/{uid}")
    public List<StorageBooking> listUserBooking(@PathVariable String uid){
        return storageBookingService.listUserBooking(uid);
    }
    @GetMapping("all/p/{pid}")
    public List<StorageBooking> placeBooking(@PathVariable String pid){
        return storageBookingService.placeBooking(pid);
    }
    @PatchMapping("/{id}")
    public String accept(@PathVariable String id){
        return storageBookingService.accept(id);
    }
    @PatchMapping("/r/{id}")
    public String reject(@PathVariable String id){
        return storageBookingService.reject(id);
    }
    @GetMapping("/my/{s}")
    public List<StorageBooking> getByOwner(@PathVariable String s ){
        return storageBookingService.getByOwner(s);
    }
    @GetMapping("/my/p/{s}")
    public List<StorageBooking> getByOwnerPending(@PathVariable String s ){
        return storageBookingService.requestsPending(s);
    }
    @PatchMapping("/c/{id}")
    public String completed(@PathVariable String id){
        return storageBookingService.completed(id);
    }
}
