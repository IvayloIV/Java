package mishMash.domain.models.services;

import mishMash.domain.enums.Role;

import java.util.List;

public class UserServiceModel {

    private String id;

    private String username;

    private String password;

    private String email;

    private Role role;

    private List<ChannelServiceModel> followedChannels;

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<ChannelServiceModel> getFollowedChannels() {
        return followedChannels;
    }

    public void setFollowedChannels(List<ChannelServiceModel> followedChannels) {
        this.followedChannels = followedChannels;
    }
}
