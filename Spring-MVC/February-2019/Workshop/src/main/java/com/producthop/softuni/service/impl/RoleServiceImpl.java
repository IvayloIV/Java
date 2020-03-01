package com.producthop.softuni.service.impl;

import com.producthop.softuni.domain.entities.Role;
import com.producthop.softuni.domain.models.services.RoleServiceModel;
import com.producthop.softuni.domain.models.services.UserServiceModel;
import com.producthop.softuni.repository.RoleRepository;
import com.producthop.softuni.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(ModelMapper modelMapper, RoleRepository roleRepository) {
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
    }

    @Override
    public void saveRolesToDb() {
        if (this.roleRepository.count() == 0) {
            this.roleRepository.save(new Role("USER"));
            this.roleRepository.save(new Role("MODERATOR"));
            this.roleRepository.save(new Role("ADMIN"));
            this.roleRepository.save(new Role("ROOT"));
        }
    }

    @Override
    public void addUserRoles(UserServiceModel userServiceModel, Long userCount) {
        List<RoleServiceModel> userRoles = userServiceModel.getRoles();

        if (userCount == 0) {
            userRoles.addAll(this.roleRepository.findAll()
                .stream()
                .map(r -> this.modelMapper.map(r, RoleServiceModel.class))
                .collect(Collectors.toList()));
        } else {
            userRoles.add(this.convertRole(this.roleRepository.findByAuthority("USER")));
        }
    }

    @Override
    public List<RoleServiceModel> getAll() {
        return this.roleRepository.findAll()
                .stream()
                .map(r -> this.modelMapper.map(r, RoleServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public RoleServiceModel getById(String roleId) {
        return this.modelMapper
                .map(this.roleRepository.findById(roleId)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid role id!")), RoleServiceModel.class);
    }

    @Override
    public RoleServiceModel getByAuthority(String authority) {
        return this.modelMapper
                .map(this.roleRepository.findByAuthority(authority), RoleServiceModel.class);
    }

    private RoleServiceModel convertRole(Role role) {
        return this.modelMapper.map(role, RoleServiceModel.class);
    }
}
