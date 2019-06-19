package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Average_Students_Grades {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, List<Double>> students = new TreeMap<>();
        Integer n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            String[] tokens = sc.nextLine().split("\\s+");
            String name = tokens[0];
            Double grade = Double.parseDouble(tokens[1]);

            students.putIfAbsent(name, new ArrayList<>());
            students.get(name).add(grade);
        }

        students.forEach((name, grades) -> {
            String gradesStr = grades.stream()
                    .map(a -> String.format("%.2f", a))
                    .collect(Collectors.joining(" "));

            Double sum = 0d;
            for (Double grade : grades) {
                sum += grade;
            }
            Double avg = sum / grades.size();
            System.out.println(String.format("%s -> %s (avg: %.2f)", name, gradesStr, avg));
        });
    }
}
