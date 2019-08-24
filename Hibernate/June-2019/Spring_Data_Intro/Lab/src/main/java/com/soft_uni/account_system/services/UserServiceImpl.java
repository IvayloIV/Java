package com.soft_uni.account_system.services;

import com.soft_uni.account_system.models.User;
import com.soft_uni.account_system.repositories.UserRepository;
import com.soft_uni.account_system.services.Base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(User user) {
        User existUser = this.userRepository.getByUsername(user.getUsername());
        if (existUser == null) {
            this.userRepository.save(user);
        }
    }
}
