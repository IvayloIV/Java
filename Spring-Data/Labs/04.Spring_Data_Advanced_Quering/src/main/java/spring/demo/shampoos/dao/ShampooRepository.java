package spring.demo.shampoos.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import spring.demo.shampoos.models.Shampoo;
import spring.demo.shampoos.models.Size;

import java.util.List;

public interface ShampooRepository extends MainRepository<Shampoo> {

    List<Shampoo> findAllBySizeOrderById(Size size);

    List<Shampoo> findAllBySizeOrLabel_IdOrderByPrice(Size size, Long labelId);

    List<Shampoo> findAllByPriceGreaterThanOrderByPriceDesc(double price);

    Long countAllByPriceLessThan(double price);

    @Query("SELECT s FROM Shampoo s " +
            " JOIN s.ingredients i " +
            " WHERE i.name in :ingredients")
    List<Shampoo> findAllByIngredientsIn(@Param("ingredients") List<String> ingredients);

    @Query("SELECT s FROM Shampoo s " +
            "WHERE size(s.ingredients) < :ingredientCount")
    List<Shampoo> findAllByIngredientCountLessThan(@Param("ingredientCount") int ingredientCount);

    double findIngredientsPriceByBrand(@Param("brand") String brand);
}
