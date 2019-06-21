package Custom_List;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CustomList<T extends Comparable<T>> {
    private List<T> list;

    public CustomList() {
        this.list = new ArrayList<>();
    }

    public void add(T element) {
        this.list.add(element);
    }

    public T remove(int index) {
        return this.list.remove(index);
    }

    public boolean contains(T element) {
        return this.list.contains(element);
    }

    public void swap(int index1, int index2) {
        T temp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, temp);
    }

    public int countGreaterThan(T element) {
        int count = 0;

        for (T el : list) {
            if (element.compareTo(el) < 0) {
                count++;
            }
        }

        return count;
    }

    public T getMax() {
        T max = list.get(0);

        for (T element : list) {
            if (element.compareTo(max) > 0) {
                max = element;
            }
        }

        return max;
    }

    public T getMin() {
        T min = list.get(0);

        for (T element : list) {
            if (element.compareTo(min) < 0) {
                min = element;
            }
        }

        return min;
    }

    public void forEach(Consumer<T> consumer) {
        for (T element : list) {
            consumer.accept(element);
        }
    }
}
