package com.rentalparking.parking.Controller;

import com.rentalparking.parking.Entity.Parking;
import com.rentalparking.parking.Service.ParkingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/park")
@CrossOrigin("*")
@AllArgsConstructor
public class ParkingController {
    ParkingService parkingService;

    @PostMapping("/")
    public String addParking(@RequestBody Parking parking){
        return parkingService.addParking(parking);
    }
    @GetMapping("/{id}")
    public Parking getParking(@PathVariable String id){
        return parkingService.getParking(id);
    }
    @DeleteMapping("/{id}")
    public String deleteParking(@PathVariable String id){
        return parkingService.deleteParking(id);
    }
    @PatchMapping("/{id}/{avail}")
    public String toggleAvail(@PathVariable String id,@PathVariable char avail){
        return parkingService.toggleAvailability(id,avail=='1');
    }
    @GetMapping("/{lt}/{ld}/{rad}")
    public List<Parking> getParkingLocation(@PathVariable double lt ,@PathVariable double ld ,@PathVariable int rad){
        return parkingService.findNearBy(lt,ld,rad);
    }
    @GetMapping("/my/{uid}")
    public List<Parking> myParking(@PathVariable String uid){
        return parkingService.findMine(uid);
    }
}
