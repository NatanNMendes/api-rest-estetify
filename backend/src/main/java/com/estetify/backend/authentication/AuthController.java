package com.estetify.backend.authentication;

import com.estetify.backend.models.users.Users;
import com.estetify.backend.repository.users.UsersRepository;
import com.estetify.backend.utils.Gender;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Date;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtUtils jwtUtils;
    private final UsersRepository usersRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.username(),
                            request.password()
                    )
            );

            Users user = usersRepository.findByEmail(request.username())
                    .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

            String token = jwtUtils.generateToken(user.getEmail(), authorities);

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
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
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
