package com.game.store.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "games")
public class Game extends BaseEntity {
    @Column
    @Pattern(regexp = "[A-Z][A-Za-z0-9]{2,99}", message = "Invalid title.")
    private String title;

    @Column
    @Size(min = 11, max = 11, message = "Trailer need to be exact 11 characters.")
    private String trailer;

    @Column(name = "image_thumbnail")
    @Pattern(regexp = "^(http:\\/\\/|https:\\/\\/).*$", message = "Invalid thumbnail.")
    private String imageThumbnail;

    @Column(precision = 19, scale = 1)
    @Min(1)
    private Double size;

    @Column(precision = 19, scale = 2)
    @Min(1)
    private BigDecimal price;

    @Column
    @Size(min = 20)
    private String description;

    @Column
    private LocalDate releaseDate;

    @ManyToMany(mappedBy = "games", targetEntity = User.class)
    private Set<User> users;

    public Game() {
    }

    public Game(String title, String trailer, String imageThumbnail, Double size, BigDecimal price, String description, LocalDate releaseDate) {
        this.title = title;
        this.trailer = trailer;
        this.imageThumbnail = imageThumbnail;
        this.size = size;
        this.price = price;
        this.description = description;
        this.releaseDate = releaseDate;
        this.users = new HashSet<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getImageThumbnail() {
        return imageThumbnail;
    }

    public void setImageThumbnail(String imageThumbnail) {
        this.imageThumbnail = imageThumbnail;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return String.format("Title: %s\n" +
                "Price: %.2f\n" +
                "Description: %s\n" +
                "Release date: %s\n",
                    this.getTitle(),
                    this.getPrice(),
                    this.getDescription(),
                    this.getReleaseDate());
    }
}
