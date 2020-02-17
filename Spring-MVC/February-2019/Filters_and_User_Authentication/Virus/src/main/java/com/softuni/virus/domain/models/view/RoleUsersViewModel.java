package com.softuni.virus.domain.models.view;

import java.util.Objects;

public class RoleUsersViewModel implements Comparable<RoleUsersViewModel> {

    private String id;

    private String authority;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleUsersViewModel that = (RoleUsersViewModel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(authority, that.authority);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, authority);
    }

    @Override
    public int compareTo(RoleUsersViewModel o) {
        return this.authority.compareTo(o.authority);
    }
}
