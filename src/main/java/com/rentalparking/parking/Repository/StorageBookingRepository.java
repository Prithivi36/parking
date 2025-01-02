package com.rentalparking.parking.Repository;

import com.rentalparking.parking.Entity.StorageBooking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StorageBookingRepository extends MongoRepository<StorageBooking,String> {
    List<StorageBooking> findByUserId(String id);
    List<StorageBooking> findByOwner(String id);
}
