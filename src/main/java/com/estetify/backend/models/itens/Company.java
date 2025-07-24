package com.estetify.backend.models.itens;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String tradeName;

    @NotBlank
    private String taxID;

    @Pattern(regexp = "\\+?\\d+")
    private String phone;

    @Email
    private String email;

    @Embedded
    private Address address;

    private LocalDate establishmentDate;

    private String operatingHours;

    @ElementCollection
    private List<String> socialMediaLinks;

    @Column(length = 1000)
    private String description;

    private String logo;

    @ElementCollection
    private List<Double> ratings;

    private String legalRepresentative;

    private int numberEmployees;

    @OneToMany
    private List<Hairdresser> hairdressers;

    private BigDecimal revenue;

    @ElementCollection
    private List<String> paymentMethodsAccepted;

    private String status;

    // Construtores
    public Company() {}

    public Company(String name, String tradeName, String taxID) {
        this.name = name;
        this.tradeName = tradeName;
        this.taxID = taxID;
    }

    // Getters e Setters
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

    public LocalDate getEstablishmentDate() { return establishmentDate; }
    public void setEstablishmentDate(LocalDate establishmentDate) { this.establishmentDate = establishmentDate; }

    public String getOperatingHours() { return operatingHours; }
    public void setOperatingHours(String operatingHours) { this.operatingHours = operatingHours; }

    public List<String> getSocialMediaLinks() {
        return socialMediaLinks != null ? Collections.unmodifiableList(socialMediaLinks) : List.of();
    }
    public void setSocialMediaLinks(List<String> socialMediaLinks) {
        this.socialMediaLinks = socialMediaLinks;
    }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLogo() { return logo; }
    public void setLogo(String logo) { this.logo = logo; }

    public List<Double> getRatings() {
        return ratings != null ? Collections.unmodifiableList(ratings) : List.of();
    }
    public void setRatings(List<Double> ratings) {
        this.ratings = ratings;
    }

    public String getLegalRepresentative() { return legalRepresentative; }
    public void setLegalRepresentative(String legalRepresentative) { this.legalRepresentative = legalRepresentative; }

    public int getNumberEmployees() { return numberEmployees; }
    public void setNumberEmployees(int numberEmployees) { this.numberEmployees = numberEmployees; }

    public List<Hairdresser> getHairdressers() {
        return hairdressers != null ? Collections.unmodifiableList(hairdressers) : List.of();
    }
    public void setHairdressers(List<Hairdresser> hairdressers) {
        this.hairdressers = hairdressers;
    }

    public BigDecimal getRevenue() { return revenue; }
    public void setRevenue(BigDecimal revenue) { this.revenue = revenue; }

    public List<String> getPaymentMethodsAccepted() {
        return paymentMethodsAccepted != null ? Collections.unmodifiableList(paymentMethodsAccepted) : List.of();
    }
    public void setPaymentMethodsAccepted(List<String> paymentMethodsAccepted) {
        this.paymentMethodsAccepted = paymentMethodsAccepted;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // toString (útil para debug)
    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tradeName='" + tradeName + '\'' +
                ", taxID='" + taxID + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    // equals e hashCode (bons para uso em coleções ou testes)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company company)) return false;
        return Objects.equals(id, company.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
