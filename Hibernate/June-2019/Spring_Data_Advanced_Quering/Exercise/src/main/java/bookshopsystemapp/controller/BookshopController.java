package bookshopsystemapp.controller;

import bookshopsystemapp.service.Base.AuthorService;
import bookshopsystemapp.service.Base.BookService;
import bookshopsystemapp.service.Base.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class BookshopController implements CommandLineRunner {

    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final BookService bookService;

    @Autowired
    public BookshopController(AuthorService authorService, CategoryService categoryService, BookService bookService) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.bookService.increaseBookCopies("12-08-2005", 100);
    }
}
