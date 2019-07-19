package Restaurant;

public class Main {
    public static void main(String[] args) {
        Cake cake = new Cake("test");
        System.out.println(cake.getName());
        System.out.println(cake.getPrice());
        System.out.println(cake.getCalories());
        System.out.println(cake.getGrams());
    }
}
