package com.softuni.bookshop.services;

import com.softuni.bookshop.entities.Author;
import com.softuni.bookshop.repositories.AuthorRepository;
import com.softuni.bookshop.repositories.BookRepository;
import com.softuni.bookshop.services.Base.AuthorService;
import com.softuni.bookshop.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void registerAuthor(Author author) {
        this.authorRepository.save(author);
    }

    @Override
    public List<Author> createAuthors() throws IOException {
        List<Author> authors = new ArrayList<>();
        List<String> inputs = FileUtil.readFile("authors.txt");

        for (String line : inputs) {
            String[] data = line.split("\\s+");
            String firstName = data[0];
            String lastName = data[1];

            Author author = new Author(firstName, lastName);
            this.registerAuthor(author);
            authors.add(author);
        }

        return authors;
    }

    @Override
    public List<String> getAllOrderByBooksCount() {
        return this.authorRepository.findAll()
                .stream()
                .sorted((a, b) -> b.getBooks().size() - a.getBooks().size())
                .map(a -> String.format("%s %s - %d",
                        a.getFirstName(),
                        a.getLastName(),
                        a.getBooks().size()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getBooksByAuthor(String firstName, String lastName) {
        Author author = this.authorRepository.getAuthorByFirstNameAndLastName(firstName, lastName);
        return this.bookRepository.getAllByAuthorOrderByReleaseDateDescTitleAsc(author)
                .stream()
                .map(a -> String.format("%s %s %s",
                        a.getTitle(),
                        a.getReleaseDate(),
                        a.getCopies()))
                .collect(Collectors.toList());
    }
}
