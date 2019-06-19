package CustomStack;

import java.util.EmptyStackException;
import java.util.function.Consumer;

public class CustomStack {
    private static final int DEFAULT_CAPACITY = 4;

    private int size;
    private int capacity;
    private int[] arr;

    public CustomStack() {
        this.capacity = DEFAULT_CAPACITY;
        this.arr = new int[this.capacity];
    }

    public void push(int element) {
        if (this.size >= this.capacity) {
            this.resizeArr();
        }

        this.arr[this.size] = element;
        this.size++;
    }

    public int pop() {
        this.checkSize();

        this.size--;
        int element = this.arr[this.size];

        if (this.size <= this.capacity / 4) {
            this.shrinkArr();
        }

        return element;
    }

    public int peek() {
        this.checkSize();
        return this.arr[this.size - 1];
    }

    public void forEach(Consumer<Integer> consumer) {
        for (int i = this.size - 1; i >= 0; i--) {
            consumer.accept(this.arr[i]);
        }
    }

    private void checkSize() {
        if (this.size == 0) {
            throw new EmptyStackException();
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
