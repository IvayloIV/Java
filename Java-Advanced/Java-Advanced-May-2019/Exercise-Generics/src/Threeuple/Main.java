package Threeuple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] input1 = bufferedReader.readLine().split("\\s+");
        String name1 = input1[0] + " " + input1[1];
        String address = input1[2];
        String town = input1[3];
        Threeuple<String, String, String> tuple1 = new Threeuple<>(name1, address, town);
        System.out.println(tuple1);

        String[] input2 = bufferedReader.readLine().split("\\s+");
        String name2 = input2[0];
        int litersOfBeer = Integer.parseInt(input2[1]);
        Boolean isDrunk = input2[2].equals("drunk");
        Threeuple<String, Integer, Boolean> tuple2 = new Threeuple<>(name2, litersOfBeer, isDrunk);
        System.out.println(tuple2);

        String[] input3 = bufferedReader.readLine().split("\\s+");
        String name3 = input3[0];
        double accountBalance = Double.parseDouble(input3[1]);
        String bankName = input3[2];
        Threeuple<String, Double, String> tuple3 = new Threeuple<>(name3, accountBalance, bankName);
        System.out.println(tuple3);
    }
}
