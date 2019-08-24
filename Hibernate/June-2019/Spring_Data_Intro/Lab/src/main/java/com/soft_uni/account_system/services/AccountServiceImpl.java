package com.soft_uni.account_system.services;

import com.soft_uni.account_system.models.Account;
import com.soft_uni.account_system.repositories.AccountRepository;
import com.soft_uni.account_system.services.Base.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Primary
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal money, Long id) {
        Account account = this.accountRepository.getById(id);
        this.checkAccountExist(account);
        this.checkUserExist(account);

        if (account.getBalance().compareTo(money) < 0) {
            throw new IllegalArgumentException("Account does not have enough money!");
        }

        account.setBalance(account.getBalance().subtract(money));
        this.accountRepository.save(account);
    }

    @Override
    public void transferMoney(BigDecimal money, Long id) {
        Account account = this.accountRepository.getById(id);
        this.checkAccountExist(account);
        this.checkUserExist(account);

        if (BigDecimal.ZERO.compareTo(money) >= 0) {
            throw new IllegalArgumentException("Money cannot be negative!");
        }

        account.setBalance(account.getBalance().add(money));
        this.accountRepository.save(account);
    }

    private void checkAccountExist(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Account does not exist!");
        }
    }

    private void checkUserExist(Account account) {
        if (account.getUser() == null) {
            throw new IllegalArgumentException("Account does not belongs to user!");
        }
    }
}
