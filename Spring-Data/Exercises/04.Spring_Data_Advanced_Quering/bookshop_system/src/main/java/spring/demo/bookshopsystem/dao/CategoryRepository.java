package spring.demo.bookshopsystem.dao;

import org.springframework.data.repository.CrudRepository;
import spring.demo.bookshopsystem.models.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
