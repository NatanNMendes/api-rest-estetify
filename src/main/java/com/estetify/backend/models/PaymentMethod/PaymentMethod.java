package com.estetify.backend.models.PaymentMethod;

import com.estetify.backend.utils.PaymentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "payment_methods")
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PaymentType type;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private String currency;

    @NotNull
    private LocalDateTime transactionDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    public PaymentMethod() {
    }

    public PaymentMethod(PaymentType type, BigDecimal amount, String currency,
                         LocalDateTime transactionDate, PaymentStatus status) {
        this.type = type;
        this.amount = amount;
        this.currency = currency;
        this.transactionDate = transactionDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public PaymentType getType() {
        return type;
    }

    public void setType(PaymentType type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PaymentMethod{" +
                "id=" + id +
                ", type=" + type +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", transactionDate=" + transactionDate +
                ", status=" + status +
                '}';
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

    private class PaymentType {
    }
}
