package Jar_of_T;

import java.util.ArrayDeque;
import java.util.Deque;

public class Jar<T> {
    private Deque<T> element;

    public Jar() {
        this.element = new ArrayDeque<>();
    }

    public void add(T element) {
        this.element.push(element);
    }

    public T remove() {
        return this.element.pop();
    }
}
