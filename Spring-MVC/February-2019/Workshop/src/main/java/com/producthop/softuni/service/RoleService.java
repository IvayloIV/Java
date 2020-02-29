package com.producthop.softuni.service;

import com.producthop.softuni.domain.models.services.RoleServiceModel;
import com.producthop.softuni.domain.models.services.UserServiceModel;

import java.util.List;

public interface RoleService {

    public void saveRolesToDb();

    public void addUserRoles(UserServiceModel userServiceModel, Long userCount);

    public List<RoleServiceModel> getAll();

    public RoleServiceModel getById(String roleId);

    public RoleServiceModel getByAuthority(String authority);
}
