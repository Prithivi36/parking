package com.rentalparking.parking.Repository;

import com.rentalparking.parking.Entity.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends MongoRepository<Booking,String> {
    Optional<List<Booking>> findByUserId(String id);
    Optional<List<Booking>> findBySpaceId(String id);
    Optional<List<Booking>> findByOwner(String id);

}
