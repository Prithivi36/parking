package com.rentalparking.parking.Service;

import com.rentalparking.parking.Entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User getUser(String id);
    String saveUser(User user);
    String getByUserMail(String email);

}
