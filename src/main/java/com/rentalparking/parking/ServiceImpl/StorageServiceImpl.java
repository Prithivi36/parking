package com.rentalparking.parking.ServiceImpl;

import com.rentalparking.parking.Entity.Parking;
import com.rentalparking.parking.Entity.Storage;
import com.rentalparking.parking.Entity.User;
import com.rentalparking.parking.Exception.ApiException;
import com.rentalparking.parking.Repository.StorageRepository;
import com.rentalparking.parking.Repository.UserRepository;
import com.rentalparking.parking.Service.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StorageServiceImpl implements StorageService {
    StorageRepository storageRepository;
    UserRepository userRepository;
    MongoTemplate mongoTemplate;
    @Override
    public String addStorage(Storage store) {
        User usr = userRepository.findById(store.getUserId()).orElseThrow(
                ()->  new ApiException(HttpStatus.NOT_FOUND,"Not Found")
        );
        store.setOwnerName(usr.getName());
        store.setAvailable(true);
        storageRepository.save(store);
        return "Successfully saved";
    }

    @Override
    public Storage getStorage(String id) {
        return storageRepository.findById(id).orElseThrow(
                ()->new ApiException(HttpStatus.NOT_FOUND,"Not Found")
        );
    }

    @Override
    public String toggleAvailability(String id, boolean avail) {
        Storage store = getStorage(id);
        store.setAvailable(avail);
        storageRepository.save(store);
        return "Updated";
    }

    @Override
    public String deleteParking(String id) {
        storageRepository.deleteById(id);
        return "deleted";
    }

    @Override
    public List<Storage> findNearBy(double latitude, double longitude, double radius) {
        Query query = new Query();
        query.addCriteria(
                Criteria.where("location")
                        .near(new GeoJsonPoint(latitude,longitude))
                        .maxDistance(radius)
        );
        return mongoTemplate.find(query, Storage.class);
    }

    @Override
    public List<Storage> findMine(String id) {
        return storageRepository.findByUserId(id).orElseThrow(
                ()->new ApiException(HttpStatus.NOT_FOUND,"Not found")
        );
    }

    @Override
    public List<Parking> findByVehicleAndPrice(String vehicleType, double maxPrice) {
        return List.of();
    }
}
