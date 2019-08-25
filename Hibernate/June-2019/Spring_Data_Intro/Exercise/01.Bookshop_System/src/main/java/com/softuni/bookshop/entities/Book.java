package com.softuni.bookshop.entities;

import com.softuni.bookshop.enums.EditionType;
import com.softuni.bookshop.enums.AgeRestriction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int bookId;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "age_restriction", nullable = false)
    private AgeRestriction ageRestriction;

    @Column(nullable = false)
    private int copies;

    @Column
    private String description;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "edition_type", nullable = false)
    private EditionType editionType;

    @Column(precision = 19, scale = 2, nullable = false)
    private BigDecimal price;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "author_id")
    private Author author;

    @ManyToMany
    @JoinTable(name = "books_categories",
        joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id"))
    private Set<Category> categories;

    public Book() {
    }

    public Book(AgeRestriction ageRestriction, int copies, EditionType editionType, BigDecimal price, LocalDate releaseDate, String title, Author author) {
        this.ageRestriction = ageRestriction;
        this.copies = copies;
        this.editionType = editionType;
        this.price = price;
        this.releaseDate = releaseDate;
        this.title = title;
        this.author = author;
        this.categories = new HashSet<>();
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public AgeRestriction getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(AgeRestriction ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EditionType getEditionType() {
        return editionType;
    }

    public void setEditionType(EditionType editionType) {
        this.editionType = editionType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
