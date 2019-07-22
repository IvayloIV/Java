package Class_Box_Data_Validation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            double length = Double.parseDouble(bufferedReader.readLine());
            double width = Double.parseDouble(bufferedReader.readLine());
            double height = Double.parseDouble(bufferedReader.readLine());

            Box box = new Box(length, width, height);
            double volume = box.calculateVolume();
            double lateralSurfaceArea = box.calculateLateralSurfaceArea();
            double surfaceArea = box.calculateSurfaceArea();

            System.out.println(String.format("Surface Area - %.2f", surfaceArea));
            System.out.println(String.format("Lateral Surface Area - %.2f", lateralSurfaceArea));
            System.out.println(String.format("Volume - %.2f", volume));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
