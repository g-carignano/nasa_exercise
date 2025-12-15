package com.carignano.nasa_exercise.controller;

import com.carignano.nasa_exercise.dto.local.AuthRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtEncoder encoder;
    private final InMemoryUserDetailsManager userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(JwtEncoder encoder,
                          InMemoryUserDetailsManager userDetailsManager,
                          PasswordEncoder passwordEncoder) {
        this.encoder = encoder;
        this.userDetailsService = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/token")
    public ResponseEntity<Map<String, String>> token(@RequestBody AuthRequest request) {

        //Load the user from InMemoryUserDetailsManager
        UserDetails user;
        try {
            user = userDetailsService.loadUserByUsername(request.getUsername());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        //Check password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        //Build JWT claims
        Instant now = Instant.now();
        long expiry = 3600L;

        // Include all roles dynamically
        String roles = user.getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("nasa-awc-app")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(user.getUsername())
                .claim("scope", roles)   // <-- include all roles
                .build();

        //Encode token
        String token = encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok(Map.of("access_token", token));
    }
}