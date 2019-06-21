package Threeuple;

public class Threeuple<T, E, J> {
    private T item1;
    private E item2;
    private J item3;

    public Threeuple(T item1, E item2, J item3) {
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
    }

    public T getItem1() {
        return item1;
    }

    private void setItem1(T item1) {
        this.item1 = item1;
    }

    public E getItem2() {
        return item2;
    }

    private void setItem2(E item2) {
        this.item2 = item2;
    }

    public J getItem3() {
        return item3;
    }

    private void setItem3(J item3) {
        this.item3 = item3;
    }

    @Override
    public String toString() {
        return String.format("%s -> %s -> %s", this.item1, this.item2, this.item3);
    }
}
