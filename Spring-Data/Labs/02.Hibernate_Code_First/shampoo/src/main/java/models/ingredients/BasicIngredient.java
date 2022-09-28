package models.ingredients;

import models.Identity;
import models.shampoos.BasicShampoo;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ingredients")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ingredient_type")
public abstract class BasicIngredient extends Identity implements Ingredient {

    @Column
    private String name;

    @Column
    private Double price;

    @ManyToMany(mappedBy = "ingredients", targetEntity = BasicShampoo.class)
    private Set<BasicShampoo> shampoos;

    public BasicIngredient() {
    }

    public BasicIngredient(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<BasicShampoo> getShampoos() {
        return shampoos;
    }

    public void setShampoos(Set<BasicShampoo> shampoos) {
        this.shampoos = shampoos;
    }
}
