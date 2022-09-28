package spring.demo.accountsystem.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import spring.demo.accountsystem.dao.AccountRepository;
import spring.demo.accountsystem.models.Account;
import spring.demo.accountsystem.services.AccountService;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account findAccount(Long id) {
        return accountRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Account does not exist!"));
    }

    @Override
    public void withdrawMoney(BigDecimal money, Long id) {
        Account account = findAccount(id);

        if (account.getBalance().compareTo(money) < 0) {
            throw new IllegalArgumentException("You cannot withdraw! Your balance is " + account.getBalance());
        }

        account.setBalance(account.getBalance().subtract(money));
        accountRepository.save(account);
    }

    @Override
    public void depositMoney(BigDecimal money, Long id) {
        Account account = findAccount(id);
        account.setBalance(account.getBalance().add(money));
        accountRepository.save(account);
    }

    @Transactional
    @Override
    public void transferMoney(BigDecimal money, Long from, Long to) {
        depositMoney(money, to);
        withdrawMoney(money, from);
    }
}
