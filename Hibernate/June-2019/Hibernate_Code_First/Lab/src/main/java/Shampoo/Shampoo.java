package Shampoo;

import Ingredient.BasicIngredient;
import Label.BasicLabel;
import Label.Size;

import java.math.BigDecimal;
import java.util.Set;

public interface Shampoo {
    int getId();

    void setId(int id);

    String getBrand();

    void setBrand(String brand);

    BigDecimal getPrice();

    void setPrice(BigDecimal price);

    Size getSize();

    void setSize(Size size);

    BasicLabel getLabel();

    void setLabel(BasicLabel label);

    Set<BasicIngredient> getIngredients();

    void setIngredients(BasicIngredient ingredients);
}
