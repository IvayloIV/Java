package com.company;

import java.io.*;

public class Serialize_Custom_Object {

    public static void main(String[] args) {
        String userDir = System.getProperty("user.dir");
        String path = userDir + "/course.ser";

        Course course = new Course("Pesho", 23);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(course);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Course implements Serializable {
    String name;
    Integer numberOfStudents;

    public Course(String name, Integer numberOfStudents) {
        this.name = name;
        this.numberOfStudents = numberOfStudents;
    }
}
