package com.rentalparking.paraking.Repository;

import com.rentalparking.paraking.Entity.Parking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingRepository extends MongoRepository<Parking,String> {
    Optional<List<Parking>> findByUserId(String id);
}
