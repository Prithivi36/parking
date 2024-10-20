package com.rentalparking.paraking.Repository;

import com.rentalparking.paraking.Entity.Parking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRepository extends MongoRepository<Parking,String> {
}
