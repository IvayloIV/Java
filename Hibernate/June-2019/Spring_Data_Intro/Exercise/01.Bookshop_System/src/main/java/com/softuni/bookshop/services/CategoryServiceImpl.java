package com.softuni.bookshop.services;

import com.softuni.bookshop.entities.Category;
import com.softuni.bookshop.repositories.CategoryRepository;
import com.softuni.bookshop.services.Base.CategoryService;
import com.softuni.bookshop.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void registerCategory(Category category) {
        this.categoryRepository.save(category);
    }

    @Override
    public List<Category> createCategories() throws IOException {
        List<Category> categories = new ArrayList<>();
        List<String> inputs = FileUtil.readFile("authors.txt");

        for (String line : inputs) {
            String[] data = line.split("\\s+");
            String name = data[0];

            Category category = new Category(name);
            this.registerCategory(category);
            categories.add(category);
        }

        return categories;
    }
}
