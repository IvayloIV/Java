package com.softuni.user_system.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 30)
    @Min(4)
    private String username;

    @Column(nullable = false, length = 50)
    @Min(6)
    private String password;

    @Column(nullable = false)
    @Email
    private String email;

    @Column(name = "registered_on")
    private LocalDate registeredOn;

    @Column(name = "last_time_logged_in")
    private LocalDate lastTimeLoggedIn;

    @Column
    @Max(120)
    @Min(1)
    private int age;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "born_town", referencedColumnName = "town_id")
    private Town bornTown;

    @ManyToOne
    @JoinColumn(name = "currently_living", referencedColumnName = "town_id")
    private Town currentlyLiving;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToMany
    @JoinTable(name = "friends",
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id"))
    private Set<User> friends;

    @OneToMany(mappedBy = "owner", targetEntity = Album.class)
    private Set<Album> albums;

    public User() {
    }

    public User(@Min(4) String username, @Min(6) String password, @Email String email, LocalDate registeredOn, LocalDate lastTimeLoggedIn, @Max(120) @Min(1) int age, boolean isDeleted, String firstName, String lastName) {
        this.username = username;
        this.setPassword(password);
        this.email = email;
        this.registeredOn = registeredOn;
        this.lastTimeLoggedIn = lastTimeLoggedIn;
        this.age = age;
        this.isDeleted = isDeleted;
        this.firstName = firstName;
        this.lastName = lastName;
        this.friends = new HashSet<>();
        this.albums = new HashSet<>();
    }

    public void setPassword(String password) {
        if (!password.matches("[a-z]")) {
            throw new IllegalArgumentException("At least one lower letter is required for password!");
        }

        if (!password.matches("[A-Z]]")) {
            throw new IllegalArgumentException("At least one upper letter is required for password!");
        }

        if (!password.matches("[0-9]]")) {
            throw new IllegalArgumentException("At least one digit is required for password!");
        }

        if (!password.matches("[!@#$%^&*()_+<>?]")) {
            throw new IllegalArgumentException("At least one special symbol is required for password!");
        }

        this.password = password;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(LocalDate registeredOn) {
        this.registeredOn = registeredOn;
    }

    public LocalDate getLastTimeLoggedIn() {
        return lastTimeLoggedIn;
    }

    public void setLastTimeLoggedIn(LocalDate lastTimeLoggedIn) {
        this.lastTimeLoggedIn = lastTimeLoggedIn;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Town getBornTown() {
        return bornTown;
    }

    public void setBornTown(Town bornTown) {
        this.bornTown = bornTown;
    }

    public Town getCurrentlyLiving() {
        return currentlyLiving;
    }

    public void setCurrentlyLiving(Town currentlyLiving) {
        this.currentlyLiving = currentlyLiving;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }
}
