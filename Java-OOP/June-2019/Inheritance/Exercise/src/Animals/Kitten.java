package Animals;

public class Kitten extends Cat {
    public Kitten(String name, int age) throws IllegalArgumentException {
        super(name, age, "Female");
    }

    @Override
    public String produceSound() {
        return "Meow";
    }
}
