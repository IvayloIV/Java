package com.producthop.softuni.domain.models.views.product;

import com.producthop.softuni.domain.models.views.category.CategoryDetailsViewModel;

import java.math.BigDecimal;
import java.util.List;

public class ProductDeleteViewModel {

    private String id;

    private String name;

    private String description;

    private BigDecimal price;

    private List<CategoryDetailsViewModel> categories;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public List<CategoryDetailsViewModel> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDetailsViewModel> categories) {
        this.categories = categories;
    }
}
