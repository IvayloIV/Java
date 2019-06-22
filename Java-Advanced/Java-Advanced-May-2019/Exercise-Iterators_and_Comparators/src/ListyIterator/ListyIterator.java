package ListyIterator;

import java.util.Arrays;
import java.util.List;

public class ListyIterator {
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
}
