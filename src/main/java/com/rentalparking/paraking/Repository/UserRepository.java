package com.rentalparking.paraking.Repository;

import com.rentalparking.paraking.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    User findByEmail(String email);
}
