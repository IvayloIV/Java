package Custom_List;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        CustomList<String> customList = new CustomList<>();
        String input;
        while (!"END".equals(input = bufferedReader.readLine())) {
            String[] tokens = input.split("\\s+");
            String command = tokens[0];

            if (command.equals("Add")) {
                customList.add(tokens[1]);
            } else if (command.equals("Remove")) {
                int index = Integer.parseInt(tokens[1]);
                customList.remove(index);
            } else if (command.equals("Contains")) {
                System.out.println(customList.contains(tokens[1]));
            } else if (command.equals("Swap")) {
                int index1 = Integer.parseInt(tokens[1]);
                int index2 = Integer.parseInt(tokens[2]);
                customList.swap(index1, index2);
            } else if (command.equals("Greater")) {
                System.out.println(customList.countGreaterThan(tokens[1]));
            } else if (command.equals("Max")) {
                System.out.println(customList.getMax());
            } else if (command.equals("Min")) {
                System.out.println(customList.getMin());
            } else if (command.equals("Print")) {
                customList.forEach(System.out::println);
            }
        }
    }
}
