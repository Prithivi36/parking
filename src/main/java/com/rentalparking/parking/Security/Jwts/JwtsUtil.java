package com.rentalparking.parking.Security.Jwts;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@AllArgsConstructor
public class JwtsUtil {
    private final String jwtsSecret="a8800cb29902ade31b3d39a73babc7fd5d2c8691082761c7ba0aa22f720820e3";

    private final long jwtsExpiration=604800000;

    public String getJwtsToken(Authentication authentication){
        String username= authentication.getName();
        Date issuedAt=new Date();

        Date expiration=new Date(issuedAt.getTime()+jwtsExpiration);

        String token= Jwts.builder()
                .subject(username)
                .issuedAt(issuedAt)
                .expiration(expiration)
                .signWith(getKey())
                .compact();
        return token;
    }

    public  String getUsername(String token){
        String username=Jwts.parser()
                .setSigningKey(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
        return username;
    }

    public Boolean isValid(String token){
        Jwts.parser()
                .setSigningKey(getKey())
                .build()
                .parse(token);

        return true;
    }

    public Key getKey(){
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtsSecret)
        );
    }
}
