package spring.demo.accountsystem.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import spring.demo.accountsystem.models.Account;
import spring.demo.accountsystem.models.User;
import spring.demo.accountsystem.services.AccountService;
import spring.demo.accountsystem.services.UserService;

import java.math.BigDecimal;

@Component
public class AccountSystemRunner {

    private final Logger logger = LoggerFactory.getLogger(AccountSystemRunner.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @EventListener(ApplicationReadyEvent.class)
    public void run() throws Exception {
        User user = new User();
        user.setUsername("Pesho");
        user.setAge(45);

        Account account1 = new Account(new BigDecimal(1000));
        user.getAccounts().add(account1);
        account1.setUser(user);

        Account account2 = new Account(new BigDecimal(500));
        user.getAccounts().add(account2);
        account2.setUser(user);

        userService.registerUser(user);

        accountService.depositMoney(new BigDecimal(500), account1.getId());
        logger.info(accountService.findAccount(account1.getId()).getBalance().toPlainString());

        accountService.withdrawMoney(new BigDecimal(100), account1.getId());
        logger.info(accountService.findAccount(account1.getId()).getBalance().toPlainString());

        accountService.transferMoney(new BigDecimal(500), account1.getId(), account2.getId());
        logger.info("Account1 balance = {}", accountService.findAccount(account1.getId()).getBalance().toPlainString());
        logger.info("Account2 balance = {}", accountService.findAccount(account2.getId()).getBalance().toPlainString());
    }
}
