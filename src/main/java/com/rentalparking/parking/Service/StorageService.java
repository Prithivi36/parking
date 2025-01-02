package com.rentalparking.parking.Service;

import com.rentalparking.parking.Entity.Parking;
import com.rentalparking.parking.Entity.Storage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StorageService {
    String addStorage(Storage store);
    Storage getStorage(String id);
    String toggleAvailability(String id,boolean avail);
    String deleteParking(String id);
    List<Storage> findNearBy(double latitude, double longitude, double radius);
    List<Storage> findMine(String id);
    List<Parking> findByVehicleAndPrice(String vehicleType,double maxPrice);
}
