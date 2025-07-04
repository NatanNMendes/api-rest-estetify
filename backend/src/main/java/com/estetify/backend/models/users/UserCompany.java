package com.estetify.backend.models.users;

import java.security.Permission;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
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

    //Construtor com parametros básicos
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
        // Limpa a senha anterior se existir
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
        this.endereco = endereco != null ? endereco.trim() : null;
    }

    public Set<Permission> getPermissions() {
        return new HashSet<>(permissions);
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions != null ? new HashSet<>(permissions) : new HashSet<>();
    }

    public Set<Contact> getContacts() {
        return new HashSet<>(contacts);
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts != null ? new HashSet<>(contacts) : new HashSet<>();
    }

    public Boolean isActive() {
        return active;
    }
    //Define o status da empresa
    public void setActive(Boolean active) {
        this.active = active != null ? active : true;
    }
    //Data de resgistro da empresa
    public Date getDateRegister() {
        return DateRegister != null ? new Date(DateRegister.getTime()) : null;
    }

    public void setDateRegister(Date dateRegister) {
        this.DateRegister = dateRegister != null ? new Date(dateRegister.getTime()) : null;
    }

    public Date getLastAccess() {
        return LastAccess != null ? new Date(LastAccess.getTime()) : null;
    }
    //Atualiza a data do último acesso para a data/hora atual.
    public void updateLastAccess() {
        this.LastAccess = new Date();
    }
    // Métodos de negócio
    public String getFormattedCnpj() {
        if (cnpj == null || cnpj.length() != 14) return cnpj;
        return String.format("%s.%s.%s/%s-%s",
                cnpj.substring(0, 2),
                cnpj.substring(2, 5),
                cnpj.substring(5, 8),
                cnpj.substring(8, 12),
                cnpj.substring(12));
    }

    public void addContact(Contact contact) {
        if (contact != null) {
            contacts.add(contact);
        }
    }

    public void removeContact(Contact contact) {
        if (contact != null) {
            contacts.remove(contact);
        }
    }

    public void addPermission(Permission permission) {
        if (permission != null) {
            permissions.add(permission);
        }
    }

    public void removePermission(Permission permission) {
        if (permission != null) {
            permissions.remove(permission);
        }
    }

    // Métodos de segurança
    public void clearPassword() {
        if (password != null) {
            Arrays.fill(password, '0');
            password = null;
        }
    }

    // Representação do objeto
    @Override
    public String toString() {
        return "UserCompany{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", cnpj='" + getFormattedCnpj() + '\'' +
                ", active=" + active +
                '}';
    }

    // Igualdade baseada no ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCompany that = (UserCompany) o;
        return id.equals(that.id);
    }
    //Retorna o hashcod baseado no id
    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
