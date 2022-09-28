package spring.demo.bookshopsystem.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import spring.demo.bookshopsystem.models.Author;

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
}
