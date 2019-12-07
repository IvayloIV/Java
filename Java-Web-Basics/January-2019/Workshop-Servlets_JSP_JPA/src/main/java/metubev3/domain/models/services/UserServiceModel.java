package metubev3.domain.models.services;

import metubev3.domain.enums.UserRole;

import java.util.List;

public class UserServiceModel {
    private String id;
    private String username;
    private String password;
    private String email;
    private UserRole role;
    private List<TubeServiceModel> tubes;

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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public List<TubeServiceModel> getTubes() {
        return tubes;
    }

    public void setTubes(List<TubeServiceModel> tubes) {
        this.tubes = tubes;
    }
}
