package Custom_List_Sorter;

import java.util.Collections;

public class Sorter {
    public static <T extends Comparable<T>> void sort(CustomList<T> elements) {
        Collections.sort(elements.list);
    }
}
