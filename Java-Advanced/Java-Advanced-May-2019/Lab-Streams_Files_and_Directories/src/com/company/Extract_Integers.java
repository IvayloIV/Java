package com.company;

import java.io.*;
import java.util.Scanner;

public class Extract_Integers {

    public static void main(String[] args) {
        String userDir = System.getProperty("user.dir");

        String inputPath = userDir + "/input.txt";
        String outputPath = userDir + "/output.txt";

        try (Scanner sc = new Scanner(new FileInputStream(inputPath));
             PrintWriter fos = new PrintWriter(outputPath)) {

            while (sc.hasNext()) {
                sc.next();
                if (sc.hasNextInt()) {
                    fos.println(sc.nextInt());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
