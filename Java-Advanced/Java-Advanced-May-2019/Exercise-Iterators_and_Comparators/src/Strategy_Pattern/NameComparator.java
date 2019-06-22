package Strategy_Pattern;

import java.util.Comparator;

public class NameComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        if (p1.getName().length() != p2.getName().length()) {
            return p1.getName().length() - p2.getName().length();
        }

        String firstLetterP1 = String.valueOf(p1.getName().toLowerCase().charAt(0));
        String firstLetterP2 = String.valueOf(p2.getName().toLowerCase().charAt(0));
        return firstLetterP1.compareTo(firstLetterP2);
    }
}
