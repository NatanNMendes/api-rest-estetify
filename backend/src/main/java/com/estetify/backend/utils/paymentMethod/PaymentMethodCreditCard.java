package com.estetify.backend.utils.paymentMethod;

import java.time.LocalDateTime;

public class PaymentMethodCreditCard extends PaymentMethod{
    private String cardNumber;
    private String cardholderName;
    private LocalDateTime expirationDate;
    private String cvv;
    private String installments;
    private String cardBrand;
}
