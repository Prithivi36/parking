package com.rentalparking.parking.Service;

import com.rentalparking.parking.Entity.Parking;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ParkingService {
    String addParking(Parking parking);
    Parking getParking(String id);
    String toggleAvailability(String id,boolean avail);
    String deleteParking(String id);
    List<Parking> findNearBy(double latitude, double longitude, double radius);
    List<Parking> findMine(String id);
    List<Parking> findByVehicleAndPrice(String vehicleType,double maxPrice);
}
