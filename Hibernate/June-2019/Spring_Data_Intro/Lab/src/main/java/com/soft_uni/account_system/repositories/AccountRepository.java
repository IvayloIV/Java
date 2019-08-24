package com.soft_uni.account_system.repositories;

import com.soft_uni.account_system.models.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
    Account getById(Long id);
}
