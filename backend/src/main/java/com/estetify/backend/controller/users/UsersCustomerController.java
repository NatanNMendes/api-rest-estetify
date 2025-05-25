package com.estetify.backend.controller.users;

import com.estetify.backend.models.itens.ItensProduct;
import com.estetify.backend.models.users.UsersCustomer;
import com.estetify.backend.repository.itens.ItensProductRepository;
import com.estetify.backend.repository.users.UsersCustomerRepository;
import com.estetify.backend.utils.Gender;
import com.estetify.backend.utils.Sexuality;
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
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/users/customers")
@RequiredArgsConstructor
public class UsersCustomerController {

    private final UsersCustomerRepository usersCustomerRepository;
    private final ItensProductRepository itensProductRepository;
    private final PasswordEncoder passwordEncoder;

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
                .sexuality(userDTO.getSexuality())
                .profilePhoto(userDTO.getProfilePhoto())
                .build();

        UsersCustomer savedUser = usersCustomerRepository.save(newUser);
        return ResponseEntity.created(URI.create("/api/users/customers/" + savedUser.getId()))
                .body(savedUser);
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
                    existingUser.setSexuality(updateDTO.getSexuality());
                    existingUser.setProfilePhoto(updateDTO.getProfilePhoto());

                    UsersCustomer updatedUser = usersCustomerRepository.save(existingUser);
                    return ResponseEntity.ok(updatedUser);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{userId}/cart")
    public ResponseEntity<?> addToCart(
            @PathVariable Long userId,
            @Valid @RequestBody ItemOperationDTO itemDTO) {

        // Busca o usuário
        Optional<UsersCustomer> userOpt = usersCustomerRepository.findById(userId);
        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Busca o item pelo ID
        Optional<ItensProduct> itemOpt = itensProductRepository.findById(itemDTO.getItemId());
        if (itemOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Item não encontrado");
        }

        UsersCustomer user = userOpt.get();
        ItensProduct item = itemOpt.get();

        // Adiciona o item ao carrinho
        user.getShoppingCart().add(item);
        usersCustomerRepository.save(user);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{userId}/purchase")
    public ResponseEntity<?> purchaseItems(@PathVariable Long userId) {
        return usersCustomerRepository.findById(userId)
                .map(user -> {
                    user.getPurchasedItems().addAll(user.getShoppingCart());
                    user.getPurchaseHistory().addAll(user.getShoppingCart());
                    user.getShoppingCart().clear();
                    usersCustomerRepository.save(user);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
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
        private Sexuality sexuality;
        private String profilePhoto;
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
        private Sexuality sexuality;
        private String profilePhoto;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ItemOperationDTO {
        @NotNull
        private Long itemId;
    }
}
