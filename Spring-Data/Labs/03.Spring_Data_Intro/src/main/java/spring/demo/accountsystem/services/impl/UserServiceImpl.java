package spring.demo.accountsystem.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.demo.accountsystem.models.User;
import spring.demo.accountsystem.dao.UserRepository;
import spring.demo.accountsystem.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public void registerUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("User has already registered.");
        }

        userRepository.save(user);
    }
}
