package com.producthop.softuni.domain.models.bindings.product;

import java.math.BigDecimal;
import java.util.List;

public class ProductEditBindingModel {

    private String name;

    private String description;

    private BigDecimal price;

    private List<String> categoriesId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<String> getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(List<String> categoriesId) {
        this.categoriesId = categoriesId;
    }
}
