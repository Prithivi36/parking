package com.rentalparking.parking.ServiceImpl;

import com.rentalparking.parking.Entity.Notification;
import com.rentalparking.parking.Entity.User;
import com.rentalparking.parking.Exception.ApiException;
import com.rentalparking.parking.Repository.UserRepository;
import com.rentalparking.parking.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        user.setInbox(new ArrayList<>());
        return userRepository.save(user).getName()+"Saved Successfully";
    }

    @Override
    public String getByUserMail(String email) {
        return userRepository.findByEmail(email).get_id();
    }

    @Override
    public String viewMessage(String id) {
        User usr =userRepository.findById(id).orElseThrow(()->new ApiException(HttpStatus.NOT_FOUND,"UserNotFound"));
        List<Notification> notifies=usr.getInbox().stream().map((n)->{n.setViewed(true);return n;}).toList();
        usr.setInbox(notifies);
        userRepository.save(usr);
        return "Viewed";
    }
}
