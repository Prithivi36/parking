package com.rentalparking.parking.Security.Models;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepo extends MongoRepository<UserSecuritiy,String> {
    Optional<UserSecuritiy> findByEmail(String mail);
}
