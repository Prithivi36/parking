package com.rentalparking.paraking.Repository;

import com.rentalparking.paraking.Entity.StorageBooking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StorageBookingRepository extends MongoRepository<StorageBooking,String> {
    List<StorageBooking> findByUserId(String id);
    List<StorageBooking> findByOwner(String id);
}
