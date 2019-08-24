package com.soft_uni.account_system;

import com.soft_uni.account_system.models.Account;
import com.soft_uni.account_system.models.User;
import com.soft_uni.account_system.services.Base.AccountService;
import com.soft_uni.account_system.services.Base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private UserService userService;
    private AccountService accountService;

    @Autowired
    public ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User("Penka", 23);
        Account account = new Account(new BigDecimal(25000), user);

        user.getAccounts().add(account);
        this.userService.registerUser(user);

        try {
            this.accountService.withdrawMoney(new BigDecimal(20000), 2L);
//            this.accountService.transferMoney(new BigDecimal(50000), 2L);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
