package mishMash.domain.entities;

import mishMash.domain.enums.Role;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Column(name = "username", unique = true, nullable = false, updatable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @ManyToMany(targetEntity = Channel.class, mappedBy = "followers")
    private List<Channel> followedChannels;

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Channel> getFollowedChannels() {
        return followedChannels;
    }

    public void setFollowedChannels(List<Channel> followedChannels) {
        this.followedChannels = followedChannels;
    }
}
