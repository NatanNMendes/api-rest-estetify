package com.estetify.backend.models.PaymentMethod;

import com.estetify.backend.utils.PaymentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entidade que representa uma forma de pagamento associada a uma transação.
 */
@Entity
@Table(name = "payment_methods")
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Tipo de pagamento não pode ser nulo.")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentType type;

    @NotNull(message = "Valor não pode ser nulo.")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @NotNull(message = "Moeda não pode ser nula.")
    @Column(nullable = false, length = 3)
    private String currency;

    @NotNull(message = "Data da transação não pode ser nula.")
    @Column(nullable = false)
    private LocalDateTime transactionDate;

    @NotNull(message = "Status do pagamento não pode ser nulo.")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

    // --- Construtores ---

    public PaymentMethod() {
        // Construtor padrão exigido por JPA
    }

    public PaymentMethod(PaymentType type, BigDecimal amount, String currency,
                         LocalDateTime transactionDate, PaymentStatus status) {
        setType(type);
        setAmount(amount);
        setCurrency(currency);
        setTransactionDate(transactionDate);
        setStatus(status);
    }

    // --- Getters e Setters com validações defensivas ---

    public Long getId() {
        return id;
    }

    public PaymentType getType() {
        return type;
    }

    public void setType(PaymentType type) {
        this.type = Objects.requireNonNull(type, "Tipo de pagamento não pode ser nulo.");
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        Objects.requireNonNull(amount, "Valor não pode ser nulo.");
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Valor do pagamento não pode ser negativo.");
        }
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        Objects.requireNonNull(currency, "Moeda não pode ser nula.");
        if (currency.trim().length() != 3) {
            throw new IllegalArgumentException("Código da moeda deve conter 3 letras (ex: BRL, USD).");
        }
        this.currency = currency.trim().toUpperCase();
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = Objects.requireNonNull(transactionDate, "Data da transação não pode ser nula.");
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = Objects.requireNonNull(status, "Status do pagamento não pode ser nulo.");
    }

    // --- Utilitários ---

    @Override
    public String toString() {
        return String.format(
                "PaymentMethod{id=%d, type=%s, amount=%.2f, currency='%s', date=%s, status=%s}",
                id, type, amount, currency, transactionDate, status
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentMethod that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
