package com.example.demo.repositories;

import com.example.demo.domain.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedQuery;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> getAllByNameStartingWith(String prefix);

    List<Ingredient> getAllByNameInOrderByPrice(List<String> ingredientsName);

    Ingredient getByName(String name);

    @Transactional
    @Modifying
    void deleteIngredientByName(String name);

    @Transactional
    @Modifying
    void increaseIngredientsPrice(BigDecimal percent);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Ingredient i " +
            "SET i.price = :price " +
            "WHERE i.name IN :names")
    void updateIngredientsPriceByNames(List<String> names, BigDecimal price);
}
