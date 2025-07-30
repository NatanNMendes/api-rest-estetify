package com.estetify.backend.models.itens;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false)
    private String tradeName;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String taxID;

    @Pattern(regexp = "\\+?\\d{8,20}")
    private String phone;

    @Email
    private String email;

    @Embedded
    private Address address;

    private LocalDate establishmentDate;

    private String operatingHours;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "company_social_media_links", joinColumns = @JoinColumn(name = "company_id"))
    @Column(name = "link")
    private List<@Size(min = 5) String> socialMediaLinks = new ArrayList<>();

    @Column(length = 1000)
    private String description;

    private String logo;

    @ElementCollection
    @CollectionTable(name = "company_ratings", joinColumns = @JoinColumn(name = "company_id"))
    private List<@DecimalMin("0.0") @DecimalMax("5.0") Double> ratings = new ArrayList<>();

    private String legalRepresentative;

    @PositiveOrZero
    private int numberEmployees;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private List<Hairdresser> hairdressers = new ArrayList<>();

    @PositiveOrZero
    private BigDecimal revenue;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "company_payment_methods", joinColumns = @JoinColumn(name = "company_id"))
    @Column(name = "payment_method")
    private List<@NotBlank String> paymentMethodsAccepted = new ArrayList<>();

    @NotBlank
    private String status;

    // --- Construtores ---
    public Company() {}

    public Company(String name, String tradeName, String taxID) {
        setName(name);
        setTradeName(tradeName);
        setTaxID(taxID);
    }

    // --- Getters e Setters com validações seguras ---

    public Long getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name).trim();
    }

    public String getTradeName() { return tradeName; }

    public void setTradeName(String tradeName) {
        this.tradeName = Objects.requireNonNull(tradeName).trim();
    }

    public String getTaxID() { return taxID; }

    public void setTaxID(String taxID) {
        this.taxID = Objects.requireNonNull(taxID).replaceAll("[^\\d]", "");
    }

    public String getPhone() { return phone; }

    public void setPhone(String phone) {
        this.phone = phone != null ? phone.trim() : null;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) {
        this.email = email != null ? email.trim().toLowerCase() : null;
    }

    public Address getAddress() { return address; }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDate getEstablishmentDate() { return establishmentDate; }

    public void setEstablishmentDate(LocalDate establishmentDate) {
        this.establishmentDate = establishmentDate;
    }

    public String getOperatingHours() { return operatingHours; }

    public void setOperatingHours(String operatingHours) {
        this.operatingHours = operatingHours != null ? operatingHours.trim() : null;
    }

    public List<String> getSocialMediaLinks() {
        return Collections.unmodifiableList(socialMediaLinks);
    }

    public void setSocialMediaLinks(List<String> socialMediaLinks) {
        this.socialMediaLinks = socialMediaLinks != null ? new ArrayList<>(socialMediaLinks) : new ArrayList<>();
    }

    public String getDescription() { return description; }

    public void setDescription(String description) {
        this.description = description != null ? description.trim() : null;
    }

    public String getLogo() { return logo; }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<Double> getRatings() {
        return Collections.unmodifiableList(ratings);
    }

    public void setRatings(List<Double> ratings) {
        this.ratings = ratings != null ? new ArrayList<>(ratings) : new ArrayList<>();
    }

    public String getLegalRepresentative() { return legalRepresentative; }

    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative != null ? legalRepresentative.trim() : null;
    }

    public int getNumberEmployees() { return numberEmployees; }

    public void setNumberEmployees(int numberEmployees) {
        this.numberEmployees = numberEmployees;
    }

    public List<Hairdresser> getHairdressers() {
        return Collections.unmodifiableList(hairdressers);
    }

    public void setHairdressers(List<Hairdresser> hairdressers) {
        this.hairdressers = hairdressers != null ? new ArrayList<>(hairdressers) : new ArrayList<>();
    }

    public BigDecimal getRevenue() { return revenue; }

    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }

    public List<String> getPaymentMethodsAccepted() {
        return Collections.unmodifiableList(paymentMethodsAccepted);
    }

    public void setPaymentMethodsAccepted(List<String> paymentMethodsAccepted) {
        this.paymentMethodsAccepted = paymentMethodsAccepted != null ? new ArrayList<>(paymentMethodsAccepted) : new ArrayList<>();
    }

    public String getStatus() { return status; }

    public void setStatus(String status) {
        this.status = Objects.requireNonNull(status).trim().toUpperCase();
    }

    // --- Métodos utilitários ---

    @Override
    public String toString() {
        return String.format("Company{id=%d, name='%s', tradeName='%s', taxID='%s', email='%s'}",
                id, name, tradeName, taxID, email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company other)) return false;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
