package com.softuni.user_system.services;

import com.softuni.user_system.entities.User;
import com.softuni.user_system.repositories.UserRepository;
import com.softuni.user_system.services.Base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(User user) {
        this.userRepository.save(user);
    }

    @Override
    public List<String> getAllUserByEmailProvider(String emailProvider) {
        return this.userRepository.getUsersByEmailEndingWith(emailProvider)
                .stream()
                .map(u -> String.format("%s %s",
                        u.getUsername(),
                        u.getEmail()))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getAllUsersLoginBefore(LocalDate date) {
        return this.userRepository.getUsersByLastTimeLoggedInBefore(date);
    }

    @Override
    public void setIsDeleteToTrue(List<User> users) {
        for (User user : users) {
            user.setDeleted(true);
            this.userRepository.save(user);
        }
    }

    @Override
    public void removeAllMarkedUsers(List<User> users) {
        users.forEach(this.userRepository::delete);
    }
}
