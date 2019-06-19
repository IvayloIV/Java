package Company_Roster;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Department> departments = new HashMap<>();
        int n = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            String[] tokens = bufferedReader.readLine().split("\\s+");
            String name = tokens[0];
            double salary = Double.parseDouble(tokens[1]);
            String position = tokens[2];
            String department = tokens[3];

            Employee employee;
            if (tokens.length > 5) {
                String email = tokens[4];
                Integer age = Integer.parseInt(tokens[5]);
                employee = new Employee(name, salary, position, department, email, age);
            } else if (tokens.length <= 4) {
                employee = new Employee(name, salary, position, department);
            } else {
                String data = tokens[4];

                if (data.matches("^\\d+$")) {
                    int age = Integer.parseInt(data);
                    employee = new Employee(name, salary, position, department, age);
                } else {
                    employee = new Employee(name, salary, position, department, data);
                }
            }

            departments.putIfAbsent(department, new Department(department));
            departments.get(department).addEmployee(employee);
        }

        List<Department> sortedDepartments = departments.entrySet().stream()
                .map(Map.Entry::getValue)
                .sorted()
                .collect(Collectors.toList());

        System.out.println(sortedDepartments.get(0));
    }
}
