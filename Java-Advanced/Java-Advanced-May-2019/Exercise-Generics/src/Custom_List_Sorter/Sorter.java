package Custom_List_Sorter;

public class Sorter {
    public static <T extends Comparable<T>> void sort(CustomList<T> elements) {
        for (int i = 0; i < elements.getSize() - 1; i++) {
            for (int j = 0; j < elements.getSize() - i - 1; j++) {
                if (elements.get(j).compareTo(elements.get(j + 1)) > 0) {
                    elements.swap(j, j + 1);
                }
            }
        }
    }
}
