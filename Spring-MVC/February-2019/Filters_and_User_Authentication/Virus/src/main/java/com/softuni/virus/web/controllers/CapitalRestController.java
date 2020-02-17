package com.softuni.virus.web.controllers;

import com.softuni.virus.domain.models.view.CapitalHomeViewModel;
import com.softuni.virus.service.CapitalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/capital")
public class CapitalRestController {

    private final ModelMapper modelMapper;
    private final CapitalService capitalService;

    @Autowired
    public CapitalRestController(ModelMapper modelMapper, CapitalService capitalService) {
        this.modelMapper = modelMapper;
        this.capitalService = capitalService;
    }

    @GetMapping("/all")
    public List<CapitalHomeViewModel> capitalsAll() {
        return this.capitalService.getAll()
                .stream()
                .map(c -> this.modelMapper.map(c, CapitalHomeViewModel.class))
                .collect(Collectors.toList());
    }
}
