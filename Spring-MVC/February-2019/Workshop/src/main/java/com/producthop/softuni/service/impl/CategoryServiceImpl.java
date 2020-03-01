package com.producthop.softuni.service.impl;

import com.producthop.softuni.domain.entities.Category;
import com.producthop.softuni.domain.models.services.CategoryServiceModel;
import com.producthop.softuni.repository.CategoryRepository;
import com.producthop.softuni.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(ModelMapper modelMapper, CategoryRepository categoryRepository) {
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public boolean save(CategoryServiceModel categoryServiceModel) {
        try {
            Category category = this.modelMapper.map(categoryServiceModel, Category.class);
            this.categoryRepository.save(category);
            return true;
        } catch (Exception err) {
            return false;
        }
    }

    @Override
    public List<CategoryServiceModel> getAll() {
        return this.categoryRepository.findAll()
                .stream()
                .map(c -> this.modelMapper.map(c, CategoryServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryServiceModel findById(String id) {
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found."));
        return this.modelMapper.map(category, CategoryServiceModel.class);
    }

    @Override
    public boolean removeById(String id) {
        try {
            this.categoryRepository.deleteById(id);
            return true;
        } catch (Exception err) {
            return false;
        }
    }
}
