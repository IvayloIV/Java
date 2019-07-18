package Stack_Of_Strings;

public class Main {
    public static void main(String[] args) {
        StackOfStrings stack = new StackOfStrings();

        stack.push("Gosho");
        stack.push("Pesho");
        stack.push("Ivan");

        System.out.println(stack.pop());
        stack.pop();
        System.out.println(stack.peek());
        //stack.pop();
        System.out.println(stack.isEmpty());
    }
}
