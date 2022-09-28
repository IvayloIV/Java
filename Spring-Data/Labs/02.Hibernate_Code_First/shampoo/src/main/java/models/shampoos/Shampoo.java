package models.shampoos;

import models.ingredients.BasicIngredient;
import models.labels.BasicLabel;

import java.util.Set;

public interface Shampoo {

    public Long getId();

    public void setId(Long id);

    public String getBrand();

    public void setBrand(String brand);

    public Double getPrice();

    public void setPrice(Double price);

    public ShampooSize getSize();

    public void setSize(ShampooSize size);

    public BasicLabel getBasicLabel();

    public void setBasicLabel(BasicLabel basicLabel);

    public Set<BasicIngredient> getIngredients();

    public void setIngredients(Set<BasicIngredient> ingredients);
}
