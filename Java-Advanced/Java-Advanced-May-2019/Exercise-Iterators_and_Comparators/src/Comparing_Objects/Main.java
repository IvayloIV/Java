package Comparing_Objects;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        List<Person> people = new ArrayList<>();
        String line;
        while(!"END".equals(line = bufferedReader.readLine())) {
            String[] tokens = line.split("\\s+");
            String name = tokens[0];
            int age = Integer.parseInt(tokens[1]);
            String town = tokens[2];
            people.add(new Person(name, age, town));
        }

        int n = Integer.parseInt(bufferedReader.readLine());

        Person person = people.get(n - 1);
        int equalPeople = 0;
        for (Person currentPerson : people) {
            if (currentPerson.compareTo(person) == 0) {
                equalPeople++;
            }
        }

        if (equalPeople == 1) {
            System.out.println("No matches");
        } else {
            int peopleSize = people.size();
            System.out.println(String.format("%d %d %d",
                    equalPeople,
                    peopleSize - equalPeople,
                    peopleSize));
        }
    }
}
