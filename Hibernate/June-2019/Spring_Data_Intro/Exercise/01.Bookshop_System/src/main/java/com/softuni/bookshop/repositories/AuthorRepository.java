package com.softuni.bookshop.repositories;

import com.softuni.bookshop.entities.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    List<Author> findAll();

    Author getAuthorByFirstNameAndLastName(String firstName, String lastName);
}
