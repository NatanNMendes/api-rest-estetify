package com.estetify.backend.models.itens;

import java.util.List;

public class Company {
    private Long id;
    private String name;
    private String tradeName;
    private String taxID;
    private String phone;
    private String email;
    private Address address;
    private String establishmentDate;
    private String operatingHours;
    private List<String> socialMediaLinks;
    private String description;
    private String logo;
    private List<Double> ratings;
    private String legalRepresentative;
    private int numberEmployees;
    private List<Hairdresser> hairdressers;
    private Double revenue;
    private List<String> paymentMethodsAccepted;
    private String status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getTradeName() { return tradeName; }
    public void setTradeName(String tradeName) { this.tradeName = tradeName; }

    public String getTaxID() { return taxID; }
    public void setTaxID(String taxID) { this.taxID = taxID; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    public String getEstablishmentDate() { return establishmentDate; }
    public void setEstablishmentDate(String establishmentDate) { this.establishmentDate = establishmentDate; }

    public String getOperatingHours() { return operatingHours; }
    public void setOperatingHours(String operatingHours) { this.operatingHours = operatingHours; }

    public List<String> getSocialMediaLinks() { return socialMediaLinks; }
    public void setSocialMediaLinks(List<String> socialMediaLinks) { this.socialMediaLinks = socialMediaLinks; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLogo() { return logo; }
    public void setLogo(String logo) { this.logo = logo; }

    public List<Double> getRatings() { return ratings; }
    public void setRatings(List<Double> ratings) { this.ratings = ratings; }

    public String getLegalRepresentative() { return legalRepresentative; }
    public void setLegalRepresentative(String legalRepresentative) { this.legalRepresentative = legalRepresentative; }

    public int getNumberEmployees() { return numberEmployees; }
    public void setNumberEmployees(int numberEmployees) { this.numberEmployees = numberEmployees; }

    public List<Hairdresser> getHairdressers() { return hairdressers; }
    public void setHairdressers(List<Hairdresser> hairdressers) { this.hairdressers = hairdressers; }

    public Double getRevenue() { return revenue; }
    public void setRevenue(Double revenue) { this.revenue = revenue; }

    public List<String> getPaymentMethodsAccepted() { return paymentMethodsAccepted; }
    public void setPaymentMethodsAccepted(List<String> paymentMethodsAccepted) { this.paymentMethodsAccepted = paymentMethodsAccepted; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
