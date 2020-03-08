package com.producthop.softuni.domain.models.bindings.product;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

public class ProductCreateBindingModel {

    private String name;

    private String description;

    private BigDecimal price;

    private MultipartFile image;

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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public List<String> getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(List<String> categoriesId) {
        this.categoriesId = categoriesId;
    }
}
