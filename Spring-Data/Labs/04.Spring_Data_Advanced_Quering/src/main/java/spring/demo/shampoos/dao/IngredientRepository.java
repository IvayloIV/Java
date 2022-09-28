package spring.demo.shampoos.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import spring.demo.shampoos.models.Ingredient;

import java.util.List;

public interface IngredientRepository extends MainRepository<Ingredient>, CustomRepository  {

    List<Ingredient> findAllByNameStartsWith(String startText);

    List<Ingredient> findAllByNameInOrderByPrice(Iterable<String> ingredients);

    @Modifying
    @Transactional
    @Query("DELETE from Ingredient i " +
            " WHERE i.name = :name")
    void deleteByName(String name);

    @Modifying
    @Transactional
    void increasePrice(@Param("percentage") Double percentage);

    @Modifying
    @Transactional
    @Query("UPDATE Ingredient i " +
            " SET i.price = i.price + 100 " +
            " WHERE i.name in :names")
    void increasePriceWith100ByNameIn(@Param("names") Iterable<String> names);

    List<Ingredient> findTop7ByOrderByIdDesc();
}
