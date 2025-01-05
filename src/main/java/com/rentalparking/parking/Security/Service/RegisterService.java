package com.rentalparking.parking.Security.Service;

import com.rentalparking.parking.Entity.User;
import com.rentalparking.parking.Security.Models.LoginRepo;
import com.rentalparking.parking.Security.Models.UserSecuritiy;
import com.rentalparking.parking.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class RegisterService {
    private LoginRepo userRepository;
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    public String registerNewUser(UserSecuritiy userEntity){
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userRepository.save(userEntity);
        User user = new User();
        user.setName(userEntity.getName());
        user.setPhone(userEntity.getPhone());
        user.setEmail(userEntity.getEmail());
        userService.saveUser(user);
        return "Success";
    }
}
