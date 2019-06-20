package List_Utilities;

import java.util.List;

public class ListUtils<T> {
    public static <T extends Comparable<T> > T getMin(List<T> list) {
        if (list.size() == 0) {
            throw new IllegalArgumentException();
        }

        T minElement = list.get(0);

        for (T element : list) {
            if (minElement.compareTo(element) > 0) {
                minElement = element;
            }
        }

        return minElement;
    }

    public static <T extends Comparable<T> > T getMax(List<T> list) {
        if (list.size() == 0) {
            throw new IllegalArgumentException();
        }

        T minElement = list.get(0);

        for (T element : list) {
            if (minElement.compareTo(element) < 0) {
                minElement = element;
            }
        }

        return minElement;
    }
}
