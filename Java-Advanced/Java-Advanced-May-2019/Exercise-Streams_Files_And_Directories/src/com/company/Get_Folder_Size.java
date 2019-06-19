package com.company;

import java.io.*;

public class Get_Folder_Size {

    public static void main(String[] args) {
        String userDir = System.getProperty("user.dir");

        File root = new File(userDir + "/resources/Exercises Resources");

        Long totalSize = 0L;
        for (File file : root.listFiles()) {
            totalSize += file.length();
        }

        System.out.println("Folder size: " + totalSize);
    }
}
