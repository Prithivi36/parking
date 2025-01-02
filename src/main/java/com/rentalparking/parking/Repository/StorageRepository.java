package com.rentalparking.parking.Repository;

import com.rentalparking.parking.Entity.Storage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StorageRepository extends MongoRepository<Storage,String> {
    Optional<List<Storage>> findByUserId(String id);
}
