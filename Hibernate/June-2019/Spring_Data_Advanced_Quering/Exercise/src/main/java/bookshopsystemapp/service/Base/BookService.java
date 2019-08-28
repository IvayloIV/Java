package bookshopsystemapp.service.Base;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface BookService {

    void seedBooks() throws IOException;

    List<String> getAllBooksTitlesAfter();

    Set<String> getAllAuthorsWithBookBefore();

    void printTitlesByAgeRestriction(String ageRestrictionStr);

    void printAllGoldenBooksWithCopiesLessThan(int copiesCount);

    void printTitlesWithPriceBound(BigDecimal lowBound, BigDecimal highBound);

    void printTitlesOfNotReleasedYear(int year);

    void printBooksBeforeDate(String dateStr);

    void printBooksTitleContains(String str);

    void printBooksByAuthorLastNameContains(String prefix);

    void printCountOfBooksWithTitleLongerThan(int count);

    void printBooksByCopiesAuthors();

    void printBookByTitle(String title);

    void increaseBookCopies(String dateStr, int copies);

    void removeBooksByCopiesLessThan(int copies);
}
