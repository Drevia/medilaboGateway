package com.openclassrooms.medilaboGateway.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Date;

@Service
public class JwtService {


    private static final String SECRET_KEY = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";

    public String generateToken(Principal principal) {

        return Jwts.builder()
                .subject(principal.getName())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(SignatureAlgorithm.HS256, Decoders.BASE64.decode(SECRET_KEY))
                .compact();
    }
}
