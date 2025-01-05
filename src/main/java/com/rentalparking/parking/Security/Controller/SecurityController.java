package com.rentalparking.parking.Security.Controller;

import com.rentalparking.parking.Security.Models.Login;
import com.rentalparking.parking.Security.Models.UserSecuritiy;
import com.rentalparking.parking.Security.Service.LoginService;
import com.rentalparking.parking.Security.Service.RegisterService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class SecurityController {
    private LoginService loginService;


    @PostMapping("/get")
    public String getToken(@RequestBody Login loginDto){
        System.out.println("Controller Passed");
        return  loginService.LoginToken(loginDto);
    }
    private RegisterService registerService;

    @PostMapping("/reg")
    public String newRegistration(@RequestBody UserSecuritiy register){
        return registerService.registerNewUser(register);
    }
}
