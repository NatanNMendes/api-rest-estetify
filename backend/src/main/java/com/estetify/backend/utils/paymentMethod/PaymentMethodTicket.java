package com.estetify.backend.utils.paymentMethod;

import java.time.LocalDateTime;

public class PaymentMethodTicket extends PaymentMethod{
    private String ticketNumber;
    private LocalDateTime expirationDate;
    private String barcode;
    private LocalDateTime issuer;
}
