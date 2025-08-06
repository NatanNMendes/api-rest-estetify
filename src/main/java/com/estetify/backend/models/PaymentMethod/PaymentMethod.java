package com.estetify.backend.models.PaymentMethod;

import com.estetify.backend.utils.PaymentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entidade que representa uma forma de pagamento associada a uma transação financeira.
 * Usa validações integradas e estrutura preparada para persistência e auditoria.
 */
@Entity
@Table(name = "payment_methods")
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Tipo de pagamento é obrigatório.")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentType type;

    @NotNull(message = "Valor é obrigatório.")
    @DecimalMin(value = "0.00", inclusive = true, message = "Valor do pagamento não pode ser negativo.")
    @Digits(integer = 10, fraction = 2, message = "Valor inválido. Máximo de 10 dígitos inteiros e 2 decimais.")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @NotNull(message = "Moeda é obrigatória.")
    @Size(min = 3, max = 3, message = "Código da moeda deve ter exatamente 3 letras.")
    @Column(nullable = false, length = 3)
    private String currency;

    @NotNull(message = "Data da transação é obrigatória.")
    @PastOrPresent(message = "Data da transação não pode estar no futuro.")
    @Column(nullable = false)
    private LocalDateTime transactionDate;

    @NotNull(message = "Status do pagamento é obrigatório.")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

    // -------------------- Construtores --------------------

    protected PaymentMethod() {
        // Requerido pelo JPA
    }

    public PaymentMethod(PaymentType type, BigDecimal amount, String currency,
                         LocalDateTime transactionDate, PaymentStatus status) {
        this.type = validateType(type);
        this.amount = validateAmount(amount);
        this.currency = validateCurrency(currency);
        this.transactionDate = validateTransactionDate(transactionDate);
        this.status = validateStatus(status);
    }

    // -------------------- Getters --------------------

    public Long getId() {
        return id;
    }

    public PaymentType getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    // -------------------- Setters defensivos --------------------

    public void setType(PaymentType type) {
        this.type = validateType(type);
    }

    public void setAmount(BigDecimal amount) {
        this.amount = validateAmount(amount);
    }

    public void setCurrency(String currency) {
        this.currency = validateCurrency(currency);
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = validateTransactionDate(transactionDate);
    }

    public void setStatus(PaymentStatus status) {
        this.status = validateStatus(status);
    }

    // -------------------- Validações privadas --------------------

    private PaymentType validateType(PaymentType type) {
        return Objects.requireNonNull(type, "Tipo de pagamento não pode ser nulo.");
    }

    private BigDecimal validateAmount(BigDecimal amount) {
        Objects.requireNonNull(amount, "Valor não pode ser nulo.");
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Valor do pagamento não pode ser negativo.");
        }
        return amount.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private String validateCurrency(String currency) {
        Objects.requireNonNull(currency, "Moeda não pode ser nula.");
        String trimmed = currency.trim().toUpperCase();
        if (trimmed.length() != 3 || !trimmed.matches("[A-Z]{3}")) {
            throw new IllegalArgumentException("Moeda inválida. Use o código ISO 4217 (ex: BRL, USD).");
        }
        return trimmed;
    }

    private LocalDateTime validateTransactionDate(LocalDateTime date) {
        Objects.requireNonNull(date, "Data da transação não pode ser nula.");
        if (date.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Data da transação não pode estar no futuro.");
        }
        return date;
    }

    private PaymentStatus validateStatus(PaymentStatus status) {
        return Objects.requireNonNull(status, "Status do pagamento não pode ser nulo.");
    }

    // -------------------- Utilitários --------------------

    @Override
    public String toString() {
        return String.format(
                "PaymentMethod{id=%d, tipo=%s, valor=%.2f, moeda='%s', data=%s, status=%s}",
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
