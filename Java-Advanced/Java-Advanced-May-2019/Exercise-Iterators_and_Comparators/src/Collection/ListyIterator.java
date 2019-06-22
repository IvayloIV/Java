package Collection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class ListyIterator implements Iterable<String> {
    private List<String> data;
    private int index;

    public ListyIterator(String... data) {
        this.data = Arrays.asList(data);
    }

    public boolean move() {
        if (!this.hasNext()) {
            return false;
        }

        this.index++;
        return true;
    }

    public boolean hasNext() {
        return this.index < this.data.size() - 1;
    }


    public void print() {
        if (this.data.size() == 0) {
            throw new IndexOutOfBoundsException("Invalid Operation!");
        }

        System.out.println(this.data.get(this.index));
    }

    public void printAll() {
        for (String element : this) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return this.index < data.size();
            }

            @Override
            public String next() {
                return data.get(this.index++);
            }
        };
    }

    @Override
    public void forEach(Consumer<? super String> action) {
        Iterator<String> iterator = this.iterator();

        while (iterator.hasNext()) {
            action.accept(iterator.next());
        }
    }
}
