package models.ingredients;

import models.shampoos.BasicShampoo;

import java.util.Set;

public interface Ingredient {

    public Long getId();

    public void setId(Long id);

    public String getName();

    public void setName(String name);

    public Double getPrice();

    public void setPrice(Double price);

    public Set<BasicShampoo> getShampoos();

    public void setShampoos(Set<BasicShampoo> shampoos);
}
