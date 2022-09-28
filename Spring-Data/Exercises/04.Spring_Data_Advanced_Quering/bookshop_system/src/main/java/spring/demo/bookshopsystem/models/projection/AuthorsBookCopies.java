package spring.demo.bookshopsystem.models.projection;

import org.springframework.beans.factory.annotation.Value;

public interface AuthorsBookCopies {

    @Value("#{target.firstName + ' ' + target.lastName}")
    public String getName();

    public long getCopies();
}
