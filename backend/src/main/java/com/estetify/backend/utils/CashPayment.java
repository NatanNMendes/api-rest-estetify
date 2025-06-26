package com.estetify.backend.utils;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@DiscriminatorValue("CASH")
public class CashPayment extends PaymentMethod {
    private BigDecimal amountReceived;

    @Override
    public PaymentResult processPayment(BigDecimal amount) {
        PaymentResult result = new PaymentResult();
        result.setTransactionId("CASH-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));

        if (amountReceived != null && amountReceived.compareTo(amount) >= 0) {
            result.setStatus(PaymentStatus.COMPLETED);
            result.setMessage("Pagamento em dinheiro recebido");

            if (amountReceived.compareTo(amount) > 0) {
                BigDecimal change = amountReceived.subtract(amount);
                result.setMessage(result.getMessage() + ". Troco: R$ " + change);
            }
        } else {
            result.setStatus(PaymentStatus.FAILED);
            result.setMessage("Valor recebido insuficiente");
        }

        return result;
    }


    // Getters e Setters
    public BigDecimal getAmountReceived() {
        return amountReceived;
    }

    public void setAmountReceived(BigDecimal amountReceived) {
        this.amountReceived = amountReceived;
    }
}
