package com.softuni.user_system.services.Base;

import com.softuni.user_system.entities.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService {
    void createUser(User user);

    List<String> getAllUserByEmailProvider(String emailProvider);

    List<User> getAllUsersLoginBefore(LocalDate date);

    void setIsDeleteToTrue(List<User> users);

    void removeAllMarkedUsers(List<User> users);
}
