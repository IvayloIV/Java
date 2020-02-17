package com.softuni.virus.service;

import com.softuni.virus.domain.entities.Role;
import com.softuni.virus.domain.entities.User;
import com.softuni.virus.domain.models.service.UserServiceModel;
import com.softuni.virus.domain.models.view.RoleUsersViewModel;
import com.softuni.virus.repository.RoleRepository;
import com.softuni.virus.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;



    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public boolean registerUser(UserServiceModel userServiceModel) {
        this.seedDatabaseRoles();

        User user = this.modelMapper.map(userServiceModel, User.class);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        this.generateUserRoles(user);

        try {
            this.userRepository.saveAndFlush(user);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public List<UserServiceModel> getAll() {
        return this.userRepository.findAll()
                .stream()
                .map(u -> this.modelMapper.map(u, UserServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserServiceModel getById(String id) {
        return this.modelMapper
                .map(this.userRepository.findById(id), UserServiceModel.class);
    }

    @Override
    public void changeRole(String userId, String roleId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));
        Role role = this.roleRepository.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("Role not found."));

        if (user.getRoles().stream().anyMatch(r -> r.getId().equals(roleId))) {
            user.getRoles().remove(role);
        } else {
            user.getRoles().add(role);
        }

        this.userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found."));
    }

    private void seedDatabaseRoles() {
        if (this.roleRepository.count() == 0) {
            this.roleRepository.saveAndFlush(new Role("ROOT"));
            this.roleRepository.saveAndFlush(new Role("ADMIN"));
            this.roleRepository.saveAndFlush(new Role("MODERATOR"));
            this.roleRepository.saveAndFlush(new Role("USER"));
        }
    }

    private void generateUserRoles(User user) {
        if (this.userRepository.count() == 0) {
            user.getRoles().add(this.roleRepository.findByAuthority("ROOT"));
            user.getRoles().add(this.roleRepository.findByAuthority("ADMIN"));
            user.getRoles().add(this.roleRepository.findByAuthority("MODERATOR"));
        }
        user.getRoles().add(this.roleRepository.findByAuthority("USER"));
    }
}
