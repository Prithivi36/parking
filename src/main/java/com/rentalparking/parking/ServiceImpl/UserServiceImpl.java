package com.rentalparking.parking.ServiceImpl;

import com.rentalparking.parking.Entity.User;
import com.rentalparking.parking.Exception.ApiException;
import com.rentalparking.parking.Repository.UserRepository;
import com.rentalparking.parking.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    @Override
    public User getUser(String id) {
        return userRepository.findById(id).orElseThrow(()->new ApiException(HttpStatus.NOT_FOUND,"Not Found"));
    }

    @Override
    public String saveUser(User user) {
        return userRepository.save(user).getName()+"Saved Successfully";
    }

    @Override
    public String getByUserMail(String email) {
        return userRepository.findByEmail(email).get_id();
    }
}
