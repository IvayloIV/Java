package Generic_Scale;

public class Main {

    public static void main(String[] args) {
        Scale<Integer> scale = new Scale<>(1, 3);
        System.out.println(scale.getHeavier());
    }
}
