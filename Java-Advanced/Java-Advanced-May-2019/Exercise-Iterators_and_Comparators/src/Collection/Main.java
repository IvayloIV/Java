package Collection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        ListyIterator listyIterator = null;
        String input;
        while (!"END".equals(input = bufferedReader.readLine())) {
            try {
                if (input.equals("Move")) {
                    System.out.println(listyIterator.move());
                } else if (input.equals("Print")) {
                    listyIterator.print();
                } else if (input.equals("HasNext")) {
                    System.out.println(listyIterator.hasNext());
                } else if (input.equals("PrintAll")) {
                    listyIterator.printAll();
                } else if (input.startsWith("Create")) {
                    if (input.equals("Create")) {
                        listyIterator = new ListyIterator();
                    } else {
                        String[] tokens = input.substring(7).split("\\s+");
                        listyIterator = new ListyIterator(tokens);
                    }
                }
            } catch (IndexOutOfBoundsException err) {
                System.out.println(err.getMessage());
            }
        }
    }
}
