package com.softuni.virus.service;

import com.softuni.virus.domain.models.service.RoleServiceModel;
import com.softuni.virus.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private ModelMapper modelMapper;
    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(ModelMapper modelMapper, RoleRepository roleRepository) {
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<RoleServiceModel> getAll() {
        return this.roleRepository.findAll()
                .stream()
                .map(r -> this.modelMapper.map(r, RoleServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public RoleServiceModel getById(String id) {
        return this.modelMapper
                .map(this.roleRepository.findById(id), RoleServiceModel.class);
    }

    @Override
    public RoleServiceModel getByAuthority(String authority) {
        return this.modelMapper
                .map(this.roleRepository.findByAuthority(authority), RoleServiceModel.class);
    }

    @Override
    public RoleServiceModel findByAuthority(String authority) {
        return this.modelMapper
                .map(this.roleRepository.findByAuthority(authority), RoleServiceModel.class);
    }
}
