package com.estetify.backend.authentication;

import com.estetify.backend.models.users.UserHairdresser;
import com.estetify.backend.models.users.Users;
import com.estetify.backend.models.users.UsersCustomer;
import com.estetify.backend.repository.users.UserHairdresserRepository;
import com.estetify.backend.repository.users.UsersCustomerRepository;
import com.estetify.backend.security.JwtTokenProvider;
import com.estetify.backend.services.FirebaseService;
import com.estetify.backend.utils.Gender;
import com.estetify.backend.utils.Sexuality;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.firebase.auth.FirebaseToken;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final FirebaseService firebaseService;
    private final UsersCustomerRepository usersCustomerRepository;
    private final UserHairdresserRepository userHairdresserRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/firebase-login")
    public ResponseEntity<AuthResponse> firebaseLogin(@RequestBody FirebaseTokenDto firebaseTokenDto) {
        try {
            FirebaseToken decodedToken = firebaseService.verifyToken(firebaseTokenDto.token());
            String email = decodedToken.getEmail();
            String firebaseUid = decodedToken.getUid();
            String name = decodedToken.getName() != null ? decodedToken.getName() : "Usuário Sem Nome";
            String photo = decodedToken.getPicture() != null ? decodedToken.getPicture() : "";

            Optional<Users> userOptional = usersCustomerRepository.findByEmail(email)
                    .map(Users.class::cast)
                    .or(() -> userHairdresserRepository.findByEmail(email).map(Users.class::cast));

            Users user = userOptional.orElseGet(() -> {
                UsersCustomer newUser = new UsersCustomer();
                newUser.setEmail(email);
                newUser.setName(name);
                newUser.setProfilePhoto(photo);
                newUser.setFirebaseUid(firebaseUid);
                newUser.setGender(Gender.DEFAULT);
                newUser.setSexuality(Sexuality.DEFAULT);

                return usersCustomerRepository.save(newUser);
            });

            if (user.getFirebaseUid() == null || !user.getFirebaseUid().equals(firebaseUid)) {
                user.setFirebaseUid(firebaseUid);
                if (user instanceof UsersCustomer) {
                    usersCustomerRepository.save((UsersCustomer) user);
                } else {
                    userHairdresserRepository.save((UserHairdresser) user);
                }
            }

            String localJwtToken = jwtTokenProvider.generateToken(email);
            return ResponseEntity.ok(new AuthResponse(localJwtToken, "Login realizado com sucesso"));

        } catch (Exception e) {
            return ResponseEntity.status(401).body(new AuthResponse(null, "Falha na autenticação: " + e.getMessage()));
        }
    }

    public record AuthResponse(String token, String message) {}

    public record FirebaseTokenDto(String token) {}
}