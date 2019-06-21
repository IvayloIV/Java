package Generic_Swap_Method_String;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        List<Box> boxes = new ArrayList<>();
        int n = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            String element = bufferedReader.readLine();
            Box<String> box = new Box<>(element);
            boxes.add(box);
        }

        int[] tokens = Arrays.stream(bufferedReader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int index1 = tokens[0];
        int index2 = tokens[1];

        Box.swap(boxes, index1, index2);
        for (Box box : boxes) {
            System.out.println(box);
        }
    }
}
