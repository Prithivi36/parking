package com.rentalparking.parking.Repository;

import com.rentalparking.parking.Entity.Parking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingRepository extends MongoRepository<Parking,String> {
    Optional<List<Parking>> findByUserId(String id);
}
