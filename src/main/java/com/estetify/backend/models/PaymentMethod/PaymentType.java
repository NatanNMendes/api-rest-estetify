package com.estetify.backend.models.PaymentMethod;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Enum que representa os tipos de métodos de pagamento aceitos pelo sistema Estetify.
 */
public enum PaymentType {

    CREDIT_CARD("CREDIT_CARD", "Cartão de Crédito", true, "💳"),
    DEBIT_CARD("DEBIT_CARD", "Cartão de Débito", false, "🏦"),
    PIX("PIX", "Pix", false, "🔁"),
    BOLETO("BOLETO", "Boleto Bancário", false, "🧾"),
    PAYPAL("PAYPAL", "PayPal", true, "🌐"),
    TRANSFER("TRANSFER", "Transferência Bancária", false, "💸");

    private static final Map<String, PaymentType> CODE_MAP =
            Arrays.stream(values()).collect(Collectors.toUnmodifiableMap(pt -> pt.code, pt -> pt));

    private final String code;
    private final String displayName;
    private final boolean allowsInstallments;
    private final String icon;

    PaymentType(String code, String displayName, boolean allowsInstallments, String icon) {
        this.code = code;
        this.displayName = displayName;
        this.allowsInstallments = allowsInstallments;
        this.icon = icon;
    }

    /**
     * Retorna o código técnico do método de pagamento.
     */
    @JsonValue
    public String getCode() {
        return code;
    }

    /**
     * Nome legível para exibição ao usuário.
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Retorna se permite parcelamento.
     */
    public boolean allowsInstallments() {
        return allowsInstallments;
    }

    /**
     * Ícone ou emoji representativo do método.
     */
    public String getIcon() {
        return icon;
    }

    /**
     * Cria um PaymentType a partir do código técnico.
     */
    @JsonCreator
    public static PaymentType fromCode(String code) {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Código de pagamento não pode ser nulo ou vazio.");
        }
        PaymentType type = CODE_MAP.get(code.toUpperCase());
        if (type == null) {
            throw new IllegalArgumentException("Método de pagamento desconhecido: " + code);
        }
        return type;
    }

    /**
     * Lista todos os métodos com seus dados completos (útil para front-ends).
     */
    public static List<PaymentType> getAllTypes() {
        return List.of(values());
    }

    /**
     * Lista apenas os nomes amigáveis de todos os métodos.
     */
    public static List<String> getAllDisplayNames() {
        return Arrays.stream(values())
                .map(PaymentType::getDisplayName)
                .toList();
    }

    /**
     * Retorna um DTO com todos os dados relevantes (útil para APIs).
     */
    public PaymentTypeDTO toDTO() {
        return new PaymentTypeDTO(code, displayName, allowsInstallments, icon);
    }

    @Override
    public String toString() {
        return icon + " " + displayName;
    }

    /**
     * DTO auxiliar para exposição segura dos dados.
     */
    public record PaymentTypeDTO(String code, String displayName, boolean allowsInstallments, String icon) {}
}
