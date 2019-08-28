package bookshopsystemapp.service;

import bookshopsystemapp.domain.entities.*;
import bookshopsystemapp.repository.AuthorRepository;
import bookshopsystemapp.repository.BookRepository;
import bookshopsystemapp.repository.CategoryRepository;
import bookshopsystemapp.service.Base.BookService;
import bookshopsystemapp.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final static String BOOKS_FILE_PATH = "src/main/resources/files/books.txt";

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final FileUtil fileUtil;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository, FileUtil fileUtil) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedBooks() throws IOException {
        if (this.bookRepository.count() != 0) {
            return;
        }

        String[] booksFileContent = this.fileUtil.getFileContent(BOOKS_FILE_PATH);
        for (String line : booksFileContent) {
            String[] lineParams = line.split("\\s+");

            Book book = new Book();
            book.setAuthor(this.getRandomAuthor());

            EditionType editionType = EditionType.values()[Integer.parseInt(lineParams[0])];
            book.setEditionType(editionType);

            LocalDate releaseDate = LocalDate.parse(lineParams[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
            book.setReleaseDate(releaseDate);

            int copies = Integer.parseInt(lineParams[2]);
            book.setCopies(copies);

            BigDecimal price = new BigDecimal(lineParams[3]);
            book.setPrice(price);

            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(lineParams[4])];
            book.setAgeRestriction(ageRestriction);

            StringBuilder title = new StringBuilder();
            for (int i = 5; i < lineParams.length; i++) {
                title.append(lineParams[i]).append(" ");
            }

            book.setTitle(title.toString().trim());

            Set<Category> categories = this.getRandomCategories();
            book.setCategories(categories);

            this.bookRepository.saveAndFlush(book);
        }
    }

    @Override
    public List<String> getAllBooksTitlesAfter() {
        List<Book> books = this.bookRepository.findAllByReleaseDateAfter(LocalDate.parse("2000-12-31"));

        return books.stream().map(b -> b.getTitle()).collect(Collectors.toList());
    }

    @Override
    public Set<String> getAllAuthorsWithBookBefore() {
        List<Book> books = this.bookRepository.findAllByReleaseDateBefore(LocalDate.parse("1990-01-01"));

        return books.stream().map(b -> String.format("%s %s", b.getAuthor().getFirstName(), b.getAuthor().getLastName())).collect(Collectors.toSet());
    }

    @Override
    public void printTitlesByAgeRestriction(String ageRestrictionStr) {
        AgeRestriction ageRestriction = AgeRestriction.valueOf(ageRestrictionStr.toUpperCase());
        this.bookRepository.findAllByAgeRestriction(ageRestriction)
            .forEach(b -> System.out.println(b.getTitle()));
    }

    @Override
    public void printAllGoldenBooksWithCopiesLessThan(int copiesCount) {
        EditionType goldenType = EditionType.GOLD;
        this.bookRepository.findAllByEditionTypeAndCopiesLessThan(goldenType, copiesCount)
            .forEach(b -> System.out.println(b.getTitle()));
    }

    @Override
    public void printTitlesWithPriceBound(BigDecimal lowBound, BigDecimal highBound) {
        this.bookRepository.findAllByPriceLessThanOrPriceGreaterThan(lowBound, highBound)
            .forEach(b -> System.out.println(b.getTitle() + " - $" + b.getPrice()));
    }

    @Override
    public void printTitlesOfNotReleasedYear(int year) {
        LocalDate dataBefore = LocalDate.of(year, 1, 1);
        LocalDate dataAfter = LocalDate.of(year, 12, 31);
        this.bookRepository.getBooksByReleaseDateBeforeOrReleaseDateAfter(dataBefore, dataAfter)
            .forEach(b -> System.out.println(b.getTitle()));
    }

    @Override
    public void printBooksBeforeDate(String dateStr) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(dateStr, dateFormat);
        this.bookRepository.findAllByReleaseDateBefore(date)
            .forEach(b -> System.out.println(String.format("%s %s %.2f",
                    b.getTitle(),
                    b.getEditionType(),
                    b.getPrice())));
    }

    @Override
    public void printBooksTitleContains(String str) {
        this.bookRepository.findAllByTitleContainingIgnoreCase(str)
            .forEach(b -> System.out.println(b.getTitle()));
    }

    @Override
    public void printBooksByAuthorLastNameContains(String prefix) {
        List<Author> authors = this.authorRepository.findAllByLastNameStartingWithIgnoreCase(prefix);
        this.bookRepository.getAllByAuthors(authors)
                .forEach(a -> System.out.println(String.format("%s (%s)",
                            a.get("title"),
                            a.get("authorName"))));
    }

    @Override
    public void printCountOfBooksWithTitleLongerThan(int count) {
        System.out.println(this.bookRepository.getCountOfBooksWithLengthLongerThan(count));
    }

    @Override
    public void printBooksByCopiesAuthors() {
        this.bookRepository.getBooksCopiesByAuthors()
                .forEach(b -> System.out.println(b.get("authorName") + " - " + b.get("copies")));
    }

    @Override
    public void printBookByTitle(String title) {
        System.out.println(this.bookRepository.getBooksByTitle(title));
    }

    @Override
    public void increaseBookCopies(String dateStr, int copies) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(dateStr, formatter);

        List<Book> books = this.bookRepository.findAllByReleaseDateAfter(date);
        this.bookRepository.increaseCopiesOfBooksAfterReleaseDate(date, copies);
        System.out.println(books.size() * copies);
    }

    @Override
    public void removeBooksByCopiesLessThan(int copies) {
        int countBooks = this.bookRepository.countBooksByCopiesLessThan(copies);
        this.bookRepository.removeBooksWithCopiesLessThan(copies);
        System.out.println("Deleted books count - " + countBooks);
    }

    private Author getRandomAuthor() {
        Random random = new Random();

        int randomId = random.nextInt((int) (this.authorRepository.count())) + 1;

        return this.authorRepository.findById(randomId).orElse(null);
    }

    private Set<Category> getRandomCategories() {
        Set<Category> categories = new LinkedHashSet<>();

        Random random = new Random();
        int length = random.nextInt(5);

        for (int i = 0; i < length; i++) {
            Category category = this.getRandomCategory();

            categories.add(category);
        }

        return categories;
    }

    private Category getRandomCategory() {
        Random random = new Random();

        int randomId = random.nextInt((int) (this.categoryRepository.count() - 1)) + 1;

        return this.categoryRepository.findById(randomId).orElse(null);
    }
}
