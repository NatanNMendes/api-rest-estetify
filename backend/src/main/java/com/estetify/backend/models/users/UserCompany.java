package com.estetify.backend.models.users;

import java.security.Permission;
import java.util.Date;
import java.util.Set;

public class UserCompany extends Users {
    private String id;
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

}
