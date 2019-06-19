package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public class Add_VAT {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        UnaryOperator<Double> addVAT = num -> num * 1.2;
        Consumer<Double> printData = a -> System.out.println(String.format("%.2f", a));

        System.out.println("Prices with VAT:");
        Arrays.stream(bufferedReader.readLine().split(", "))
                .map(Double::parseDouble)
                .map(addVAT)
                .forEach(printData);
    }
}
