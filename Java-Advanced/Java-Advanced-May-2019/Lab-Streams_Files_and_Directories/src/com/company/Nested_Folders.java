package com.company;

import java.io.File;
import java.util.ArrayDeque;
import java.util.Deque;

public class Nested_Folders {

    public static void main(String[] args) {
        String userDir = System.getProperty("user.dir");

        File file = new File(userDir + "/files/Files-and-Streams");
        Integer folderCount = 0;
        Deque<File> queue = new ArrayDeque<>();
        queue.offer(file);

        while (!queue.isEmpty()) {
            File currentFile = queue.poll();

            for (File f : currentFile.listFiles()) {
                if (f.isDirectory()) {
                    queue.offer(f);
                }
            }

            folderCount++;
            System.out.println(currentFile.getName());
        }

        System.out.println(String.format("[%d] folders", folderCount));
    }
}