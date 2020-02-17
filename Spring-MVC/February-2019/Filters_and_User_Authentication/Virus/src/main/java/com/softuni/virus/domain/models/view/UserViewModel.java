package com.softuni.virus.domain.models.view;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UserViewModel {

    private String id;

    private String username;

    private Set<RoleUsersViewModel> roles;

    public UserViewModel() {
        this.roles = new HashSet<>();
    }

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

    public Set<RoleUsersViewModel> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleUsersViewModel> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return String.join(", ",
                this.roles.stream().map(RoleUsersViewModel::getAuthority)
                        .collect(Collectors.toList()));
    }
}
