package com.estetify.backend.utils;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "payment_type")
public abstract class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean active = true;
    private LocalDateTime createdAt = LocalDateTime.now();

    public abstract PaymentResult processPayment(BigDecimal amount);

    // Getters e Setters
    public Long getId() { return id; }
    public Boolean isActive() { return active; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}