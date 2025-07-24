package com.estetify.backend.models.PaymentMethod;

import java.time.YearMonth;
import java.util.Objects;

/**
 * Classe que representa um cartão de crédito utilizado como método de pagamento.
 */
public class CreditCard {
    private String cardNumber;
    private String cardholderName;
    private YearMonth expirationDate;
    private String cvv;
    private int installments;
    private String cardBrand;

    /**
     * Construtor padrão.
     */
    public CreditCard() {
    }

    /**
     * Construtor completo.
     */
    public CreditCard(String cardNumber, String cardholderName, YearMonth expirationDate,
                      String cvv, int installments, String cardBrand) {
        this.cardNumber = Objects.requireNonNull(cardNumber, "Número do cartão não pode ser nulo");
        this.cardholderName = Objects.requireNonNull(cardholderName, "Nome do titular não pode ser nulo");
        this.expirationDate = Objects.requireNonNull(expirationDate, "Data de validade não pode ser nula");
        this.cvv = Objects.requireNonNull(cvv, "CVV não pode ser nulo");
        this.installments = installments;
        this.cardBrand = Objects.requireNonNull(cardBrand, "Bandeira do cartão não pode ser nula");
    }

    /** @return Número do cartão */
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = Objects.requireNonNull(cardNumber);
    }

    /** @return Nome do titular do cartão */
    public String getCardholderName() {
        return cardholderName;
    }

    public void setCardholderName(String cardholderName) {
        this.cardholderName = Objects.requireNonNull(cardholderName);
    }

    /** @return Data de expiração do cartão */
    public YearMonth getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(YearMonth expirationDate) {
        this.expirationDate = Objects.requireNonNull(expirationDate);
    }

    /** @return Código de segurança do cartão */
    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = Objects.requireNonNull(cvv);
    }

    /** @return Número de parcelas */
    public int getInstallments() {
        return installments;
    }

    public void setInstallments(int installments) {
        this.installments = installments;
    }

    /** @return Bandeira do cartão (Visa, MasterCard, etc.) */
    public String getCardBrand() {
        return cardBrand;
    }

    public void setCardBrand(String cardBrand) {
        this.cardBrand = Objects.requireNonNull(cardBrand);
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "cardNumber='****" + (cardNumber != null && cardNumber.length() >= 4
                ? cardNumber.substring(cardNumber.length() - 4) : "") + '\'' +
                ", cardholderName='" + cardholderName + '\'' +
                ", expirationDate=" + expirationDate +
                ", installments=" + installments +
                ", cardBrand='" + cardBrand + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreditCard)) return false;
        CreditCard that = (CreditCard) o;
        return Objects.equals(cardNumber, that.cardNumber) &&
                Objects.equals(cardholderName, that.cardholderName) &&
                Objects.equals(expirationDate, that.expirationDate) &&
                Objects.equals(cvv, that.cvv) &&
                installments == that.installments &&
                Objects.equals(cardBrand, that.cardBrand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, cardholderName, expirationDate, cvv, installments, cardBrand);
    }
}
