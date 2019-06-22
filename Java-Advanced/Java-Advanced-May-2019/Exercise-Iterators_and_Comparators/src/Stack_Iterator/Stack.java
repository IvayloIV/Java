package Stack_Iterator;

import java.util.EmptyStackException;
import java.util.Iterator;

public class Stack<T> implements Iterable<T> {
    private static final int DEFAULT_CAPACITY = 4;

    private int size;
    private int capacity;
    private Object[] arr;

    public Stack() {
        this.capacity = DEFAULT_CAPACITY;
        this.arr = new Object[this.capacity];
    }

    public void push(T element) {
        if (this.size >= this.capacity) {
            this.resizeArr();
        }

        this.arr[this.size] = element;
        this.size++;
    }

    public T pop() {
        this.checkSize();

        this.size--;
        T element = (T)this.arr[this.size];

        if (this.size <= this.capacity / 4) {
            this.shrinkArr();
        }

        return element;
    }

    private void checkSize() throws EmptyStackException {
        if (this.size == 0) {
            throw new IndexOutOfBoundsException("No elements");
        }
    }

    private void resizeArr() {
        this.capacity *= 2;
        Object[] newArr = new Object[this.capacity];
        copyElements(newArr);
        this.arr = newArr;
    }

    private void shrinkArr() {
        this.capacity /= 2;
        Object[] newArr = new Object[this.capacity];
        copyElements(newArr);
        this.arr = newArr;
    }

    private void copyElements(Object[] newArr) {
        for (int i = 0; i < this.size; i++) {
            newArr[i] = this.arr[i];
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = size - 1;

            @Override
            public boolean hasNext() {
                return this.index >= 0;
            }

            @Override
            public T next() {
                return (T)arr[this.index--];
            }
        };
    }
}
