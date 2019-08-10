import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Square_Root {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        double number = Double.parseDouble(bufferedReader.readLine());
        try {
            if (number < -1) {
                throw new IllegalArgumentException("Invalid number");
            }
            double sqrtNum = Math.sqrt(number);
            System.out.println(sqrtNum);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Good bye");
        }
    }
}
