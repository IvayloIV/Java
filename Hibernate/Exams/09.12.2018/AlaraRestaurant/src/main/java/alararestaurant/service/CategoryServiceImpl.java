package alararestaurant.service;

import alararestaurant.domain.entities.Category;
import alararestaurant.domain.entities.Item;
import alararestaurant.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public String exportCategoriesByCountOfItems() {
        StringBuilder sb = new StringBuilder();
        List<Category> categories = this.categoryRepository.getByCountOfItems();
        categories.sort((a, b) -> {
            if (a.getItems().size() == b.getItems().size()) {

                BigDecimal aSum = a.getItems()
                        .stream()
                        .map(Item::getPrice)
                        .reduce(BigDecimal::add)
                        .get();

                BigDecimal bSum = b.getItems()
                        .stream()
                        .map(Item::getPrice)
                        .reduce(BigDecimal::add)
                        .get();

                return bSum.compareTo(aSum);
            }

            return 0;
        });

        for (Category category : categories) {
            sb.append("Category: ")
                    .append(category.getName())
                    .append(System.lineSeparator());

            category.getItems().forEach(i ->
                    sb.append("--- Item Name: ")
                        .append(i.getName()).append(System.lineSeparator())
                        .append(String.format("--- Item Price: %.2f%n%n",
                                i.getPrice())));
        }
        return sb.toString().trim();
    }
}
