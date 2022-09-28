package spring.demo.bookshopsystem.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import spring.demo.bookshopsystem.models.Author;
import spring.demo.bookshopsystem.models.Book;
import spring.demo.bookshopsystem.models.projection.AuthorsBookCopies;

import java.time.LocalDate;
import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Long> {

    @Query(value = "SELECT concat(b.author.firstName, ' ', b.author.lastName) FROM Book b " +
            " WHERE b.releaseDate < :releaseDate " +
            " GROUP BY b.author " +
            " ORDER BY b.author.firstName")
    public List<String> authorByBooksBeforeReleaseDate(@Param("releaseDate") LocalDate releaseDate);

    @Query("SELECT a FROM Author a " +
            " ORDER BY size(a.books) desc")
    public List<Author> findAllAuthorsByOrderByBooksSizeDesc();

    public List<Author> findAllByFirstNameEndsWith(String firstNameEnd);

    public List<Author> findAllByLastNameStartingWithIgnoreCase(String authorLastName);

    @Query("SELECT a.firstName as firstName," +
            " a.lastName as lastName," +
            " sum(b.copies) as copies " +
            "FROM Author a " +
            "JOIN a.books b " +
            "GROUP BY a.firstName, a.lastName " +
            "ORDER BY sum(b.copies) DESC")
    public List<AuthorsBookCopies> findAllByBookCopies();

//    CREATE PROCEDURE getAuthorsBookCount(IN firstName VARCHAR(255), IN lastName VARCHAR(255), OUT booksCount INT)
//    BEGIN
//    SELECT COUNT(b.id) INTO booksCount FROM authors a
//    JOIN books b on a.id = b.author_id
//    WHERE a.first_name = firstName
//    AND a.last_name = lastName;
//    END;
    @Procedure("getAuthorsBookCount")
    public Integer findBooksCountByAuthorFirstAndLastName(String firstName, String lastName);
}
