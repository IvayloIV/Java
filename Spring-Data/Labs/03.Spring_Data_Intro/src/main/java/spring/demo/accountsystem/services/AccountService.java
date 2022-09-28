package spring.demo.accountsystem.services;

import spring.demo.accountsystem.models.Account;

import java.math.BigDecimal;

public interface AccountService {

    public Account findAccount(Long id);

    public void withdrawMoney(BigDecimal money, Long id) throws Exception;

    public void depositMoney(BigDecimal money, Long id);

    public void transferMoney(BigDecimal money, Long from, Long to) throws Exception;
}
