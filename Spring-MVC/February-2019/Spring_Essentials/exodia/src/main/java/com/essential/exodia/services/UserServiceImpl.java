package com.essential.exodia.services;

import com.essential.exodia.domain.entites.User;
import com.essential.exodia.domain.models.UserLoginBindingModel;
import com.essential.exodia.domain.models.UserRegisterBindingModel;
import com.essential.exodia.repositories.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerUser(UserRegisterBindingModel userRegisterBindingModel) {
        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords must match!");
        }

        User user = this.modelMapper.map(userRegisterBindingModel, User.class);
        user.setPassword(DigestUtils.sha256Hex(userRegisterBindingModel.getPassword()));
        this.userRepository.save(user);
    }

    @Override
    public String loginUser(UserLoginBindingModel userLoginBindingModel) {
        String username = userLoginBindingModel.getUsername();
        User user = this.userRepository.findByUsername(username);

        if (user == null || !user.getPassword().equals(DigestUtils.sha256Hex(userLoginBindingModel.getPassword()))) {
            throw new IllegalArgumentException("Invalid user.");
        }

        return user.getId();
    }
}
