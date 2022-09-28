package spring.demo.bookshopsystem.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import spring.demo.bookshopsystem.dao.AuthorRepository;
import spring.demo.bookshopsystem.models.Author;
import spring.demo.bookshopsystem.services.AuthorService;
import spring.demo.bookshopsystem.utils.ResourceReader;

import java.time.LocalDate;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final Logger logger = LoggerFactory.getLogger(AuthorServiceImpl.class);

    private final AuthorRepository authorRepository;
    private final ResourceReader resourceReader;

    @Value("classpath:files/authors.txt")
    private Resource authorsResource;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository,
                             ResourceReader resourceReader) {
        this.authorRepository = authorRepository;
        this.resourceReader = resourceReader;
    }

    @Override
    public void parseAuthors() {
        resourceReader.readResource(authorsResource)
            .forEach(author -> {
                String[] authorNames = author.split("\\s+");
                authorRepository.save(new Author(authorNames[0], authorNames[1]));
            });
    }

    @Override
    public void printAuthorByBooks() {
        authorRepository.authorByBooksBeforeReleaseDate(LocalDate.of(1990, 1, 1))
            .forEach(logger::info);
    }

    @Override
    public void printAuthorByBooksSize() {
        authorRepository.findAllAuthorsByOrderByBooksSizeDesc()
                .forEach(a -> logger.info("{} {} - {}", a.getFirstName(), a.getLastName(), a.getBooks().size()));
    }

    @Override
    public void printAuthorsByFirstNameEndsWith() {
        authorRepository.findAllByFirstNameEndsWith("dy")
            .forEach(a -> logger.info("{} {}", a.getFirstName(), a.getLastName()));
    }

    @Override
    public void printAuthorsByBookCopies() {
        authorRepository.findAllByBookCopies()
            .forEach(a -> logger.info("{} - {}", a.getName(), a.getCopies()));
    }

    @Override
    public void printAuthorsBooksCount() {
        final String firstName = "Wanda";
        final String lastName = "Morales";
        Integer booksCount = authorRepository.findBooksCountByAuthorFirstAndLastName(firstName, lastName);
        logger.info("{} {} has written {} books", firstName, lastName, booksCount);
    }
}
