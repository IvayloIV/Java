package casebook.domain.entities;

import casebook.domain.enums.Gender;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Column(name = "username", unique = true, nullable = false, updatable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "gender", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToMany
    @JoinTable(name = "users_friends",
                joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id"))
    private List<User> friends;

    @ManyToMany(mappedBy = "friends")
    private List<User> userFriends;

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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public List<User> getUserFriends() {
        return userFriends;
    }

    public void setUserFriends(List<User> userFriends) {
        this.userFriends = userFriends;
    }
}
