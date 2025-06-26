package com.estetify.backend.repository.users;

import com.estetify.backend.models.users.UsersCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersCustomerRepository extends JpaRepository<UsersCustomer, Long> {
    boolean existsByEmail(String email);

    Optional<UsersCustomer> findByEmail(String email);
}
