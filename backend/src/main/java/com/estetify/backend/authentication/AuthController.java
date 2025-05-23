package com.estetify.backend.authentication;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authManager, JwtUtils jwtUtils) {
        this.authManager = authManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(), // Usar .username() ao invés de .getUsername()
                        request.password()  // Usar .password() ao invés de .getPassword()
                )
        );

        String token = jwtUtils.generateToken(request.username());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}

// DTOs
record AuthRequest(String username, String password) {}
record AuthResponse(String token) {}
