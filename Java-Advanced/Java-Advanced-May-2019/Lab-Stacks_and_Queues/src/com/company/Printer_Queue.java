package com.company;

import java.util.ArrayDeque;
import java.util.Scanner;

public class Printer_Queue {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayDeque<String> queue = new ArrayDeque<>();
        String line = "";
        while(!"print".equals(line = sc.nextLine())) {
            if (line.equals("cancel")) {
                if (queue.isEmpty()) {
                    System.out.println("Printer is on standby");
                } else {
                    System.out.println("Canceled " + queue.poll());
                }
            } else {
                queue.offer(line);
            }
        }

        while(!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }
}
