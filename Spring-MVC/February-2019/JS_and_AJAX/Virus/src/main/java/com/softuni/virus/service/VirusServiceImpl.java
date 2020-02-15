package com.softuni.virus.service;

import com.softuni.virus.domain.entities.Virus;
import com.softuni.virus.domain.models.service.CapitalServiceModel;
import com.softuni.virus.domain.models.service.VirusServiceModel;
import com.softuni.virus.repository.CapitalRepository;
import com.softuni.virus.repository.VirusRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VirusServiceImpl implements VirusService {

    private final ModelMapper modelMapper;
    private final VirusRepository virusRepository;
    private final CapitalRepository capitalRepository;

    @Autowired
    public VirusServiceImpl(ModelMapper modelMapper, VirusRepository virusRepository, CapitalRepository capitalRepository) {
        this.modelMapper = modelMapper;
        this.virusRepository = virusRepository;
        this.capitalRepository = capitalRepository;
    }

    @Override
    public boolean save(VirusServiceModel virusServiceModel) {
        try {
            this.setCapitals(virusServiceModel);

            this.virusRepository
                    .save(this.modelMapper.map(virusServiceModel, Virus.class));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<VirusServiceModel> getAll() {
        return this.virusRepository.findAll()
                .stream()
                .map(v -> this.modelMapper.map(v, VirusServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void removeById(String id) {
        this.virusRepository.deleteById(id);
    }

    @Override
    public VirusServiceModel findById(String id) {
        try {
            Virus virus = this.virusRepository.findById(id).get();
            return this.modelMapper.map(virus, VirusServiceModel.class);
        } catch (Exception ex) {
          return null;
        }
    }

    private void setCapitals(VirusServiceModel virusServiceModel) {
        virusServiceModel.setCapitals(virusServiceModel.getCapitals()
                .stream()
                .map(c -> this.modelMapper
                        .map(this.capitalRepository
                                .findById(c.getId()).get(), CapitalServiceModel.class))
                .collect(Collectors.toList()));
    }
}
