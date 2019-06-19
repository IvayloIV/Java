package com.company;

import java.io.FileInputStream;
import java.io.IOException;

public class Read_File {

    public static void main(String[] args) {
	    String userDir = System.getProperty("user.dir");

	    String inputPath = userDir + "/input.txt";

	    try (FileInputStream fis = new FileInputStream(inputPath)) {
            Integer textByte;

            while ((textByte = fis.read()) >= 0) {
                String binary = Integer.toBinaryString(textByte);
                System.out.printf("%s ", binary);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
