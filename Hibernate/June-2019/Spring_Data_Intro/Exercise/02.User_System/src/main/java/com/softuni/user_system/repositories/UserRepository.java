package com.softuni.user_system.repositories;

import com.softuni.user_system.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> getUsersByEmailEndingWith(String emailProvider);

    List<User> getUsersByLastTimeLoggedInBefore(LocalDate date);
}
