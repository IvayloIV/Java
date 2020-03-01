package com.producthop.softuni.service.impl;

import com.producthop.softuni.domain.entities.Product;
import com.producthop.softuni.domain.models.services.CategoryServiceModel;
import com.producthop.softuni.domain.models.services.ProductServiceModel;
import com.producthop.softuni.repository.ProductRepository;
import com.producthop.softuni.service.CategoryService;
import com.producthop.softuni.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Autowired
    public ProductServiceImpl(ModelMapper modelMapper, ProductRepository productRepository, CategoryService categoryService) {
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public boolean save(ProductServiceModel productServiceModel) {
        try {
            Product product = this.modelMapper.map(productServiceModel, Product.class);
            this.productRepository.save(product);
            return true;
        } catch (Exception err) {
            return false;
        }
    }

    @Override
    public boolean save(ProductServiceModel productServiceModel, List<String> categoriesId) {
        this.convertCategories(categoriesId, productServiceModel);
        return this.save(productServiceModel);
    }

    @Override
    public List<ProductServiceModel> getAll() {
        return this.productRepository.findAll()
                .stream()
                .map(p -> this.modelMapper.map(p , ProductServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductServiceModel getById(String id) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product id."));
        return this.modelMapper.map(product, ProductServiceModel.class);
    }

    @Override
    public boolean removeById(String id) {
        try {
            this.productRepository.deleteById(id);
            return true;
        } catch (Exception err) {
            return false;
        }
    }

    private void convertCategories(List<String> categoriesId, ProductServiceModel productServiceModel) {
        List<CategoryServiceModel> categories = productServiceModel.getCategories();
        categories.clear();

        categories.addAll(categoriesId
                .stream()
                .map(this.categoryService::findById)
                .collect(Collectors.toList()));
    }
}
