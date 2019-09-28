package com.essential.exodia.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController extends BaseController {
    @GetMapping("/")
    public ModelAndView index(ModelAndView modelAndView, HttpSession httpSession) {
        if (httpSession.getAttribute("userId") != null) {
            modelAndView.setViewName("redirect:/schedule/home");
            return modelAndView;
        }
        modelAndView.addObject("session", httpSession);
        return super.view("index", modelAndView);
    }
}
