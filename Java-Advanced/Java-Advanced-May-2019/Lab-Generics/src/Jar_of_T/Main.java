package Jar_of_T;

public class Main {

    public static void main(String[] args) {
        Jar<Integer> nums = new Jar<>();
        nums.add(3);
        nums.add(12);
        nums.add(6);

        System.out.println(nums.remove());
        System.out.println(nums.remove());
    }

}
