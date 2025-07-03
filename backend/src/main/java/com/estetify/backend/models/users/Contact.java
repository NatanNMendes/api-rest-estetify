package com.estetify.backend.models.users;

public class Contact {
    private String name;
    private String Telephone;
    private String email;
    private String Department;
    private boolean contactMain;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
