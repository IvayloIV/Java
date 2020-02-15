package com.softuni.virus.service;

import com.softuni.virus.domain.models.service.CapitalServiceModel;
import com.softuni.virus.repository.CapitalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CapitalServiceImpl implements CapitalService {

    private final ModelMapper modelMapper;
    private final CapitalRepository capitalRepository;

    @Autowired
    public CapitalServiceImpl(ModelMapper modelMapper, CapitalRepository capitalRepository) {
        this.modelMapper = modelMapper;
        this.capitalRepository = capitalRepository;
    }

    @Override
    public List<CapitalServiceModel> getAll() {
        return this.capitalRepository.findAll()
                .stream()
                .map(c -> this.modelMapper.map(c, CapitalServiceModel.class))
                .collect(Collectors.toList());
    }
}
