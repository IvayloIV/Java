package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.function.Consumer;

public class Knights_of_Honor {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Consumer<String> printFormat = a -> System.out.println("Sir " + a);

        Arrays.stream(bufferedReader.readLine().split("\\s+")).forEach(printFormat);
    }
}
