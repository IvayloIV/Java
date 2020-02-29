package com.producthop.softuni.web.controllers;

import org.springframework.web.servlet.ModelAndView;

public abstract class BaseController {

    protected ModelAndView view(String file, ModelAndView modelAndView) {
        modelAndView.addObject("view", file);
        modelAndView.setViewName("fragments/base-layout");
        return modelAndView;
    }

    protected ModelAndView view(String file) {
        return this.view(file, new ModelAndView());
    }

    protected ModelAndView redirect(String path, ModelAndView modelAndView) {
        modelAndView.setViewName("redirect:" + path);
        return modelAndView;
    }

    protected ModelAndView redirect(String path) {
        return this.redirect(path, new ModelAndView());
    }
}
