package com.softuni.virus.web.controllers;

import com.softuni.virus.domain.enums.Magnitude;
import com.softuni.virus.domain.enums.VirusMutation;
import com.softuni.virus.domain.models.binding.VirusBindingModel;
import com.softuni.virus.domain.models.service.VirusServiceModel;
import com.softuni.virus.domain.models.view.CapitalCreateVirusViewModel;
import com.softuni.virus.service.CapitalService;
import com.softuni.virus.service.VirusService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/virus")
public class VirusController extends BaseController {

    private List<CapitalCreateVirusViewModel> capitalCreateVirusViewModels;

    private final ModelMapper modelMapper;
    private final VirusService virusService;
    private final CapitalService capitalService;

    @Autowired
    public VirusController(ModelMapper modelMapper, VirusService virusService, CapitalService capitalService) {
        this.modelMapper = modelMapper;
        this.virusService = virusService;
        this.capitalService = capitalService;
    }

    @ModelAttribute("virusBindingModel")
    public VirusBindingModel virusBindingModel() {
        return new VirusBindingModel();
    }

    @ModelAttribute("mutations")
    public List<String> getMutations() {
        return Arrays.stream(VirusMutation.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @ModelAttribute("magnitudes")
    public List<String> getMagnitudes() {
        return Arrays.stream(Magnitude.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @ModelAttribute("capitals")
    public List<CapitalCreateVirusViewModel> getCapitals() {
        if (this.capitalCreateVirusViewModels == null) {
            this.capitalCreateVirusViewModels =  this.capitalService.getAll()
                    .stream()
                    .map(c -> this.modelMapper.map(c, CapitalCreateVirusViewModel.class))
                    .collect(Collectors.toList());
        }
        return this.capitalCreateVirusViewModels;
    }

    @GetMapping("/all")
    public ModelAndView getAll(ModelAndView modelAndView) {
        return super.view("virus/all", modelAndView);
    }

    @GetMapping("/add")
    public ModelAndView addVirus() {
        return super.view("virus/add");
    }

    @PostMapping("/add")
    public ModelAndView addVirusConfirm(@Valid @ModelAttribute("virusBindingModel") VirusBindingModel virusBindingModel,
                                        BindingResult bindingResult,
                                        ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            return super.view("virus/add", modelAndView);
        }

        VirusServiceModel virusModel = this.modelMapper.map(virusBindingModel, VirusServiceModel.class);
        this.setCapitalsId(virusModel, virusBindingModel.getCapitals());
        if (!this.virusService.save(virusModel)) {
            return super.view("virus/add", modelAndView);
        }

        return super.redirect("/virus/all");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id, ModelAndView modelAndView) {
        VirusBindingModel virus = this.modelMapper
                .map(this.virusService.findById(id), VirusBindingModel.class);
        modelAndView.addObject("virusBindingModel", virus);
        return super.view("virus/edit", modelAndView);
    }

    @PostMapping("/edit/{id}")
    public ModelAndView addVirusConfirm(@Valid @ModelAttribute("virusBindingModel") VirusBindingModel virusBindingModel,
                                        BindingResult bindingResult,
                                        ModelAndView modelAndView,
                                        @PathVariable("id") String id) {
        if (bindingResult.hasErrors()) {
            return super.view("virus/edit", modelAndView);
        }

        VirusServiceModel virusModel = this.modelMapper.map(virusBindingModel, VirusServiceModel.class);
        virusModel.setId(id);
        this.setCapitalsId(virusModel, virusBindingModel.getCapitals());
        if (!this.virusService.save(virusModel)) {
            return super.view("virus/edit", modelAndView);
        }

        return super.redirect("/virus/all");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView removeById(@PathVariable("id") String id,
                                   ModelAndView modelAndView) {
        VirusBindingModel virusBindingModel = this.modelMapper
                .map(this.virusService.findById(id), VirusBindingModel.class);
        modelAndView.addObject("virusBindingModel", virusBindingModel);
        return super.view("virus/delete", modelAndView);
    }

    @PostMapping("/delete/{id}")
    public ModelAndView removeByIdConfirm(@PathVariable("id") String id) {
        this.virusService.removeById(id);
        return this.redirect("/virus/all");
    }

    private void setCapitalsId(VirusServiceModel virusModel, List<String> capitals) {
        for (int i = 0; i < virusModel.getCapitals().size(); i++) {
            virusModel.getCapitals().get(i).setId(capitals.get(i));
        }
    }
}
