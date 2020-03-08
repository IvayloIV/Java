package com.producthop.softuni.service;

import com.producthop.softuni.domain.models.services.ProductServiceModel;

import java.util.List;

public interface ProductService {

    public ProductServiceModel save(ProductServiceModel productServiceModel);

    public ProductServiceModel save(ProductServiceModel productServiceModel, List<String> categoriesId);

    public List<ProductServiceModel> getAll();

    public ProductServiceModel getById(String id);

    public boolean removeById(String id);
}
