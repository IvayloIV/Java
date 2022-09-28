package models.shampoos;

import models.Identity;
import models.ingredients.BasicIngredient;
import models.labels.BasicLabel;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shampoos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "shampoo_type", discriminatorType = DiscriminatorType.STRING)
public abstract class BasicShampoo extends Identity implements Shampoo {

    @Column
    private String brand;

    @Column
    private Double price;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private ShampooSize size;

    @OneToOne(targetEntity = BasicLabel.class, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "labels", referencedColumnName = "id")
    private BasicLabel basicLabel;

    @ManyToMany(targetEntity = BasicIngredient.class, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "shampoos_ingredients",
            joinColumns = @JoinColumn(name = "shampoo_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "id")
    )
    private Set<BasicIngredient> ingredients = new HashSet<BasicIngredient>();

    public BasicShampoo() {
    }

    public BasicShampoo(String brand, Double price, ShampooSize size, BasicLabel basicLabel) {
        this.brand = brand;
        this.price = price;
        this.size = size;
        this.basicLabel = basicLabel;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ShampooSize getSize() {
        return size;
    }

    public void setSize(ShampooSize size) {
        this.size = size;
    }

    public BasicLabel getBasicLabel() {
        return basicLabel;
    }

    public void setBasicLabel(BasicLabel basicLabel) {
        this.basicLabel = basicLabel;
    }

    public Set<BasicIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<BasicIngredient> ingredients) {
        this.ingredients = ingredients;
    }
}
