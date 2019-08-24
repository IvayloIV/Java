package com.soft_uni.account_system.repositories;

import com.soft_uni.account_system.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User getByUsername(String username);
}
