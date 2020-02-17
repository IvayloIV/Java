package com.softuni.virus.service;

import com.softuni.virus.domain.models.service.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    public boolean registerUser(UserServiceModel userServiceModel);

    public List<UserServiceModel> getAll();

    public UserServiceModel getById(String id);

    public void changeRole(String userId, String roleId);
}
