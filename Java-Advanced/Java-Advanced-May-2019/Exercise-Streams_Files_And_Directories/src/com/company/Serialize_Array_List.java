package com.company;

import java.io.*;
import java.util.ArrayList;

public class Serialize_Array_List {

    public static void main(String[] args) {
        String userDir = System.getProperty("user.dir");
        String path = userDir + "/list.ser";

        ArrayList<Double> nums = new ArrayList<>();
        nums.add(2.3);
        nums.add(5.1);
        nums.add(5.3);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
             ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            oos.writeObject(nums);
            nums = (ArrayList<Double>)ois.readObject();
            for (Double num : nums) {
                System.out.println(num);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
