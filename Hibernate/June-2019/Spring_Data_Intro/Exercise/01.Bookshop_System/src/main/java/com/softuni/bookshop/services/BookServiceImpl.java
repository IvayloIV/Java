package com.softuni.bookshop.services;

import com.softuni.bookshop.entities.Author;
import com.softuni.bookshop.entities.Book;
import com.softuni.bookshop.entities.Category;
import com.softuni.bookshop.enums.AgeRestriction;
import com.softuni.bookshop.enums.EditionType;
import com.softuni.bookshop.repositories.BookRepository;
import com.softuni.bookshop.services.Base.BookService;
import com.softuni.bookshop.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void registerBook(Book book) {
        this.bookRepository.save(book);
    }

    @Override
    public void createBooks(List<Author> authors, List<Category> categories) throws IOException {
        Random random = new Random();
        List<String> inputs = FileUtil.readFile("books.txt");

        for (String line : inputs) {
            String[] data = line.split("\\s+");
            int authorIndex = random.nextInt(authors.size());
            Author author = authors.get(authorIndex);
            EditionType editionType = EditionType.values()[Integer.parseInt(data[0])];
            LocalDate releaseDate = LocalDate.parse(data[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
            int copies = Integer.parseInt(data[2]);
            BigDecimal price = new BigDecimal(data[3]);
            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(data[4])];
            StringBuilder titleBuilder = new StringBuilder();
            for (int i = 5; i < data.length; i++) {
                titleBuilder.append(data[i]).append(" ");
            }
            titleBuilder.delete(titleBuilder.lastIndexOf(" "), titleBuilder.lastIndexOf(" "));
            String title = titleBuilder.toString();

            Category category = categories.get(random.nextInt(categories.size()));
            Book book = new Book(ageRestriction, copies, editionType, price, releaseDate, title, author);
            book.getCategories().add(category);

            this.registerBook(book);
        }
    }

    @Override
    public List<Book> getAfterYear(LocalDate date) {
        return this.bookRepository.getAllByReleaseDateAfter(date);
    }

    @Override
    public List<String> getAllAuthors(LocalDate data) {
        return this.bookRepository
                .getAllByReleaseDateBefore(data)
                .stream()
                .map(b -> String.format("%s %s",
                        b.getAuthor().getFirstName(),
                        b.getAuthor().getLastName()))
                .collect(Collectors.toList());
    }
}
