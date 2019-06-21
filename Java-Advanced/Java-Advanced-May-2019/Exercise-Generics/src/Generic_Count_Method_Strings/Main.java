package Generic_Count_Method_Strings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        List<Box<String>> boxes = new ArrayList<>();
        int n = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            String element = bufferedReader.readLine();
            Box<String> box = new Box<>(element);
            boxes.add(box);
        }

        String compareElement = bufferedReader.readLine();
        System.out.println(Box.compareElements(boxes, compareElement));
    }
}
