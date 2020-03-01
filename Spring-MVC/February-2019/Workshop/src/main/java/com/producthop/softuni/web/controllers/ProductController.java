package com.producthop.softuni.web.controllers;

import com.producthop.softuni.domain.models.bindings.product.ProductCreateBindingModel;
import com.producthop.softuni.domain.models.bindings.product.ProductEditBindingModel;
import com.producthop.softuni.domain.models.services.CategoryServiceModel;
import com.producthop.softuni.domain.models.services.ProductServiceModel;
import com.producthop.softuni.domain.models.views.category.CategoryAllViewModel;
import com.producthop.softuni.domain.models.views.category.CategoryDetailsViewModel;
import com.producthop.softuni.domain.models.views.product.ProductAllViewModel;
import com.producthop.softuni.domain.models.views.product.ProductDetailsViewModel;
import com.producthop.softuni.domain.models.views.product.ProductEditViewModel;
import com.producthop.softuni.service.CategoryService;
import com.producthop.softuni.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/product")
public class ProductController extends BaseController {

    private final ModelMapper modelMapper;
    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public ProductController(ModelMapper modelMapper, ProductService productService, CategoryService categoryService) {
        this.modelMapper = modelMapper;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/home")
    public ModelAndView home(ModelAndView modelAndView) {
        List<CategoryAllViewModel> categories = this.getCategories();

        modelAndView.addObject("categories", categories);
        return super.view("product/home", modelAndView);
    }

    @GetMapping("/all")
    public ModelAndView all(ModelAndView modelAndView) {
        List<ProductAllViewModel> products = this.productService.getAll()
                .stream()
                .map(p -> this.modelMapper.map(p, ProductAllViewModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject("products", products);
        return super.view("product/all", modelAndView);
    }

    @GetMapping("/create")
    public ModelAndView create(ModelAndView modelAndView) {
        List<CategoryAllViewModel> categoriesView = this.getCategories();

        modelAndView.addObject("categories", categoriesView);
        return super.view("product/create", modelAndView);
    }

    @PostMapping("/create")
    public ModelAndView createConfirm(@ModelAttribute ProductCreateBindingModel model) {
        ProductServiceModel productServiceModel = this.modelMapper.map(model, ProductServiceModel.class);
        List<String> categoriesId = model.getCategoriesId();

        boolean isSaved = this.productService.save(productServiceModel, categoriesId);
        if (!isSaved) {
            return super.redirect("/product/create");
        }
        return super.redirect("/product/all");
    }

    @GetMapping("/details/{productId}")
    public ModelAndView details(@PathVariable String productId,
                                ModelAndView modelAndView) {
        ProductServiceModel product = this.productService.getById(productId);
        ProductDetailsViewModel productView = this.modelMapper
                .map(product, ProductDetailsViewModel.class);

        modelAndView.addObject("product", productView);
        return super.view("product/details", modelAndView);
    }

    @GetMapping("/edit/{productId}")
    public ModelAndView edit(@PathVariable String productId,
                                ModelAndView modelAndView) {
        ProductServiceModel product = this.productService.getById(productId);
        ProductEditViewModel productView = this.modelMapper
                .map(product, ProductEditViewModel.class);

        List<CategoryDetailsViewModel> categoriesView = this.categoryService.getAll()
                .stream()
                .map(c -> this.modelMapper.map(c, CategoryDetailsViewModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject("product", productView);
        modelAndView.addObject("categories", categoriesView);
        return super.view("product/edit", modelAndView);
    }

    @PostMapping("/edit/{productId}")
    public ModelAndView editConfirm(@ModelAttribute ProductEditBindingModel model,
                                    @PathVariable String productId) {
        ProductServiceModel productServiceModel = this.modelMapper.map(model, ProductServiceModel.class);
        List<String> categoriesId = model.getCategoriesId();
        productServiceModel.setId(productId);

        boolean isEdited = this.productService.save(productServiceModel, categoriesId);
        if (!isEdited) {
            return super.redirect("/product/edit/" + productId);
        }
        return super.redirect("/product/all");
    }

    @GetMapping("/delete/{productId}")
    public ModelAndView delete(@PathVariable String productId,
                             ModelAndView modelAndView) {
        ProductServiceModel product = this.productService.getById(productId);
        ProductEditViewModel productView = this.modelMapper
                .map(product, ProductEditViewModel.class);

        modelAndView.addObject("product", productView);
        return super.view("product/delete", modelAndView);
    }

    @PostMapping("/delete/{productId}")
    public ModelAndView deleteConfirm(@PathVariable String productId) {
        boolean isDeleted = this.productService.removeById(productId);
        if (!isDeleted) {
            return super.redirect("/product/delete/" + productId);
        }
        return super.redirect("/product/all");
    }

    private List<CategoryAllViewModel> getCategories() {
        return this.categoryService.getAll()
                .stream()
                .map(c -> this.modelMapper.map(c, CategoryAllViewModel.class))
                .collect(Collectors.toList());
    }
}
