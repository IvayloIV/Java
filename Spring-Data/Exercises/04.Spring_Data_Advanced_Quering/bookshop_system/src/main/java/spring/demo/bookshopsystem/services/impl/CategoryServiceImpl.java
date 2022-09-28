package spring.demo.bookshopsystem.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import spring.demo.bookshopsystem.dao.CategoryRepository;
import spring.demo.bookshopsystem.models.Category;
import spring.demo.bookshopsystem.services.CategoryService;
import spring.demo.bookshopsystem.utils.ResourceReader;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ResourceReader resourceReader;

    @Value("classpath:files/categories.txt")
    private Resource categoriesResource;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               ResourceReader resourceReader) {
        this.categoryRepository = categoryRepository;
        this.resourceReader = resourceReader;
    }

    @Override
    public void parseCategories() {
        resourceReader.readResource(categoriesResource)
                .forEach(category -> categoryRepository.save(new Category(category)));
    }
}
