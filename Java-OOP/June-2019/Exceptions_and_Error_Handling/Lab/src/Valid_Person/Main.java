package Valid_Person;

public class Main {
    public static void main(String[] args) {
        try {
            Person peter = new Person("Gosho", "Goshov", 120);
        } catch (IllegalArgumentException ex) {
            System.out.println("Exception thrown: " + ex.getMessage());
        }
    }
}
