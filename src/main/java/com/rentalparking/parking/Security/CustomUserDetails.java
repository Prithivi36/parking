package com.rentalparking.parking.Security;


import com.rentalparking.parking.Exception.ApiException;
import com.rentalparking.parking.Security.Models.LoginRepo;
import com.rentalparking.parking.Security.Models.UserSecuritiy;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@Service
@AllArgsConstructor
public class CustomUserDetails implements UserDetailsService {
    private LoginRepo userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserSecuritiy currentUser=userRepository.findByEmail(username).orElseThrow(()->new ApiException(HttpStatus.NOT_FOUND,"User Does Not Exist"));
        return new User(
                currentUser.getEmail(),
                currentUser.getPassword(),
                Set.of()
        );
    }
}
