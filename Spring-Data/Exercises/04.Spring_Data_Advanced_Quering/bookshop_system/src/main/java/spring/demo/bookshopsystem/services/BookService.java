package spring.demo.bookshopsystem.services;

public interface BookService {

    public void parseBooks();

    public void printBooksByReleaseDate();

    public void printBooksByAuthor();

    public void printBooksByAgeRestriction();

    public void printBooksByEditionTypeAndCopies();

    public void printBooksByPriceRange();

    public void printBooksByReleaseDateNot();

    public void printBooksByReleaseDateBefore();

    public void printBooksByTitleIgnoreCaseContaining();

    public void printBooksByAuthorLastName();

    public void printBooksCountByTitleLength();

    public void printBooksByTitle();

    public void printIncreasedBooksCopies();

    public void printDeletedBookCountByCopies();
}
