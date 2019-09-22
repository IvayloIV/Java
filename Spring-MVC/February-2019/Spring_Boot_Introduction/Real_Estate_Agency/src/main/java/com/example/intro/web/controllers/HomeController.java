package com.example.intro.web.controllers;

import com.example.intro.domain.entities.Offer;
import com.example.intro.services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {
    private final OfferService offerService;

    @Autowired
    public HomeController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/")
    public ModelAndView getHomePage(ModelAndView modelAndView) {
        List<Offer> offers = this.offerService.getAllOffers();
        modelAndView.addObject("offers", offers);
        modelAndView.setViewName("index.html");
        return modelAndView;
    }
}
