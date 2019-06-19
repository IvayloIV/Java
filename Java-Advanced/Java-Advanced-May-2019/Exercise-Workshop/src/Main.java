public class Main {

    public static void main(String[] args) {
        DoublyLinkedList linkedList = new DoublyLinkedList();

        linkedList.addLast(3);
        linkedList.addLast(1);
        linkedList.addLast(4);

        System.out.println(linkedList.get(0));
        System.out.println(linkedList.get(1));
        System.out.println(linkedList.get(2));
    }
}
