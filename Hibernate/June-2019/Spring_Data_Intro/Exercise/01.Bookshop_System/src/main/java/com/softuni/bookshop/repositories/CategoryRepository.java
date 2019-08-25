package com.softuni.bookshop.repositories;

import com.softuni.bookshop.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
}
