package Generic_Box_of_Integer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            int element = Integer.parseInt(bufferedReader.readLine());
            Box<Integer> box = new Box<>(element);
            System.out.println(box);
        }
    }
}
