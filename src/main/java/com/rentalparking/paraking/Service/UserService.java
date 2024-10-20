package com.rentalparking.paraking.Service;

import com.rentalparking.paraking.Entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User getUser(String id);
    String saveUser(User user);
}
