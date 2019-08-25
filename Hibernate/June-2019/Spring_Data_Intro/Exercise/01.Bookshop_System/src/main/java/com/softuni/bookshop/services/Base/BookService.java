package com.softuni.bookshop.services.Base;

import com.softuni.bookshop.entities.Author;
import com.softuni.bookshop.entities.Book;
import com.softuni.bookshop.entities.Category;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface BookService{
    void registerBook(Book book);

    void createBooks(List<Author> authors, List<Category> categories) throws IOException;

    List<Book> getAfterYear(LocalDate date);

    List<String> getAllAuthors(LocalDate data);
}
