package com.estetify.backend.utils;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@DiscriminatorValue("PIX")
public class PixPayment extends PaymentMethod {
    private String pixKey;
    private String qrCode;

    @Override
    public PaymentResult processPayment(BigDecimal amount) {
        this.pixKey = "PIX-" + UUID.randomUUID().toString().substring(0, 8);
        this.qrCode = "https://api.pix.example.com/qr/" + pixKey;

        PaymentResult result = new PaymentResult();
        result.setStatus(PaymentStatus.PENDING);
        result.setTransactionId(pixKey);
        result.setMessage("PIX gerado com sucesso. QR Code: " + qrCode);

        return result;
    }

    // Getters
    public String getPixKey() { return pixKey; }
    public String getQrCode() { return qrCode; }
}