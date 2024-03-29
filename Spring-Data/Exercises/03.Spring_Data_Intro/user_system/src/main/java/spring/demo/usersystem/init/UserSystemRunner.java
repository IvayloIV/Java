package spring.demo.usersystem.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import spring.demo.usersystem.services.UserService;

@Component
public class UserSystemRunner {

    private final UserService userService;

    @Autowired
    public UserSystemRunner(UserService userService) {
        this.userService = userService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void run() {
        userService.seedUsers();
        userService.setDeleteUserStatus();
        userService.deleteUsers();
    }
}
