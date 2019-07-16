package Student_System;

public class Student {
    private String name;
    private int age;
    private double grade;

    public Student(String name, int age, double grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    @Override
    public String toString() {
        String view = String.format("%s is %s years old.", this.name, this.age);
        view += this.getCommentary();
        return view;
    }

    private String getCommentary() {
        if (this.grade >= 5.00) {
            return " Excellent student.";
        } else if (this.grade >= 3.50) {
            return " Average student.";
        }
        return " Very nice person.";
    }
}
