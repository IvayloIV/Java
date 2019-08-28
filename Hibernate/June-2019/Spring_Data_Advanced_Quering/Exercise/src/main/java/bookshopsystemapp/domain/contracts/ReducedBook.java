package bookshopsystemapp.domain.contracts;

import bookshopsystemapp.domain.entities.AgeRestriction;
import bookshopsystemapp.domain.entities.EditionType;

import javax.persistence.NamedQuery;
import java.math.BigDecimal;

@NamedQuery(name = "ReducedBook.getBooksByTitle",
    query = "SELECT b.title, b.editionType, b.ageRestriction, b.price " +
            "FROM Book b " +
            "WHERE b.title = :title")
public class ReducedBook {
    private String title;
    private EditionType editionType;
    private AgeRestriction ageRestriction;
    private BigDecimal price;

    public ReducedBook(String title, EditionType editionType, AgeRestriction ageRestriction, BigDecimal price) {
        this.title = title;
        this.editionType = editionType;
        this.ageRestriction = ageRestriction;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %.2f",
                this.title,
                this.editionType,
                this.ageRestriction,
                this.price);
    }
}
