package com.estetify.backend.models.itens;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Objects;

/**
 * Representa um cabeleireiro vinculado a uma empresa no sistema Estetify.
 */
@Entity
@Table(name = "hairdressers")
public class Hairdresser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Pattern(regexp = "\\+?\\d{8,20}")
    private String phone;

    @Email
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String specialization;

    @DecimalMin(value = "0.0")
    @DecimalMax(value = "5.0")
    private Double rating;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    // --- Construtores ---

    public Hairdresser() {}

    public Hairdresser(Long id, String name, String phone, String email, String specialization, Double rating, Company company) {
        setId(id);
        setName(name);
        setPhone(phone);
        setEmail(email);
        setSpecialization(specialization);
        setRating(rating);
        setCompany(company);
    }

    // --- Getters & Setters com validações internas ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name != null ? name.trim() : null;
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name).trim();
    }

    public String getPhone() {
        return phone != null ? phone.trim() : null;
    }

    public void setPhone(String phone) {
        this.phone = phone != null ? phone.trim() : null;
    }

    public String getEmail() {
        return email != null ? email.trim().toLowerCase() : null;
    }

    public void setEmail(String email) {
        this.email = email != null ? email.trim().toLowerCase() : null;
    }

    public String getSpecialization() {
        return specialization != null ? specialization.trim() : null;
    }

    public void setSpecialization(String specialization) {
        this.specialization = Objects.requireNonNull(specialization).trim();
    }

    public Double getRating() {
        return rating != null ? rating : 0.0;
    }

    public void setRating(Double rating) {
        this.rating = (rating != null && rating >= 0 && rating <= 5) ? rating : 0.0;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = Objects.requireNonNull(company, "Company cannot be null");
    }

    // --- Métodos utilitários ---

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hairdresser that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Hairdresser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", specialization='" + specialization + '\'' +
                ", company=" + (company != null ? company.getName() : "N/A") +
                '}';
    }
}
