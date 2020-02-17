package com.softuni.virus.service;

import com.softuni.virus.domain.models.service.RoleServiceModel;

import java.util.List;

public interface RoleService {

    public List<RoleServiceModel> getAll();

    public RoleServiceModel getById(String id);

    public RoleServiceModel getByAuthority(String authority);

    public RoleServiceModel findByAuthority(String authority);
}
