package com.estetify.backend.models.users;

import java.security.Permission;
import java.util.Date;
import java.util.Set;

public class UserCompany extends Users {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String cnpj;
    private String endereco;
    private Set<Permission> permissions;
    private Set<Contact> contacts;
    private Boolean active;
    private Date DateRegister;
    private Date LastAccess;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
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
