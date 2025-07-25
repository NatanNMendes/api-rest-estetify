package com.estetify.backend.controller.users;

import com.estetify.backend.authentication.JwtUtils;
import com.estetify.backend.models.itens.ItensProduct;
import com.estetify.backend.models.itens.PurchaseHistory;
import com.estetify.backend.models.users.UsersCustomer;
import com.estetify.backend.repository.itens.ItensProductRepository;
import com.estetify.backend.repository.itens.PurchaseHistoryRepository;
import com.estetify.backend.repository.users.UsersCustomerRepository;
import com.estetify.backend.utils.Gender;
import com.estetify.backend.utils.UsersType;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/users/customers")
@RequiredArgsConstructor
public class UsersCustomerController {

    private final UsersCustomerRepository usersCustomerRepository;
    private final ItensProductRepository itensProductRepository;
    private final PurchaseHistoryRepository purchaseHistoryRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UsersCustomerDTO userDTO) {
        if (usersCustomerRepository.existsByEmail(userDTO.getEmail())) {
            return ResponseEntity.badRequest().body("Email já está em uso");
        }

        UsersCustomer newUser = UsersCustomer.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .birthDate(userDTO.getBirthDate())
                .gender(userDTO.getGender())
                .profilePhoto(userDTO.getProfilePhoto())
                .vat(userDTO.getVat())
                .phone(userDTO.getPhone())
                .whatsapp(userDTO.getWhatsapp())
                .usersType(UsersType.CUSTOMER)
                .build();

        UsersCustomer savedUser = usersCustomerRepository.save(newUser);

        List<GrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority("ROLE_" + savedUser.getUsersType().name())
        );

        String token = jwtUtils.generateToken(savedUser.getEmail(), authorities);

        AuthResponse response = new AuthResponse(token, savedUser);

        return ResponseEntity.created(URI.create("/api/users/customers/" + savedUser.getId()))
                .body(response);
    }

    @PostMapping("/{userId}/profile-photo")
    @Transactional
    public ResponseEntity<?> uploadProfilePhoto(
            @PathVariable Long userId,
            @RequestParam("file") MultipartFile file) {

        try {

            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("Arquivo não pode estar vazio");
            }

            if (file.getContentType() == null || !file.getContentType().startsWith("image/")) {
                return ResponseEntity.badRequest().body("Apenas arquivos de imagem são permitidos");
            }

            if (file.getSize() > 5 * 1024 * 1024) {
                return ResponseEntity.badRequest().body("O tamanho da imagem não pode exceder 5MB");
            }

            String base64Image = Base64.getEncoder().encodeToString(file.getBytes());

            return usersCustomerRepository.findById(userId)
                    .map(user -> {
                        user.setProfilePhoto(base64Image);
                        usersCustomerRepository.save(user);
                        return ResponseEntity.ok("Foto de perfil atualizada com sucesso");
                    })
                    .orElse(ResponseEntity.notFound().build());

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao processar a imagem: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Page<UsersCustomer>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        return ResponseEntity.ok(usersCustomerRepository.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersCustomer> getUserById(@PathVariable Long id) {
        return usersCustomerRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UsersCustomerUpdateDTO updateDTO) {

        return usersCustomerRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setName(updateDTO.getName());
                    existingUser.setBirthDate(updateDTO.getBirthDate());
                    existingUser.setGender(updateDTO.getGender());
                    existingUser.setVat(updateDTO.getVat());
                    existingUser.setPhone(updateDTO.getPhone());
                    existingUser.setWhatsapp(updateDTO.getWhatsapp());
                    if (updateDTO.getProfilePhoto() != null) {
                        existingUser.setProfilePhoto(updateDTO.getProfilePhoto());
                    }

                    UsersCustomer updatedUser = usersCustomerRepository.save(existingUser);
                    return ResponseEntity.ok(updatedUser);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{userId}/cart")
    public ResponseEntity<?> addToCart(
            @PathVariable Long userId,
            @Valid @RequestBody ItemOperationDTO itemDTO) {

        Optional<UsersCustomer> userOpt = usersCustomerRepository.findById(userId);
        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Optional<ItensProduct> itemOpt = itensProductRepository.findById(itemDTO.getItemId());
        if (itemOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Item não encontrado");
        }

        UsersCustomer user = userOpt.get();
        ItensProduct item = itemOpt.get();
        user.getShoppingCart().add(item);
        usersCustomerRepository.save(user);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{userId}/purchase")
    @Transactional
    public ResponseEntity<?> purchaseItems(@PathVariable Long userId) {
        Optional<UsersCustomer> userOpt = usersCustomerRepository.findById(userId);
        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        UsersCustomer user = userOpt.get();

        // Adiciona itens à lista de comprados
        user.getPurchasedItems().addAll(user.getShoppingCart());

        // Cria histórico de compra para cada item
        user.getShoppingCart().forEach(item -> {
            user.getPurchaseHistory().add(new PurchaseHistory(user, item));
        });

        user.getShoppingCart().clear();
        usersCustomerRepository.save(user);

        return ResponseEntity.ok(Map.of(
                "message", "Compra realizada com sucesso",
                "purchasedItems", user.getPurchasedItems().size(),
                "newHistoryEntries", user.getPurchaseHistory().size()
        ));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (usersCustomerRepository.existsById(id)) {
            usersCustomerRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UsersCustomerDTO {
        @NotBlank
        private String name;

        @Email
        @NotBlank
        private String email;

        @NotBlank
        @Size(min = 8)
        private String password;

        @Past
        private Date birthDate;
        private Gender gender;
        private String profilePhoto;

        private String vat;

        @Size(max = 20)
        private String phone;

        @Size(max = 20)
        private String whatsapp;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UsersCustomerUpdateDTO {
        @NotBlank
        private String name;

        @Past
        private Date birthDate;
        private Gender gender;
        private String profilePhoto;
        private String vat;

        @Size(max = 20)
        private String phone;

        @Size(max = 20)
        private String whatsapp;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ItemOperationDTO {
        @NotNull
        private Long itemId;
    }
    @Data
    @AllArgsConstructor
    private static class AuthResponse {
        private String token;
        private UsersCustomer user;
    }
}
