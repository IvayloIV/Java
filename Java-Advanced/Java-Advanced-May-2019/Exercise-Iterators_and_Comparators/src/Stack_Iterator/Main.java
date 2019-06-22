package Stack_Iterator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Stack<Integer> stack = new Stack<>();
        String line;
        while (!"END".equals(line = bufferedReader.readLine())) {
            try {
                if (line.equals("Pop")) {
                    stack.pop();
                } else if (line.startsWith("Push")) {
                    Arrays.stream(line.substring(5).split("[\\s,]"))
                            .filter(a -> !a.equals(""))
                            .mapToInt(Integer::parseInt)
                            .forEach(stack::push);
                }
            } catch (IndexOutOfBoundsException err) {
                System.out.println(err.getMessage());
            }
        }

        stack.forEach(System.out::println);
        stack.forEach(System.out::println);
    }
}
