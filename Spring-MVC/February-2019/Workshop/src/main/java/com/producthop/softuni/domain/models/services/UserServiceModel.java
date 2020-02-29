package com.producthop.softuni.domain.models.services;

import java.util.ArrayList;
import java.util.List;

public class UserServiceModel extends BaseServiceModel {

    private String username;

    private String password;

    private String email;

    private List<RoleServiceModel> roles;

    public UserServiceModel() {
        this.roles = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<RoleServiceModel> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleServiceModel> roles) {
        this.roles = roles;
    }
}
