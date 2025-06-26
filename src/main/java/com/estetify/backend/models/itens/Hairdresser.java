package com.estetify.backend.models.itens;

public class Hairdresser {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String specialization;
    private Double rating;
    private Company company;

    public Hairdresser() {}

    public Hairdresser(Long id, String name, String phone, String email, String specialization, Double rating, Company company) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.specialization = specialization;
        this.rating = rating;
        this.company = company;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }

    public Company getCompany() { return company; }
    public void setCompany(Company company) { this.company = company; }
}
