package com.rentalparking.paraking.Repository;

import com.rentalparking.paraking.Entity.Parking;
import com.rentalparking.paraking.Entity.Storage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StorageRepository extends MongoRepository<Storage,String> {
    Optional<List<Storage>> findByUserId(String id);
}
