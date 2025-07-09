package com.estetify.backend.repository.users;

import com.estetify.backend.models.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    @Query("SELECT u FROM Users u LEFT JOIN FETCH u.profilePhoto WHERE u.email = :email")
    Optional<Users> findByEmail(@Param("email") String email);
}
