package com.softuni.user_system.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "towns")
public class Town {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "town_id")
    private int townId;

    @Column
    private String name;

    @Column
    private String country;

    @OneToMany(mappedBy = "bornTown", targetEntity = User.class)
    private Set<User> bornTownUsers;

    @OneToMany(mappedBy = "currentlyLiving", targetEntity = User.class)
    private Set<User> currentlyLivingUsers;

    public Town() {
    }

    public Town(String name, String country) {
        this.name = name;
        this.country = country;
        this.bornTownUsers = new HashSet<>();
        this.currentlyLivingUsers = new HashSet<>();
    }

    public int getTownId() {
        return townId;
    }

    public void setTownId(int townId) {
        this.townId = townId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<User> getBornTownUsers() {
        return bornTownUsers;
    }

    public void setBornTownUsers(Set<User> bornTownUsers) {
        this.bornTownUsers = bornTownUsers;
    }

    public Set<User> getCurrentlyLivingUsers() {
        return currentlyLivingUsers;
    }

    public void setCurrentlyLivingUsers(Set<User> currentlyLivingUsers) {
        this.currentlyLivingUsers = currentlyLivingUsers;
    }
}
