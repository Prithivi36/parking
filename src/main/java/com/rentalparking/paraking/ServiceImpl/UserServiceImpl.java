package com.rentalparking.paraking.ServiceImpl;

import com.rentalparking.paraking.Entity.User;
import com.rentalparking.paraking.Exception.ApiException;
import com.rentalparking.paraking.Repository.UserRepository;
import com.rentalparking.paraking.Service.UserService;
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
}
