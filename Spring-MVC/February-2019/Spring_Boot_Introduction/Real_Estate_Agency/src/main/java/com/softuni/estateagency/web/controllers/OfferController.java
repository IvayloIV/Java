package com.softuni.estateagency.web.controllers;

import com.softuni.estateagency.domain.models.bindings.OfferBindingModel;
import com.softuni.estateagency.domain.models.bindings.OfferFindBindingModel;
import com.softuni.estateagency.domain.models.services.OfferServiceModel;
import com.softuni.estateagency.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class OfferController {

    private final ModelMapper modelMapper;
    private final OfferService offerService;

    @Autowired
    public OfferController(ModelMapper modelMapper, OfferService offerService) {
        this.modelMapper = modelMapper;
        this.offerService = offerService;
    }

    @GetMapping("/register")
    public String register() {
        return "register.html";
    }

    @PostMapping("/register")
    public String registerConfirm(@ModelAttribute(name = "model") OfferBindingModel offerBindingModel) {
        OfferServiceModel offerServiceModel = this.modelMapper
                .map(offerBindingModel, OfferServiceModel.class);
        try {
            this.offerService.createOffer(offerServiceModel);
        } catch (IllegalArgumentException err) {
            return "redirect:/register";
        }
        return "redirect:/";
    }

    @GetMapping("/find")
    public String findOffer() {
        return "find.html";
    }

    @PostMapping("/find")
    public String findOfferConfirm(@ModelAttribute(name = "model") OfferFindBindingModel offerFindBindingModel) {
        try {
            this.offerService.removeByTypeAndBudget(offerFindBindingModel);
        } catch (IllegalArgumentException err) {
            return "redirect:/find";
        }

        return "redirect:/";
    }
}
