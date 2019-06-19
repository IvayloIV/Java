package SmartArray;

import java.util.function.Consumer;

public class SmartArray {
    private static final int DEFAULT_CAPACITY = 4;

    private int size;
    private int capacity = DEFAULT_CAPACITY;
    private int[] arr;

    public SmartArray() {
        this.size = 0;
        this.arr = new int[this.capacity];
    }

    public void add(int element) {
        if (this.size >= this.capacity) {
            this.resizeArr();
        }

        this.arr[this.size] = element;
        this.size++;
    }

    public void add(int index, int element) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException();
        }

        if (this.size >= this.capacity) {
            this.resizeArr();
        }

        for (int i = this.size; i > index; i--) {
            this.arr[i] = this.arr[i - 1];
        }

        this.arr[index] = element;
        this.size++;
    }

    public int get(int index) {
        this.checkIndex(index);
        return this.arr[index];
    }

    public int remove(int index) {
        this.checkIndex(index);

        int el = this.arr[index];
        this.size--;
        for (int i = index; i <= this.size - 1; i++) {
            this.arr[i] = this.arr[i + 1];
        }

        this.arr[this.size] = 0;

        if (this.size <= this.capacity / 4) {
            this.shrinkArr();
        }
        return el;
    }

    public boolean contains(int element) {
        for (int i = 0; i < this.size; i++) {
            if (this.arr[i] == element) {
                return true;
            }
        }

        return false;
    }

    public void forEach(Consumer<Integer> consumer) {
        for (int i = 0; i < this.size; i++) {
            consumer.accept(this.arr[i]);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index > this.size - 1) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void resizeArr() {
        this.capacity *= 2;
        int[] newArr = new int[this.capacity];
        copyElements(newArr);
        this.arr = newArr;
    }

    private void shrinkArr() {
        this.capacity /= 2;
        int[] newArr = new int[this.capacity];
        copyElements(newArr);
        this.arr = newArr;
    }

    private void copyElements(int[] newArr) {
        for (int i = 0; i < this.size; i++) {
            newArr[i] = this.arr[i];
        }
    }
}
