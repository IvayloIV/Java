package com.company;

import java.io.*;

public class Serialize_Custom_Object {

    public static void main(String[] args) {
        String userDir = System.getProperty("user.dir");

        String outputPath = userDir + "/output.txt";
        Cube cube = new Cube("green", 15.3, 12.4, 3.0);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(outputPath))) {
            oos.writeObject(cube);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Cube implements Serializable {
    public String color;
    public Double width;
    public Double height;
    public Double depth;

    public Cube(String color, Double width, Double height, Double depth) {
        this.color = color;
        this.width = width;
        this.height = height;
        this.depth = depth;
    }
}
