package com.producthop.softuni.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/product")
public class ProductController extends BaseController {

    @GetMapping("/home")
    public ModelAndView home() {
        return super.view("product/home");
    }
}
