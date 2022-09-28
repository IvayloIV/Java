package spring.demo.bookshopsystem.dao;

import org.springframework.data.repository.CrudRepository;
import spring.demo.bookshopsystem.models.Book;

import java.time.LocalDate;
import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {

    public List<Book> findAllByReleaseDateAfterOrderByReleaseDate(LocalDate localDate);

    public List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitleAsc(String firstName, String lastName);
}
