package com.openclassrooms.medilaboGateway.controller;

import com.openclassrooms.medilaboGateway.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @PostMapping("/api/auth/login")
    public ResponseEntity<?> login(Authentication authentication) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        System.out.println(SecurityContextHolder.getContext());

        System.out.println(authentication);
        String token = jwtService.generateToken(authentication);

        return ResponseEntity.ok(token);
    }
}
