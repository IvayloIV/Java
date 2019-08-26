package com.example.demo.services;

import com.example.demo.domain.entities.Ingredient;
import com.example.demo.repositories.IngredientRepository;
import com.example.demo.services.Base.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void printIngredientsStartsWith(String prefix) {
        this.ingredientRepository.getAllByNameStartingWith(prefix)
            .forEach(i -> System.out.println(i.getName()));
    }

    @Override
    public void printIngredientsContains(List<String> ingredientsName) {
        this.ingredientRepository.getAllByNameInOrderByPrice(ingredientsName)
                .forEach(i -> System.out.println(i.getName()));
    }

    @Override
    public Ingredient getIngredientByName(String name) {
        return this.ingredientRepository.getByName(name);
    }

    @Override
    public void deleteIngredientByName(String name) {
        this.ingredientRepository.deleteIngredientByName(name);
    }

    @Override
    public void increaseIngredientsPrice() {
        this.ingredientRepository.increaseIngredientsPrice(BigDecimal.valueOf(1.1));
    }

    @Override
    public void updateIngredientsPriceByNames(List<String> names, BigDecimal price) {
        this.ingredientRepository.updateIngredientsPriceByNames(names, price);
    }
}
