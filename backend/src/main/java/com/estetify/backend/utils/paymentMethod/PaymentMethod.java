package com.estetify.backend.utils.paymentMethod;

import com.estetify.backend.utils.PaymentStatus;
import com.estetify.backend.utils.TypePaymentMethod;

import java.time.LocalDateTime;

public abstract class PaymentMethod {
    private int id;
    private TypePaymentMethod type;
    private Double amount;
    private Double currency;
    private LocalDateTime transactionDate;
    private PaymentStatus status;
}
