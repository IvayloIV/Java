package bookshopsystemapp.repository;

import bookshopsystemapp.domain.contracts.ReducedBook;
import bookshopsystemapp.domain.entities.AgeRestriction;
import bookshopsystemapp.domain.entities.Author;
import bookshopsystemapp.domain.entities.Book;
import bookshopsystemapp.domain.entities.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findAllByReleaseDateAfter(LocalDate date);

    List<Book> findAllByReleaseDateBefore(LocalDate date);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal lowBound, BigDecimal highBound);

    List<Book> getBooksByReleaseDateBeforeOrReleaseDateAfter(LocalDate dateBefore, LocalDate dateAfter);

    List<Book> findAllByTitleContainingIgnoreCase(String str);

    @Query("SELECT b.title AS title, CONCAT(a.firstName, ' ', a.lastName) AS authorName FROM Book b " +
            "INNER JOIN b.author a " +
            "WHERE a IN :authors")
    List<Map<String, String>> getAllByAuthors(List<Author> authors);

    @Query("SELECT COUNT(b.id) FROM Book b " +
            "WHERE LENGTH(b.title) > :lengthTitle")
    int getCountOfBooksWithLengthLongerThan(int lengthTitle);

    @Query("SELECT CONCAT(a.firstName, ' ', a.lastName) AS authorName, SUM(b.copies) AS copies FROM Book b " +
            "INNER JOIN b.author a " +
            "GROUP BY a.firstName, a.lastName " +
            "ORDER BY copies DESC")
    List<Map<String, String>> getBooksCopiesByAuthors();

    ReducedBook getBooksByTitle(String title);

    @Transactional
    @Modifying
    @Query("UPDATE Book b " +
            "SET b.copies = b.copies + :copies " +
            "WHERE b.releaseDate > :date")
    void increaseCopiesOfBooksAfterReleaseDate(LocalDate date, int copies);

    int countBooksByCopiesLessThan(int copies);

    @Transactional
    @Modifying
    @Query("DELETE FROM Book b " +
            "WHERE b.copies < :copies")
    void removeBooksWithCopiesLessThan(int copies);
}
