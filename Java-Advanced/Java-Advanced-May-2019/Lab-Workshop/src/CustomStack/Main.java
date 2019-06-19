package CustomStack;

public class Main {

    public static void main(String[] args) {
        CustomStack customStack = new CustomStack();

        customStack.push(3);
        customStack.push(1);
        customStack.push(7);
        customStack.push(0);
        customStack.pop();
        customStack.forEach(a -> System.out.println(a));
    }
}
