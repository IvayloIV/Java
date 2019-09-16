package alararestaurant.repository;

import alararestaurant.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category getByName(String name);

    @Query("SELECT c FROM Category c " +
            "ORDER BY SIZE(c.items) DESC")
    List<Category> getByCountOfItems();
}
