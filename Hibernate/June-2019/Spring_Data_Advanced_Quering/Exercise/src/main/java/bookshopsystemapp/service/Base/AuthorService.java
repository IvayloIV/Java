package bookshopsystemapp.service.Base;

import java.io.IOException;

public interface AuthorService {

    void seedAuthors() throws IOException;

    void printAuthorsByFirstNameEndsWith(String suffix);
}
