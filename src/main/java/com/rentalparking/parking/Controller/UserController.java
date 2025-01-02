package com.rentalparking.parking.Controller;

import com.rentalparking.parking.Entity.User;
import com.rentalparking.parking.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    UserService userService;

    @GetMapping("/{id}")
    public User getUser(@PathVariable String id){
        return userService.getUser(id);
    }

    @PostMapping("/")
    public String saveUse(@RequestBody User user){
        return userService.saveUser(user);
    }

    @GetMapping("/mail/{email}")
    public String findByEmail(@PathVariable String email){
        return userService.getByUserMail(email);
    }

    @PatchMapping("/{id}")
    public String viewed(@PathVariable String id){
        return userService.viewMessage(id);
    }

}
