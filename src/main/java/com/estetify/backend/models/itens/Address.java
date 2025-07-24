package com.estetify.backend.models.itens;

import java.util.Objects;

/**
 * Representa um endereço completo com informações de rua, número, cidade, estado, CEP e país.
 */
public class Address {
    private String street;
    private String number;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    /**
     * Construtor padrão.
     */
    public Address() {
        // Construtor vazio necessário para frameworks como JPA/JSON
    }

    /**
     * Construtor completo.
     *
     * @param street     Nome da rua.
     * @param number     Número do imóvel.
     * @param city       Cidade.
     * @param state      Estado.
     * @param postalCode Código postal (CEP).
     * @param country    País.
     */
    public Address(String street, String number, String city, String state, String postalCode, String country) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
    }

    // Getters e Setters
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

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
