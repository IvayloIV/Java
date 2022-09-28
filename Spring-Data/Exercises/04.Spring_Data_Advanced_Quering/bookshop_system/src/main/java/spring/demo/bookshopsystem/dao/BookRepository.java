package spring.demo.bookshopsystem.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import spring.demo.bookshopsystem.enums.AgeRestriction;
import spring.demo.bookshopsystem.enums.EditionType;
import spring.demo.bookshopsystem.models.Book;
import spring.demo.bookshopsystem.models.projection.ReducedBook;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {

    public List<Book> findAllByReleaseDateAfterOrderByReleaseDate(LocalDate localDate);

    public List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitleAsc(String firstName, String lastName);

    public List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    public List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);

    public List<Book> findAllByPriceLessThanOrPriceGreaterThanOrderByPrice(BigDecimal startPrice, BigDecimal endPrice);

    public List<Book> findAllByReleaseDateBeforeOrReleaseDateAfterOrderByReleaseDate(LocalDate startDate, LocalDate endDate);

    public List<Book> findAllByReleaseDateBefore(LocalDate releaseDate);

    public List<Book> findAllByTitleContainingIgnoreCase(String title);

    @Query("SELECT count(b) FROM Book b " +
            "WHERE length(b.title) > :titleLength")
    public long countAllByTitleGreaterThan(@Param("titleLength") int titleLength);

    public List<ReducedBook> findAllByTitle(String title);

    @Modifying
    @Transactional
    @Query("UPDATE Book b " +
            "SET b.copies = b.copies + :copies " +
            "WHERE b.releaseDate > :releaseDate")
    public int increaseBooksCopiesAfterReleaseDate(@Param("releaseDate") LocalDate releaseDate, @Param("copies") int copies);

    @Modifying
    @Transactional
    public int deleteAllByCopiesLessThan(int copies);
}
