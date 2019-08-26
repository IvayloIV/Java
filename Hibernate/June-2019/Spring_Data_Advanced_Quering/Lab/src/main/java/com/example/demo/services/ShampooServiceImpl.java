package com.example.demo.services;

import com.example.demo.domain.entities.Ingredient;
import com.example.demo.domain.entities.Label;
import com.example.demo.domain.entities.Shampoo;
import com.example.demo.domain.entities.Size;
import com.example.demo.repositories.IngredientRepository;
import com.example.demo.repositories.LabelRepository;
import com.example.demo.repositories.ShampooRepository;
import com.example.demo.services.Base.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShampooServiceImpl implements ShampooService {
    private final ShampooRepository shampooRepository;
    private final LabelRepository labelRepository;
    private final IngredientRepository ingredientRepository;

    @Autowired
    public ShampooServiceImpl(ShampooRepository shampooRepository, LabelRepository labelRepository, IngredientRepository ingredientRepository) {
        this.shampooRepository = shampooRepository;
        this.labelRepository = labelRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void printShampoosBySize(Size size) {
        List<Shampoo> shampoos = this.shampooRepository.getAllBySizeOrderById(size);
        this.printDefaultShampooFormat(shampoos);
    }

    @Override
    public void printShampoosBySizeAndLabel(Size size, Long labelId) {
        Label label = this.labelRepository.findById(labelId)
                .orElseThrow(() -> new IllegalArgumentException("Label not exist."));
        List<Shampoo> shampoos = this.shampooRepository.getAllBySizeOrLabelOrderByPriceAsc(size, label);
        this.printDefaultShampooFormat(shampoos);
    }

    @Override
    public void printShampoosPriceGreaterThan(BigDecimal price) {
        List<Shampoo> shampoos = this.shampooRepository.getAllByPriceGreaterThanOrderByPriceDesc(price);
        this.printDefaultShampooFormat(shampoos);
    }

    @Override
    public void printShampooCountLessThan(BigDecimal price) {
        System.out.println(this.shampooRepository.countAllByPriceLessThan(price));
    }

    @Override
    public void printShampoosByIngredients(List<String> names) {
        List<Ingredient> ingredients = new ArrayList<>();
        names.forEach(n -> ingredients.add(this.ingredientRepository.getByName(n)));

        List<String> shampoos = this.shampooRepository.getShampoosWithIngredients(ingredients);
        shampoos.forEach(System.out::println);
    }

    @Override
    public void printShampoosWithIngredientsLessThan(long count) {
        this.shampooRepository.getShampoosWithIngredientsCountLessThan(count)
                .forEach(System.out::println);
    }

    @Override
    public void printSumOfPriceOfShampooBrand(String brand) {
        System.out.println(this.shampooRepository.getShampoosWithTotalIngredientPrice(brand));
    }

    private void printDefaultShampooFormat(List<Shampoo> shampoos) {
        shampoos.forEach(r -> System.out.println(String.format("%s %s %.2flv.",
                r.getBrand(),
                r.getSize(),
                r.getPrice())));
    }
}
