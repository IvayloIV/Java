package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class Applied_Arithmetics {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Consumer<Integer> printNum = num -> System.out.print(num + " ");

        List<Integer> nums = Arrays.stream(bufferedReader.readLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        String line;
        while(!(line = bufferedReader.readLine()).equals("end")) {
            if (line.equals("print")) {
                nums.forEach(printNum);
                System.out.println();
                continue;
            }

            UnaryOperator<Integer> operation = GetOperation(line);
            nums = nums.stream().map(operation).collect(Collectors.toList());
        }
    }

    private static UnaryOperator<Integer> GetOperation(String operation) {
        UnaryOperator<Integer> add = a -> a + 1;
        UnaryOperator<Integer> multiply = a -> a * 2;
        UnaryOperator<Integer> subtract = a -> a - 1;

        switch (operation) {
            case "add":
                return add;
            case "multiply":
                return multiply;
            default:
                return subtract;
        }
    }
}
