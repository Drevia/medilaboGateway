package com.openclassrooms.medilaboGateway.config;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.codec.support.DefaultServerCodecConfigurer;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.security.Key;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    private String jwtKey = "4261656C64756E67";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(csrf -> csrf.disable()).cors(cors -> cors.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(request -> request.anyRequest().permitAll())
                .httpBasic(Customizer.withDefaults()).build();
    }

    //clé en base64 encoded
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.jwtKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Bean
    public UserDetailsService users() {
        UserDetails user =
                User.builder().username("user").password(passwordEncoder().encode("password"))
                        .roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
