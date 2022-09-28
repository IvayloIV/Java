package spring.demo.bookshopsystem.models.projection;

import spring.demo.bookshopsystem.enums.AgeRestriction;
import spring.demo.bookshopsystem.enums.EditionType;

import java.math.BigDecimal;

public interface ReducedBook {

    public String getTitle();

    public EditionType getEditionType();

    public AgeRestriction getAgeRestriction();

    public BigDecimal getPrice();
}
