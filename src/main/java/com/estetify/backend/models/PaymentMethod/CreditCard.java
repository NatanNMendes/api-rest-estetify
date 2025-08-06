package com.estetify.backend.models.PaymentMethod;

import java.time.YearMonth;
import java.util.Objects;

/**
 * Representa um cartão de crédito utilizado como método de pagamento.
 * Inclui informações do titular, número, validade, CVV, bandeira e parcelas.
 */
public class CreditCard {

    private String cardNumber;
    private String cardholderName;
    private YearMonth expirationDate;
    private String cvv;
    private int installments;
    private String cardBrand;

    /** Construtor vazio - necessário para serialização e frameworks como JPA ou Jackson. */
    public CreditCard() {}

    /**
     * Construtor completo com validações.
     *
     * @param cardNumber       número do cartão sem espaços
     * @param cardholderName   nome do titular
     * @param expirationDate   validade do cartão
     * @param cvv              código de verificação
     * @param installments     número de parcelas (1-24)
     * @param cardBrand        bandeira (Visa, MasterCard etc.)
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

    // ------------------- Getters e Setters com validação -------------------

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        Objects.requireNonNull(cardNumber, "Número do cartão não pode ser nulo.");
        if (!cardNumber.matches("\\d{13,19}")) {
            throw new IllegalArgumentException("Número do cartão deve conter entre 13 e 19 dígitos.");
        }
        this.cardNumber = cardNumber;
    }

    public String getCardholderName() {
        return cardholderName;
    }

    public void setCardholderName(String cardholderName) {
        this.cardholderName = Objects.requireNonNull(cardholderName, "Nome do titular não pode ser nulo.");
    }

    public YearMonth getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(YearMonth expirationDate) {
        Objects.requireNonNull(expirationDate, "Data de validade não pode ser nula.");
        if (expirationDate.isBefore(YearMonth.now())) {
            throw new IllegalArgumentException("O cartão está expirado.");
        }
        this.expirationDate = expirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        Objects.requireNonNull(cvv, "CVV não pode ser nulo.");
        if (!cvv.matches("\\d{3,4}")) {
            throw new IllegalArgumentException("CVV inválido. Deve conter 3 ou 4 dígitos.");
        }
        this.cvv = cvv;
    }

    public int getInstallments() {
        return installments;
    }

    public void setInstallments(int installments) {
        if (installments < 1 || installments > 24) {
            throw new IllegalArgumentException("Número de parcelas deve ser entre 1 e 24.");
        }
        this.installments = installments;
    }

    public String getCardBrand() {
        return cardBrand;
    }

    public void setCardBrand(String cardBrand) {
        this.cardBrand = Objects.requireNonNull(cardBrand, "Bandeira do cartão não pode ser nula.");
    }

    // ------------------- Métodos utilitários -------------------

    /** Verifica se o cartão está vencido. */
    public boolean isExpired() {
        return expirationDate.isBefore(YearMonth.now());
    }

    /** Retorna uma versão mascarada do número do cartão, exibindo apenas os 4 últimos dígitos. */
    public String getMaskedNumber() {
        if (cardNumber == null || cardNumber.length() < 4) return "****";
        return "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
    }

    @Override
    public String toString() {
        return String.format(
                "CreditCard{número='%s', titular='%s', validade=%s, parcelas=%d, bandeira='%s'}",
                getMaskedNumber(), cardholderName, expirationDate, installments, cardBrand
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
