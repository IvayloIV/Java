package com.producthop.softuni.web.controllers;

import com.producthop.softuni.domain.models.bindings.category.CategoryCreateBindingModel;
import com.producthop.softuni.domain.models.bindings.category.CategoryEditBindingModel;
import com.producthop.softuni.domain.models.services.CategoryServiceModel;
import com.producthop.softuni.domain.models.views.category.CategoryAllViewModel;
import com.producthop.softuni.domain.models.views.category.CategoryDetailsViewModel;
import com.producthop.softuni.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/category")
public class CategoryController extends BaseController {

    private final ModelMapper modelMapper;
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(ModelMapper modelMapper, CategoryService categoryService) {
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public ModelAndView all(ModelAndView modelAndView) {
        List<CategoryAllViewModel> categories = this.categoryService.getAll()
                .stream()
                .map(c -> this.modelMapper.map(c, CategoryAllViewModel.class))
                .collect(Collectors.toList());

        modelAndView.addObject("categories", categories);
        return super.view("category/all", modelAndView);
    }

    @GetMapping("/create")
    public ModelAndView create() {
        return super.view("category/create");
    }

    @PostMapping("/create")
    public ModelAndView createConfirm(@ModelAttribute CategoryCreateBindingModel model) {
        CategoryServiceModel categoryServiceModel = this.modelMapper
                .map(model, CategoryServiceModel.class);
        boolean isSaved = this.categoryService.save(categoryServiceModel);
        if (!isSaved) {
            return super.redirect("/category/create");
        }

        return super.redirect("/category/all");
    }

    @GetMapping("/edit/{categoryId}")
    public ModelAndView edit(@PathVariable String categoryId,
                             ModelAndView modelAndView) {
        CategoryServiceModel category = this.categoryService.findById(categoryId);
        modelAndView.addObject("category", category);
        return super.view("category/edit", modelAndView);
    }

    @PostMapping("/edit/{categoryId}")
    public ModelAndView editConfirm(@PathVariable String categoryId,
                                    @ModelAttribute CategoryEditBindingModel model) {
        CategoryServiceModel categoryServiceModel = this.modelMapper
                .map(model, CategoryServiceModel.class);
        categoryServiceModel.setId(categoryId);

        boolean isSaved = this.categoryService.save(categoryServiceModel);
        if (!isSaved) {
            return super.redirect("/category/edit/" + categoryId);
        }

        return super.redirect("/category/all");
    }

    @GetMapping("/delete/{categoryId}")
    public ModelAndView delete(@PathVariable String categoryId,
                             ModelAndView modelAndView) {
        CategoryServiceModel category = this.categoryService.findById(categoryId);
        modelAndView.addObject("category", category);
        return super.view("category/delete", modelAndView);
    }

    @PostMapping("/delete/{categoryId}")
    public ModelAndView deleteConfirm(@PathVariable String categoryId) {
        boolean isRemoved = this.categoryService.removeById(categoryId);
        if (!isRemoved) {
            return super.redirect("/category/delete" + categoryId);
        }

        return super.redirect("/category/all");
    }
}
