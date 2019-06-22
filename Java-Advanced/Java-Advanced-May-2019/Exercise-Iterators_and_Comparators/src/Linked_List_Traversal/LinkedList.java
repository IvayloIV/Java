package Linked_List_Traversal;

import java.util.Iterator;

public class LinkedList implements Iterable<Integer> {
     private class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    private int size;
    private Node head;
    private Node tail;

    public void add(int value) {
        Node node = new Node(value);

        if (this.tail == null) {
            this.tail = this.head = node;
        } else {
            this.tail.next = node;
            this.tail = node;
        }

        this.size++;
    }

    public boolean remove(int value) {
        if (this.size == 0) {
            return false;
        }

        Node prev = null;
        Node top = this.head;
        boolean isExist = false;
        while (top != null) {
            if (top.value == value) {
                if (prev == null && this.getSize() == 1) {
                    this.head = this.tail = null;
                } else if (prev == null) {
                    this.head = this.head.next;
                } else {
                    prev.next = top.next;
                }
                isExist = true;
                break;
            }

            prev = top;
            top = top.next;
        }

        if (!isExist) {
            return false;
        }
        this.size--;
        return true;
    }

    public int getSize() {
        return this.size;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private int index = 0;
            private Node currentNode = head;

            @Override
            public boolean hasNext() {
                return this.index < getSize();
            }

            @Override
            public Integer next() {
                int value = this.currentNode.value;
                this.currentNode = this.currentNode.next;
                this.index++;
                return value;
            }
        };
    }
}
