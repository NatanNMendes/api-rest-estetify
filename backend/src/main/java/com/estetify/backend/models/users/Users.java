package com.estetify.backend.models.users;

import com.estetify.backend.utils.Gender;
import com.estetify.backend.utils.Sexuality;
import com.estetify.backend.utils.UsersType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Access(AccessType.FIELD)
public abstract class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String name;

    @Past
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Sexuality sexuality;

    @Size(max = 14)
    private String vat;

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @Column(nullable = true)
    private String password;

    @Column(unique = true)
    private String firebaseUid;

    @Enumerated(EnumType.STRING)
    @Column(name = "users_type", insertable = false, updatable = false)
    private UsersType usersType;

    private LocalDateTime createdAt;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String profilePhoto;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
