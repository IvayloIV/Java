package com.example.intro.web.controllers;

import com.example.intro.domain.models.OfferFindBindingModel;
import com.example.intro.domain.models.OfferRegisterBindingModel;
import com.example.intro.services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OfferController {
    private final OfferService offerService;

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/register")
    public String register() {
        return "register.html";
    }

    @PostMapping("/register")
    public String registerConfirm(@ModelAttribute(name = "model") OfferRegisterBindingModel offerRegisterBindingModel) {
        try {
            this.offerService.createOffer(offerRegisterBindingModel);
        } catch (IllegalArgumentException e) {
            return "redirect:/register";
        }
        return"redirect:/";
    }

    @GetMapping("/find")
    public String find() {
        return "find.html";
    }

    @PostMapping("/find")
    public String findConfirm(@ModelAttribute(name = "model") OfferFindBindingModel offerFindBindingModel) {
        try {
            this.offerService.findOffer(offerFindBindingModel);
        } catch (IllegalArgumentException e) {
            return "redirect:/find";
        }
        return "redirect:/";
    }
}
