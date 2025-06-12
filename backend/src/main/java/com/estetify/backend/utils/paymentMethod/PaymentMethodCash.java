package com.estetify.backend.utils.paymentMethod;

import java.time.LocalDateTime;

public class PaymentMethodCash extends PaymentMethod{
    private String receivedBy;
    private String changeGiven;
    private LocalDateTime paymentLocation;
}
