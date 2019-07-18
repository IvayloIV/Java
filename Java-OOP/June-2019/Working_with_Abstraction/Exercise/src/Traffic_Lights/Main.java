package Traffic_Lights;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        List<Light> lights = Arrays.stream(bufferedReader.readLine()
                .split("\\s+"))
                .map(Light::valueOf)
                .collect(Collectors.toList());

        int n = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            lights = lights
                    .stream()
                    .map(a -> Light.values()[(a.getValue() + 1) % 3])
                    .collect(Collectors.toList());

            for (Light light : lights) {
                System.out.print(light + " ");
            }
            System.out.println();
        }
    }
}
