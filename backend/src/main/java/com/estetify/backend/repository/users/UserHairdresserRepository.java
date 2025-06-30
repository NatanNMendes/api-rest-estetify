package com.estetify.backend.repository.users;

import com.estetify.backend.models.users.UserHairdresser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserHairdresserRepository extends JpaRepository<UserHairdresser, Long> {
    Optional<UserHairdresser> findByEmail(String email);
}
