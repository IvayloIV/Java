package com.company;

import java.io.File;

public class List_Files {

    public static void main(String[] args) {
        String userDir = System.getProperty("user.dir");

        File file = new File(userDir + "/files/Files-and-Streams");

        File[] files = file.listFiles();
        if (files == null) {
            return;
        }

        for (File currentFile : files) {
            if (!currentFile.isDirectory()) {
                System.out.printf("%s: [%d]", currentFile.getName(), currentFile.length());
                System.out.println();
            }
        }
    }
}
