package com.estetify.backend.utils;

public class Address {
    private String zipCode;
    private String country;
    private String state;
    private String city;
    private String street;
    private String number;
    private AddressType addressType;
    private String complement;
    private String neighborhood;
    private String referencePoint;
    private Double latitude;
    private Double longitude;

    public Address(){
        this("","","","","","",null,"","","",0.0,0.0);
    }

    private Address(String zipCode, String country, String state, String city, String street, String number, AddressType addressType, String complement, String neighborhood, String referencePoint, Double latitude, Double longitude) {
        this.zipCode = zipCode;
        this.country = country;
        this.state = state;
        this.city = city;
        this.street = street;
        this.number = number;
        this.addressType = addressType;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.referencePoint = referencePoint;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Address{" +
                "zipCode='" + zipCode + '\'' +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", addressType=" + addressType +
                ", complement='" + complement + '\'' +
                ", neighborhood='" + neighborhood + '\'' +
                ", referencePoint='" + referencePoint + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
