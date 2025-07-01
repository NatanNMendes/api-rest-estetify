package com.estetify.backend.models.users;

import java.security.Permission;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.regex.Pattern;

public class UserCompany extends Users {
    private Long id;
    private String name;
    private String email;
    private char[] password;
    private String cnpj;
    private String endereco;
    private Set<Permission> permissions;
    private Set<Contact> contacts;
    private Boolean active;
    private Date DateRegister;
    private Date LastAccess;

    //Constructors

    public UserCompany (){
        //Constuctors Default
    }

    public UserCompany(Long Id, String name, String email, String cnpj) {
        this.id = id;
        this.name = name;
        setEmail(email);
        setCnpj(cnpj);
    }



    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if ( id == null || id <= 0){
            throw new IllegalArgumentException("Id não pode ser nulo ou vazio");
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
        }
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (email == null || !Pattern.matches(emailRegex, email)) {
            throw new IllegalArgumentException("Email inválido");
        }
        this.email = email.toLowerCase().trim();
    }

    public char[] getPassword() {
        return password != null ? password.clone() : null;
    }

    public void setPassword(char[] password) {
        if (password == null || password.length < 8) {
            throw new IllegalArgumentException("Senha deve ter pelo menos 8 caracteres");
        }
        if (this.password != null){
            Arrays.fill(this.password, '0');
        }
        this.password = password;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        String cleanedCnpj = cnpj.replaceAll("[^0-9]", "");
        if(cleanedCnpj.length() != 14) {
            throw new IllegalArgumentException("O CNPJ deve conter 14 dígitos");
        }
        this.cnpj = cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getDateRegister() {
        return DateRegister;
    }

    public void setDateRegister(Date dateRegister) {
        this.DateRegister = dateRegister;
    }

    public Date getLastAccess() {
        return LastAccess;
    }

    public void setLastAccess(Date lastAccess) {
        this.LastAccess = lastAccess;
    }

    // Outros métodos úteis

    @Override
    public String toString() {
        return "UserCompany{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", endereco='" + endereco + '\'' +
                ", active=" + active +
                '}';
    }

    // Método para adicionar uma permissão
    public void addPermission(Permission permission) {
        this.permissions.add(permission);
    }

    // Método para remover uma permissão
    public void removePermission(Permission permission) {
        this.permissions.remove(permission);
    }

    // Método para adicionar um contato
    public void addContact(Contact contact) {
        this.contacts.add(contact);
    }

    // Método para remover um contato
    public void removeContact(Contact contact) {
        this.contacts.remove(contact);
    }

}
