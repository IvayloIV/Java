package com.producthop.softuni.domain.models.services;

import com.producthop.softuni.domain.entities.Product;

import java.util.List;

public class CategoryServiceModel extends BaseServiceModel {

    private String name;

    private List<ProductServiceModel> products;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductServiceModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductServiceModel> products) {
        this.products = products;
    }
}
