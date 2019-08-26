package com.example.demo.services.Base;

import com.example.demo.domain.entities.Ingredient;

import java.math.BigDecimal;
import java.util.List;

public interface IngredientService {
    void printIngredientsStartsWith(String prefix);

    void printIngredientsContains(List<String> ingredients);

    Ingredient getIngredientByName(String name);

    void deleteIngredientByName(String name);

    void increaseIngredientsPrice();

    void updateIngredientsPriceByNames(List<String> names, BigDecimal price);
}
