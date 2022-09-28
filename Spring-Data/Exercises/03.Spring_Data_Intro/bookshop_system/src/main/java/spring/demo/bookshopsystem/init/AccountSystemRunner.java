package spring.demo.bookshopsystem.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import spring.demo.bookshopsystem.services.AuthorService;
import spring.demo.bookshopsystem.services.BookService;
import spring.demo.bookshopsystem.services.CategoryService;

@Component
public class AccountSystemRunner {

    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final BookService bookService;

    @Autowired
    public AccountSystemRunner(AuthorService authorService,
                               CategoryService categoryService,
                               BookService bookService) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void run() {
        authorService.parseAuthors();
        categoryService.parseCategories();
        bookService.parseBooks();

//        bookService.printBooksByReleaseDate();
//        authorService.printAuthorByBooks();
//        authorService.printAuthorByBooksSize();
        bookService.printBooksByAuthor();
    }
}
