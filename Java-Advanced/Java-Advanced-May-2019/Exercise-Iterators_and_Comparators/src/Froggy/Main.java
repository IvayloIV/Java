package Froggy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int[] nums = Arrays.stream(bufferedReader.readLine().split(", "))
                .mapToInt(Integer::parseInt)
                .toArray();

        Lake lake = new Lake(nums);
        List<Integer> result = new ArrayList<>();
        lake.forEach(result::add);

        System.out.println(result.stream()
                .map(a -> a.toString())
                .collect(Collectors.joining(", ")));
    }
}
