package com.estetify.backend.models.PaymentMethod;

import java.time.YearMonth;
import java.util.Objects;

/**
 * Representa um cartão de crédito utilizado como método de pagamento.
 */
public class CreditCard {

    private String cardNumber;
    private String cardholderName;
    private YearMonth expirationDate;
    private String cvv;
    private int installments;
    private String cardBrand;

    /**
     * Construtor padrão (necessário para frameworks como Jackson ou JPA).
     */
    public CreditCard() {
        // Construtor vazio
    }

    /**
     * Construtor completo com validações.
     */
    public CreditCard(String cardNumber, String cardholderName, YearMonth expirationDate,
                      String cvv, int installments, String cardBrand) {
        setCardNumber(cardNumber);
        setCardholderName(cardholderName);
        setExpirationDate(expirationDate);
        setCvv(cvv);
        setInstallments(installments);
        setCardBrand(cardBrand);
    }

    // --- Getters e Setters com validações seguras ---

    /** @return Número do cartão (sem formatação) */
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        Objects.requireNonNull(cardNumber, "Número do cartão não pode ser nulo.");
        if (cardNumber.length() < 13 || cardNumber.length() > 19 || !cardNumber.matches("\\d+")) {
            throw new IllegalArgumentException("Número do cartão inválido.");
        }
        this.cardNumber = cardNumber;
    }

    /** @return Nome do titular do cartão */
    public String getCardholderName() {
        return cardholderName;
    }

    public void setCardholderName(String cardholderName) {
        this.cardholderName = Objects.requireNonNull(cardholderName, "Nome do titular não pode ser nulo.");
    }

    /** @return Data de expiração do cartão */
    public YearMonth getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(YearMonth expirationDate) {
        Objects.requireNonNull(expirationDate, "Data de validade não pode ser nula.");
        if (expirationDate.isBefore(YearMonth.now())) {
            throw new IllegalArgumentException("Cartão expirado.");
        }
        this.expirationDate = expirationDate;
    }

    /** @return Código de segurança (CVV) */
    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        Objects.requireNonNull(cvv, "CVV não pode ser nulo.");
        if (!cvv.matches("\\d{3,4}")) {
            throw new IllegalArgumentException("CVV inválido.");
        }
        this.cvv = cvv;
    }

    /** @return Número de parcelas (mínimo: 1) */
    public int getInstallments() {
        return installments;
    }

    public void setInstallments(int installments) {
        if (installments < 1 || installments > 24) {
            throw new IllegalArgumentException("Número de parcelas deve ser entre 1 e 24.");
        }
        this.installments = installments;
    }

    /** @return Bandeira do cartão (ex: Visa, MasterCard) */
    public String getCardBrand() {
        return cardBrand;
    }

    public void setCardBrand(String cardBrand) {
        this.cardBrand = Objects.requireNonNull(cardBrand, "Bandeira do cartão não pode ser nula.");
    }

    // --- Métodos utilitários ---

    @Override
    public String toString() {
        String masked = cardNumber != null && cardNumber.length() >= 4
                ? "**** **** **** " + cardNumber.substring(cardNumber.length() - 4)
                : "****";
        return String.format(
                "CreditCard{número='%s', titular='%s', validade=%s, parcelas=%d, bandeira='%s'}",
                masked, cardholderName, expirationDate, installments, cardBrand
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreditCard)) return false;
        CreditCard that = (CreditCard) o;
        return installments == that.installments &&
                Objects.equals(cardNumber, that.cardNumber) &&
                Objects.equals(cardholderName, that.cardholderName) &&
                Objects.equals(expirationDate, that.expirationDate) &&
                Objects.equals(cvv, that.cvv) &&
                Objects.equals(cardBrand, that.cardBrand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, cardholderName, expirationDate, cvv, installments, cardBrand);
    }
}
