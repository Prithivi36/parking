package com.rentalparking.parking.ServiceImpl;

import com.rentalparking.parking.Entity.Parking;
import com.rentalparking.parking.Entity.User;
import com.rentalparking.parking.Exception.ApiException;
import com.rentalparking.parking.Repository.ParkingRepository;
import com.rentalparking.parking.Repository.UserRepository;
import com.rentalparking.parking.Service.ParkingService;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ParkingServiceImpl implements ParkingService {
    ParkingRepository parkingRepository;
    MongoTemplate mongoTemplate;
    UserRepository userRepository;
    @Override
    public String addParking(Parking parking) {
        parking.setTotalRevenue(0.0);
        User usr = userRepository.findById(parking.getUserId()).orElseThrow(()->
                new ApiException(HttpStatus.NOT_FOUND,"User Does Not exist"));
        parking.setOwnerName(usr.getName());
        return parkingRepository.save(parking).getAddress()+" Saved Successfully";
    }

    @Override
    public Parking getParking(String id) {
        return parkingRepository.findById(id).orElseThrow(()-> new ApiException(HttpStatus.NOT_FOUND,"Parking does not exist"));
    }

    @Override
    public String toggleAvailability(String id, boolean avail) {
        Parking park =getParking(id);
        park.setAvailable(avail);
        parkingRepository.save(park);
        return "Toggled currently"+((avail)?"is available":"is now not available");
    }

    @Override
    public String deleteParking(String id) {
        parkingRepository.deleteById(id);
        return "deleted Successfully";
    }

    @Override
    public List<Parking> findNearBy(double latitude, double longitude, double radius) {
        Query query = new Query();
        query.addCriteria(
                Criteria.where("location")
                        .near(new GeoJsonPoint(latitude,longitude))
                        .maxDistance(radius)
        );
        return mongoTemplate.find(query, Parking.class);
    }

    @Override
    public List<Parking> findMine(String id) {
        return parkingRepository.findByUserId(id).orElseThrow(
                ()->new ApiException(HttpStatus.NOT_FOUND,"Not found")
        );
    }

    @Override
    public List<Parking> findByVehicleAndPrice(String vehicleType, double maxPrice) {
        Query query = new Query();
        query.addCriteria(
                Criteria.where("vehicleAllowed").in(vehicleType)
                        .and("pricePerHour").lte(maxPrice)
        );

        return mongoTemplate.find(query, Parking.class);
    }
}
