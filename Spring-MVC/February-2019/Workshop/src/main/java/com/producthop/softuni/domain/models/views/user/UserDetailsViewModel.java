package com.producthop.softuni.domain.models.views.user;

import java.util.List;

public class UserDetailsViewModel {

    private String id;

    private String username;

    private String email;

    private List<RoleUserViewModel> roles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<RoleUserViewModel> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleUserViewModel> roles) {
        this.roles = roles;
    }
}
