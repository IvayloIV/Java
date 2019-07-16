package Student_System;

import java.util.HashMap;
import java.util.Map;

public class StudentSystem {
    private Map<String, Student> repo;

    public StudentSystem() {
        this.repo = new HashMap<>();
    }

    public void parseCommand(String[] args) {
        String command = args[0];
        String name = args[1];
        switch (command)
        {
            case "Create":
                int age = Integer.parseInt(args[2]);
                double grade = Double.parseDouble(args[3]);
                this.createStudent(name, age, grade);
                break;
            case "Show":
                this.showStudent(name);
                break;
        }
    }

    private void showStudent(String name) {
        if (!this.checkStudentExist(name)) {
            return;
        }

        Student student = repo.get(name);
        System.out.println(student);
    }

    private void createStudent(String name, int age, double grade) {
        if (this.checkStudentExist(name)) {
            return;
        }

        Student student = new Student(name, age, grade);
        repo.put(name, student);
    }

    private boolean checkStudentExist(String name) {
        return repo.containsKey(name);
    }
}
