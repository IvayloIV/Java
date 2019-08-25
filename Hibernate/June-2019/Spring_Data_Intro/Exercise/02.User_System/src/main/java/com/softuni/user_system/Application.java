package com.softuni.user_system;

import com.softuni.user_system.entities.User;
import com.softuni.user_system.services.Base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class Application implements CommandLineRunner {
    private final UserService userService;

    @Autowired
    public Application(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.removeInactiveUsers(LocalDate.of(2033, 1, 1));
    }

    private void printUserByEmailProvider(String emailProvider) {
        List<String> users = this.userService.getAllUserByEmailProvider(emailProvider);
        if (users.isEmpty()) {
            System.out.println("No users found with email domain " + emailProvider);
        }

        users.forEach(System.out::println);
    }

    private void removeInactiveUsers(LocalDate date) {
        List<User> users = this.userService.getAllUsersLoginBefore(date);

        if (users.isEmpty()) {
            System.out.println("No users have been deleted");
        } else {
            this.userService.setIsDeleteToTrue(users);
            this.userService.removeAllMarkedUsers(users);
            System.out.println(users.size() + " user has been deleted");
        }
    }
}
