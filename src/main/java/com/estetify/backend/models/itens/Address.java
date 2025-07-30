package com.estetify.backend.models.itens;

import java.util.Objects;

/**
 * Representa um endereço com rua, número, cidade, estado, CEP e país.
 * Classe preparada para integração com frameworks como JPA ou Jackson.
 */
// @Embeddable — se quiser embutir em outra entidade JPA
public class Address {

    private String street;
    private String number;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    /**
     * Construtor padrão para frameworks.
     */
    public Address() {
        // Construtor vazio necessário para JPA e bibliotecas de serialização
    }

    /**
     * Construtor completo com validações.
     */
    public Address(String street, String number, String city, String state, String postalCode, String country) {
        setStreet(street);
        setNumber(number);
        setCity(city);
        setState(state);
        setPostalCode(postalCode);
        setCountry(country);
    }

    // --- Getters e Setters com validação defensiva ---

    /** @return Nome da rua */
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = validateNotBlank(street, "Rua");
    }

    /** @return Número do imóvel */
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = validateNotBlank(number, "Número");
    }

    /** @return Cidade */
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = validateNotBlank(city, "Cidade");
    }

    /** @return Estado (sigla ou nome) */
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = validateNotBlank(state, "Estado").toUpperCase();
    }

    /** @return Código postal (CEP) */
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = validateNotBlank(postalCode, "CEP");
    }

    /** @return País */
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = validateNotBlank(country, "País").toUpperCase();
    }

    // --- Validação auxiliar ---

    private String validateNotBlank(String value, String fieldName) {
        Objects.requireNonNull(value, fieldName + " não pode ser nulo.");
        String trimmed = value.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " não pode ser vazio.");
        }
        return trimmed;
    }

    // --- Métodos utilitários ---

    @Override
    public String toString() {
        return String.format("%s, %s - %s/%s, %s, %s",
                street, number, city, state, postalCode, country);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) &&
                Objects.equals(number, address.number) &&
                Objects.equals(city, address.city) &&
                Objects.equals(state, address.state) &&
                Objects.equals(postalCode, address.postalCode) &&
                Objects.equals(country, address.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, number, city, state, postalCode, country);
    }
}
