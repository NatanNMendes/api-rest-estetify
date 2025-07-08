package com.estetify.backend.authentication;

import com.estetify.backend.models.users.Users;
import com.estetify.backend.repository.users.UsersRepository;
import com.estetify.backend.utils.Gender;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtils jwtUtils;
    private final UsersRepository usersRepository;

    public AuthController(
            AuthenticationManager authManager,
            JwtUtils jwtUtils,
            UsersRepository usersRepository) {
        this.authManager = authManager;
        this.jwtUtils = jwtUtils;
        this.usersRepository = usersRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        Users user = usersRepository.findByEmail(request.username())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + request.username()));

        String token = jwtUtils.generateToken(request.username());

        return ResponseEntity.ok(new AuthResponse(
                token,
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getUsersType().name(),
                user.getProfilePhoto(),
                user.getBirthDate(),
                user.getGender(),
                user.getVat(),
                user.getPhone(),
                user.getWhatsapp()
        ));
    }
}

record AuthRequest(String username, String password) {}

record AuthResponse(
        String token,
        Long userId,
        String name,
        String email,
        String userType,
        String profilePhoto,
        Date birthDate,
        Gender gender,
        String vat,
        String phone,
        String whatsapp
) {}
