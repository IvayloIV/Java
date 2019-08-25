package com.softuni.bookshop.repositories;

import com.softuni.bookshop.entities.Author;
import com.softuni.bookshop.entities.Book;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookRepository extends CrudRepository<Book, Integer> {
    List<Book> getAllByReleaseDateAfter(LocalDate date);

    List<Book> getAllByReleaseDateBefore(LocalDate data);

    List<Book> getAllByAuthorOrderByReleaseDateDescTitleAsc(Author author);
}
