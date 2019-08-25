package com.softuni.bookshop;

import com.softuni.bookshop.entities.Author;
import com.softuni.bookshop.entities.Category;
import com.softuni.bookshop.services.Base.AuthorService;
import com.softuni.bookshop.services.Base.BookService;
import com.softuni.bookshop.services.Base.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Component
@Transactional
public class ConsoleRunner implements CommandLineRunner {
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final BookService bookService;

    @Autowired
    public ConsoleRunner(AuthorService authorService, CategoryService categoryService, BookService bookService) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedDatabase();
    }

    private void printBooksByAuthor(String firstName, String lastName) {
        this.authorService.getBooksByAuthor(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAuthorsByBooksCount() {
        this.authorService.getAllOrderByBooksCount()
                .forEach(System.out::println);
    }

    private void printAuthorsBefore1990Book() {
        LocalDate data = LocalDate.of(1990, 1, 1);
        this.bookService.getAllAuthors(data).forEach(System.out::println);
    }

    private void printBooksAfter2000Year() {
        this.bookService.getAfterYear(LocalDate.of(1999, 12, 31))
                .forEach(b -> System.out.println(b.getTitle()));
    }

    private void seedDatabase() throws IOException {
        List<Author> authors = this.authorService.createAuthors();
        List<Category> categories = this.categoryService.createCategories();
        this.bookService.createBooks(authors, categories);
    }
}
