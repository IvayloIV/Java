package com.producthop.softuni.service;

import com.producthop.softuni.domain.models.services.CategoryServiceModel;

import java.util.List;

public interface CategoryService {

    public boolean save(CategoryServiceModel categoryServiceModel);

    public List<CategoryServiceModel> getAll();

    public CategoryServiceModel findById(String id);

    public boolean removeById(String id);
}
