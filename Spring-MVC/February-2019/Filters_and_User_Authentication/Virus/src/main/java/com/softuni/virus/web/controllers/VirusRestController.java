package com.softuni.virus.web.controllers;

import com.softuni.virus.domain.models.view.VirusHomeViewModel;
import com.softuni.virus.service.VirusService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/virus")
public class VirusRestController {

    private final ModelMapper modelMapper;
    private final VirusService virusService;

    @Autowired
    public VirusRestController(ModelMapper modelMapper, VirusService virusService) {
        this.modelMapper = modelMapper;
        this.virusService = virusService;
    }

    @GetMapping("/all")
    public List<VirusHomeViewModel> virusAll() {
        return this.virusService.getAll()
                .stream()
                .map(v -> this.modelMapper.map(v, VirusHomeViewModel.class))
                .collect(Collectors.toList());
    }
}
