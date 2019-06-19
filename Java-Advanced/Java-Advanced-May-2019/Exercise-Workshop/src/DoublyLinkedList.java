import java.util.EmptyStackException;
import java.util.function.Consumer;

public class DoublyLinkedList {
    private class ListNode {
        public int value;
        public ListNode prev;
        public ListNode next;

        public ListNode(int value) {
            this.value = value;
        }
    }

    private ListNode head;
    private ListNode tail;
    private int size;

    public void addFirst(int element) {
        ListNode node = new ListNode(element);

        if (head == null) {
            this.head = this.tail = node;
        } else {
            node.next = this.head;
            this.head.prev = node;
            this.head = node;
        }

        this.size++;
    }

    public void addLast(int element) {
        ListNode node = new ListNode(element);

        if (tail == null) {
            this.tail = this.head = node;
        } else {
            this.tail.next = node;
            node.prev = this.tail;
            this.tail = node;
        }

        this.size++;
    }

    public int removeFirst() {
        this.checkSize();

        int value = this.head.value;
        if (this.size == 1) {
            this.head = this.tail = null;
        } else {
            this.head = this.head.next;
            this.head.prev = null;
        }

        this.size--;
        return value;
    }

    public int removeLast() {
        this.checkSize();

        int value = this.tail.value;
        if (this.size == 1) {
            this.tail = this.head = null;
        } else {
            this.tail = this.tail.prev;
            this.tail.next = null;
        }

        this.size--;
        return value;
    }

    public int get(int index) {
        if (index < 0 || index > this.size - 1) {
            throw new IndexOutOfBoundsException();
        }

        ListNode node;
        if (index < this.size / 2) {
            node = this.head;
            while (index-- > 0) {
                node = node.next;
            }
        } else {
            node = this.tail;
            index = this.size - index - 1;
            while (index-- > 0) {
                node = node.prev;
            }
        }

        return node.value;
    }

    public void forEach(Consumer<Integer> consumer) {
        ListNode node = this.head;

        while (node != null) {
            consumer.accept(node.value);
            node = node.next;
        }
    }

    public int[] toArray() {
        int[] arr = new int[this.size];
        ListNode node = this.head;

        int count = 0;
        while (node != null) {
            arr[count++] = node.value;
            node = node.next;
        }

        return arr;
    }

    private void checkSize() {
        if (this.size == 0) {
            throw new EmptyStackException();
        }
    }
}
