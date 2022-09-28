package spring.demo.bookshopsystem.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import spring.demo.bookshopsystem.dao.AuthorRepository;
import spring.demo.bookshopsystem.dao.BookRepository;
import spring.demo.bookshopsystem.dao.CategoryRepository;
import spring.demo.bookshopsystem.enums.AgeRestriction;
import spring.demo.bookshopsystem.enums.EditionType;
import spring.demo.bookshopsystem.models.Author;
import spring.demo.bookshopsystem.models.Book;
import spring.demo.bookshopsystem.models.Category;
import spring.demo.bookshopsystem.services.BookService;
import spring.demo.bookshopsystem.utils.ResourceReader;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {

    private final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final AuthorRepository authorRepository;
    private final ResourceReader resourceReader;

    @Value("classpath:files/books.txt")
    private Resource booksResource;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository,
                           CategoryRepository categoryRepository,
                           AuthorRepository authorRepository,
                           ResourceReader resourceReader) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.authorRepository = authorRepository;
        this.resourceReader = resourceReader;
    }

    @Override
    public void parseBooks() {
        Random random = new Random();
        int authorsSize = new Long(authorRepository.count()).intValue();
        int categoriesSize = new Long(categoryRepository.count()).intValue();

        resourceReader.readResource(booksResource)
            .forEach(bookLine -> {
                String[] data = bookLine.split("\\s+");

                long authorIndex = random.nextInt(authorsSize);
                Author author = authorRepository.findById(authorIndex + 1).orElse(null);
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

                Book book = new Book();
                book.setAuthor(author);
                book.setEditionType(editionType);
                book.setReleaseDate(releaseDate);
                book.setCopies(copies);
                book.setPrice(price);
                book.setAgeRestriction(ageRestriction);
                book.setTitle(title);

                Set<Category> categories = book.getCategories();
                for (int i = 0; i < 3; i++) {
                    int categoryId = random.nextInt(categoriesSize) + 1;
                    Category category = categoryRepository.findById((long) categoryId).orElse(null);
                    categories.add(category);
                }

                bookRepository.save(book);
            });
    }

    @Override
    public void printBooksByReleaseDate() {
        bookRepository
            .findAllByReleaseDateAfterOrderByReleaseDate(LocalDate.of(1999, 12, 31))
            .forEach(b -> logger.info("{} - {}", b.getTitle(), b.getReleaseDate()));
    }

    @Override
    public void printBooksByAuthor() {
        bookRepository
            .findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitleAsc("George", "Powell")
            .forEach(b -> logger.info("{} - {} - {}", b.getTitle(), b.getReleaseDate(), b.getCopies()));
    }

    @Override
    public void printBooksByAgeRestriction() {
        bookRepository
            .findAllByAgeRestriction(AgeRestriction.TEEN)
            .forEach(b -> logger.info("{}", b.getTitle()));
    }

    @Override
    public void printBooksByEditionTypeAndCopies() {
        bookRepository
            .findAllByEditionTypeAndCopiesLessThan(EditionType.GOLD, 5000)
            .forEach(b -> logger.info("{}", b.getTitle()));
    }

    @Override
    public void printBooksByPriceRange() {
        bookRepository
            .findAllByPriceLessThanOrPriceGreaterThanOrderByPrice(new BigDecimal(5), new BigDecimal(40))
            .forEach(b -> logger.info("{} - ${}", b.getTitle(), b.getPrice()));
    }

    @Override
    public void printBooksByReleaseDateNot() {
        final int year = 1998;
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year, 12, 31);

        bookRepository
            .findAllByReleaseDateBeforeOrReleaseDateAfterOrderByReleaseDate(startDate, endDate)
            .forEach(b -> logger.info("{} - {}", b.getTitle(), b.getReleaseDate()));
    }

    @Override
    public void printBooksByReleaseDateBefore() {
        final String releaseDateStr = "30-12-1989";
        LocalDate releaseDate = LocalDate.parse(releaseDateStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        bookRepository
            .findAllByReleaseDateBefore(releaseDate)
            .forEach(b -> logger.info("{} {} {}", b.getTitle(), b.getEditionType().name(), b.getPrice()));
    }

    @Override
    public void printBooksByTitleIgnoreCaseContaining() {
        bookRepository
            .findAllByTitleContainingIgnoreCase("WOR")
            .forEach(b -> logger.info("{}", b.getTitle()));
    }

    @Override
    public void printBooksByAuthorLastName() {
        authorRepository.findAllByLastNameStartingWithIgnoreCase("gr")
            .forEach(a -> a.getBooks()
                .forEach(b -> logger.info("{} ({} {})", b.getTitle(), a.getFirstName(), a.getLastName())));
    }

    @Override
    public void printBooksCountByTitleLength() {
        final int symbols = 40;
        logger.info("There are {} books with longer title than {} symbols",
                bookRepository.countAllByTitleGreaterThan(symbols), symbols);
    }

    @Override
    public void printBooksByTitle() {
        bookRepository.findAllByTitle("Things Fall Apart")
            .forEach(b -> logger.info("{} {} {} {}", b.getTitle(), b.getEditionType(),
                    b.getAgeRestriction(), b.getPrice()));
    }

    @Override
    public void printIncreasedBooksCopies() {
        final String releaseDateStr = "06 Jun 2013";
        final int bookCopies = 44;
        LocalDate releaseDate = LocalDate.parse(releaseDateStr, DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.US));
        int increasedBooksCopies = bookRepository.increaseBooksCopiesAfterReleaseDate(releaseDate, bookCopies);
        logger.info(String.valueOf(increasedBooksCopies * bookCopies));
    }

    @Override
    public void printDeletedBookCountByCopies() {
        logger.info(String.valueOf(bookRepository.deleteAllByCopiesLessThan(10000)));
    }
}
