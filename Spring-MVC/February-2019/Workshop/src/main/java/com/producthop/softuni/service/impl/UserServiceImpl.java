package com.producthop.softuni.service.impl;

import com.producthop.softuni.domain.entities.User;
import com.producthop.softuni.domain.models.services.RoleServiceModel;
import com.producthop.softuni.domain.models.services.UserServiceModel;
import com.producthop.softuni.repository.UserRepository;
import com.producthop.softuni.service.RoleService;
import com.producthop.softuni.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper,
                           UserRepository userRepository,
                           RoleService roleService,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserServiceModel getByUsername(String username) {
        try {
            User user = this.userRepository.findByUsername(username)
                    .orElseThrow(() -> new IllegalArgumentException("Username does not exist."));
            return this.modelMapper.map(user, UserServiceModel.class);
        } catch (Exception err) {
            return null;
        }
    }

    @Override
    public boolean registerUser(UserServiceModel userServiceModel) {
        this.roleService.saveRolesToDb();
        userServiceModel.setPassword(this.bCryptPasswordEncoder
                .encode(userServiceModel.getPassword()));
        this.roleService.addUserRoles(userServiceModel, this.userRepository.count());

        try {
            User user = this.modelMapper.map(userServiceModel, User.class);
            this.userRepository.save(user);
            return true;
        } catch (Exception err) {
            return false;
        }
    }

    @Override
    public UserServiceModel profileUser(String username) {
        return this.modelMapper
                .map(this.userRepository.findByUsername(username)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid username!")), UserServiceModel.class);
    }

    @Override
    public UserServiceModel getById(String userId) {
        return this.modelMapper
                .map(this.userRepository.findById(userId)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid user id.")), UserServiceModel.class);
    }

    @Override
    public boolean editUser(UserServiceModel userServiceModel, String oldPassword) {
        UserServiceModel user = this.profileUser(userServiceModel.getUsername());

        if (!this.bCryptPasswordEncoder.matches(oldPassword, user.getPassword())) {
            return false;
        }

        user.setPassword(this.bCryptPasswordEncoder.encode(userServiceModel.getPassword()));
        user.setEmail(userServiceModel.getEmail());
        this.userRepository.save(this.modelMapper.map(user, User.class));
        return true;
    }

    @Override
    public List<UserServiceModel> getAll() {
        return this.userRepository.findAll()
                .stream()
                .map(u -> this.modelMapper.map(u, UserServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addRole(String userId, String roleId) {
        UserServiceModel userModel = this.modelMapper.map(this.userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id!")), UserServiceModel.class);

        RoleServiceModel roleModel = this.roleService.getById(roleId);
        userModel.getRoles().add(roleModel);
        this.userRepository.save(this.modelMapper.map(userModel, User.class));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid username."));
    }
}
