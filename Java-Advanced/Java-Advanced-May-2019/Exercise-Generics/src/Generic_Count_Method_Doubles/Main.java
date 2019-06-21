package Generic_Count_Method_Doubles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        List<Double> elements = new ArrayList<>();
        int n = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            Double element = Double.parseDouble(bufferedReader.readLine());
            elements.add(element);
        }

        Double compareElement = Double.parseDouble(bufferedReader.readLine());
        System.out.println(Box.compareElements(elements, compareElement));
    }
}
