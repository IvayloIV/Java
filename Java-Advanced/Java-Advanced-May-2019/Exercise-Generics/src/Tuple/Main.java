package Tuple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] input1 = bufferedReader.readLine().split("\\s+");
        String name1 = input1[0] + " " + input1[1];
        String address = input1[2];
        Tuple<String, String> tuple1 = new Tuple<>(name1, address);
        System.out.println(tuple1);

        String[] input2 = bufferedReader.readLine().split("\\s+");
        String name2 = input2[0];
        int litersOfBeer = Integer.parseInt(input2[1]);
        Tuple<String, Integer> tuple2 = new Tuple<>(name2, litersOfBeer);
        System.out.println(tuple2);

        String[] input3 = bufferedReader.readLine().split("\\s+");
        int numInt = Integer.parseInt(input3[0]);
        double numDouble = Double.parseDouble(input3[1]);
        Tuple<Integer, Double> tuple3 = new Tuple<>(numInt, numDouble);
        System.out.println(tuple3);
    }
}
