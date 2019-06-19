package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Academy_Graduation {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, List<Double>> students = new TreeMap<>();
        Integer n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            String name = sc.nextLine();
            List<Double> grades = Arrays.stream(sc.nextLine()
                    .split("\\s+"))
                    .map(Double::parseDouble)
                    .collect(Collectors.toList());

            students.putIfAbsent(name, new ArrayList<>());
            students.get(name).addAll(grades);
        }

        students.forEach((student, grades) -> {
            Double sum = 0d;
            for (Double grade : grades) {
                sum += grade;
            }
            Double avg = sum / grades.size();

            System.out.println(String.format("%s is graduated with %s", student, avg));
        });
    }
}
