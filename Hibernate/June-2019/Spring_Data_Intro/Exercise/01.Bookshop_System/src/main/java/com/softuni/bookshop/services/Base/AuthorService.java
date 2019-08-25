package com.softuni.bookshop.services.Base;

import com.softuni.bookshop.entities.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void registerAuthor(Author author);

    List<Author> createAuthors() throws IOException;

    List<String> getAllOrderByBooksCount();

    List<String> getBooksByAuthor(String firstName, String lastName);
}
