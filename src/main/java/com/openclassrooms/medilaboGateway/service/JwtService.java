package com.openclassrooms.medilaboGateway.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Service
public class JwtService {


    private String jwtSecret;

    @Value("${jwt.expirationMs}")
    private int jwtExpirationMs;

    @PostConstruct
    public void generateSecretKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA512");
        keyGenerator.init(512);
        jwtSecret = keyGenerator.generateKey().toString();
    }

    public String generateToken(Authentication authentication) {
        Key key = Keys.hmacShaKeyFor(new byte[512]);
        User userDetails = (User) authentication.getPrincipal();

        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(key, SignatureAlgorithm.HS512 )
                .compact();
    }
}
