package Book_Comparator;

import java.util.Comparator;

public class BookComparator implements Comparator<Book> {
    @Override
    public int compare(Book book1, Book book2) {
        if (book1.getTitle().compareTo(book2.getTitle()) != 0) {
            return book1.getTitle().compareTo(book2.getTitle());
        }

        return book1.getYear() - book2.getYear();
    }
}
