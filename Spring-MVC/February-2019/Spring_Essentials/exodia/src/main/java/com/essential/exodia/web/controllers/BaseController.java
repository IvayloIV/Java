package com.essential.exodia.web.controllers;

import org.springframework.web.servlet.ModelAndView;

public abstract class BaseController {
    public ModelAndView view(String path, ModelAndView modelAndView) {
        modelAndView.setViewName("fragments/base-layout");
        modelAndView.addObject("view", path);
        return modelAndView;
    }

    public String redirect(String path) {
        return "redirect:" + path;
    }
}
