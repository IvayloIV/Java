package com.producthop.softuni.web.controllers.api;

import com.producthop.softuni.domain.models.services.ProductServiceModel;
import com.producthop.softuni.domain.models.views.product.ProductHomeViewModel;
import com.producthop.softuni.service.CategoryService;
import com.producthop.softuni.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product/api")
public class ProductApiController {

    private final ModelMapper modelMapper;
    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public ProductApiController(ModelMapper modelMapper, ProductService productService, CategoryService categoryService) {
        this.modelMapper = modelMapper;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @ResponseBody
    @GetMapping(value = "/category/{id}", produces = "application/json")
    public List<ProductHomeViewModel> getByCategoryId(@PathVariable String id) {
        List<ProductServiceModel> productsService;
        List<ProductHomeViewModel> products;

        if (id.equals("all")) {
            productsService = this.productService.getAll();
        } else {
            productsService = this.categoryService.findById(id).getProducts();
        }

        products = productsService.stream()
                .map(p -> this.modelMapper.map(p, ProductHomeViewModel.class))
                .collect(Collectors.toList());
        return products;
    }
}
