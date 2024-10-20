package com.rentalparking.paraking.Controller;

import com.rentalparking.paraking.Entity.User;
import com.rentalparking.paraking.Service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
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

}
