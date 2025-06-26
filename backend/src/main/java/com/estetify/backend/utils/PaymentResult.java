package com.estetify.backend.utils;

import java.time.LocalDateTime;

public class PaymentResult {
    private PaymentStatus status;
    private String transactionId;
    private String message;
    private LocalDateTime processedAt = LocalDateTime.now();

    // Getters e Setters
    public PaymentStatus getStatus() { return status; }
    public void setStatus(PaymentStatus status) { this.status = status; }
    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public LocalDateTime getProcessedAt() { return processedAt; }
}