package com.producthop.softuni.domain.models.views.user;

import java.util.Objects;

public class RoleUserViewModel {

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
    public String toString() {
        return this.authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleUserViewModel that = (RoleUserViewModel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(authority, that.authority);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, authority);
    }
}
