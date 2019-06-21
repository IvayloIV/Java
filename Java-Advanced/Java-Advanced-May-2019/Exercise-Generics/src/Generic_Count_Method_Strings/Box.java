package Generic_Count_Method_Strings;

import java.util.List;

public class Box<T> {
    private T element;

    public Box(T element) {
        this.element = element;
    }

    public static <T> void swap(List<T> list, int index1, int index2) {
        T temp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, temp);
    }

    public static <T extends Comparable<T>> int compareElements(List<T> element, T compareElement) {
        int count = 0;

        for (T el : element) {
            if (el.compareTo(compareElement) > 0) {
                count++;
            }
        }

        return count;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", this.element.getClass().getName(), this.element);
    }
}
