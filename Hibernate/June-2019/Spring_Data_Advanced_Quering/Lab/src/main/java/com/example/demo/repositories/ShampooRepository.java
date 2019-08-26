package com.example.demo.repositories;

import com.example.demo.domain.entities.Ingredient;
import com.example.demo.domain.entities.Label;
import com.example.demo.domain.entities.Shampoo;
import com.example.demo.domain.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {
    List<Shampoo> getAllBySizeOrderById(Size size);

    List<Shampoo> getAllBySizeOrLabelOrderByPriceAsc(Size size, Label label);

    List<Shampoo> getAllByPriceGreaterThanOrderByPriceDesc(BigDecimal price);

    int countAllByPriceLessThan(BigDecimal price);

    @Query(value = "SELECT s.brand FROM Shampoo s " +
            "JOIN s.ingredients i " +
            "WHERE i IN :ingredients")
    List<String> getShampoosWithIngredients(@Param(value = "ingredients") List<Ingredient> ingredients);

    @Query(value = "SELECT s.brand FROM Shampoo s " +
            "JOIN s.ingredients i " +
            "GROUP BY s.id " +
            "HAVING COUNT(i.id) < :count")
    List<String> getShampoosWithIngredientsCountLessThan(long count);

    BigDecimal getShampoosWithTotalIngredientPrice(String brand);
}
