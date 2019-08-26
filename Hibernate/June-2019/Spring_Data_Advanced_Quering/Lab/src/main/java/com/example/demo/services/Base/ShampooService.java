package com.example.demo.services.Base;

import com.example.demo.domain.entities.Size;

import java.math.BigDecimal;
import java.util.List;

public interface ShampooService {
    void printShampoosBySize(Size size);

    void printShampoosBySizeAndLabel(Size size, Long labelId);

    void printShampoosPriceGreaterThan(BigDecimal price);

    void printShampooCountLessThan(BigDecimal price);

    void printShampoosByIngredients(List<String> names);

    void printShampoosWithIngredientsLessThan(long count);

    void printSumOfPriceOfShampooBrand(String brand);
}
