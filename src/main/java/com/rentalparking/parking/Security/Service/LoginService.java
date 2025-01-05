package com.rentalparking.parking.Security.Service;

import com.rentalparking.parking.Security.Jwts.JwtsUtil;
import com.rentalparking.parking.Security.Models.Login;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {
    private AuthenticationManager authenticationManager;
    private JwtsUtil jwtUtils;

    public String LoginToken(Login loginDto){
        System.out.println(loginDto.getEmail()+" "+loginDto.getPassword());
        Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(
                authentication
        );

        return jwtUtils.getJwtsToken(authentication);

    }
}
