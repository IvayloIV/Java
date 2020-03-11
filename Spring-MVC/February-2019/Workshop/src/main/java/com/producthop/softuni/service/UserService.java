package com.producthop.softuni.service;

import com.producthop.softuni.domain.models.services.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    public UserServiceModel getByUsername(String username);

    public boolean registerUser(UserServiceModel userServiceModel);

    public UserServiceModel profileUser(String username);

    public UserServiceModel getById(String userId);

    public boolean editUser(UserServiceModel userServiceModel, String oldPassword);

    public List<UserServiceModel> getAll();

    public void addRole(String userId, String roleId);
}
