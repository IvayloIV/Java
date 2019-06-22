package Linked_List_Traversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        LinkedList linkedList = new LinkedList();
        int n = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            String[] tokens = bufferedReader.readLine().split("\\s+");
            String command = tokens[0];
            int value = Integer.parseInt(tokens[1]);

            switch (command) {
                case "Add":
                    linkedList.add(value);
                    break;
                case "Remove":
                    linkedList.remove(value);
                    break;
            }
        }

        System.out.println(linkedList.getSize());
        linkedList.forEach(a -> System.out.print(a + " "));
    }
}
