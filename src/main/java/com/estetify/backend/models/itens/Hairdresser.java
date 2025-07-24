package com.estetify.backend.models.itens;

import java.util.Objects;

/**
 * Represents a hairdresser who works in a specific company.
 */
public class Hairdresser {

    private Long id;
    private String name;
    private String phone;
    private String email;
    private String specialization;
    private Double rating;
    private Company company;

    // Constructors
    public Hairdresser() {
        // Default constructor
    }

    public Hairdresser(Long id, String name, String phone, String email, String specialization, Double rating, Company company) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.specialization = specialization;
        this.rating = rating;
        this.company = company;
    }

    // Getters and Setters
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
        this.name = name != null ? name.trim() : null;
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
        this.specialization = specialization != null ? specialization.trim() : null;
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
        this.company = company;
    }

    // Equals and HashCode based on ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hairdresser)) return false;
        Hairdresser that = (Hairdresser) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // Optional: toString for debugging/logging
    @Override
    public String toString() {
        return "Hairdresser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", specialization='" + specialization + '\'' +
                ", rating=" + rating +
                ", company=" + (company != null ? company.getName() : "N/A") +
                '}';
    }
}
