package Custom_Exception;

public class Main {
    public static void main(String[] args) {
        try {
            Student student = new Student("Pesho", "pesho@abv.bg");
        } catch (InvalidPersonNameException e) {
            System.out.println(e.getMessage());
        }
    }
}
