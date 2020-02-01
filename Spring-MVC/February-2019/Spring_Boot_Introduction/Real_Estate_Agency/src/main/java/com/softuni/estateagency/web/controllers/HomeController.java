package com.softuni.estateagency.web.controllers;

import com.softuni.estateagency.domain.models.views.OfferHomeViewModel;
import com.softuni.estateagency.service.OfferService;
import com.softuni.estateagency.util.HtmlReader;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private ModelMapper modelMapper;
    private OfferService offerService;
    private HtmlReader htmlReader;

    @Autowired
    public HomeController(ModelMapper modelMapper, OfferService offerService, HtmlReader htmlReader) {
        this.modelMapper = modelMapper;
        this.offerService = offerService;
        this.htmlReader = htmlReader;
    }

    @GetMapping("/")
    @ResponseBody
    public String home() throws IOException {
        List<OfferHomeViewModel> offers = this.offerService.getAll()
                .stream()
                .map(o -> this.modelMapper.map(o, OfferHomeViewModel.class))
                .collect(Collectors.toList());

        return this.buildView(offers);
    }

    private String buildView(List<OfferHomeViewModel> offers) throws IOException {
        String html = this.htmlReader.reader("index");
        StringBuilder builder = new StringBuilder();

        if (offers.size() == 0) {
            builder.append("<div id=\"notifications\">There aren't any offers!</div>");
        } else {
            offers.forEach(o -> builder.append("<div class=\"apartment\">\n")
                    .append("<p>Rent:").append(o.getApartmentRent()).append("</p>\n")
                    .append("<p>Type:").append(o.getApartmentType()).append("</p>\n")
                    .append("<p>Commission:").append(o.getAgencyCommission()).append("</p>\n")
                    .append("</div>"));
        }

        return html.replace("{{offers}}", builder.toString().trim());
    }
}
