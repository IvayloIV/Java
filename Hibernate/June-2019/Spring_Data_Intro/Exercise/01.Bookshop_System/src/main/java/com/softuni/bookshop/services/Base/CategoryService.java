package com.softuni.bookshop.services.Base;

import com.softuni.bookshop.entities.Category;

import java.io.IOException;
import java.util.List;

public interface CategoryService {
    void registerCategory(Category category);

    List<Category> createCategories() throws IOException;
}
