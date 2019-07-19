package Animals;

public class Frog extends Animal {
    public Frog(String name, int age, String gender) throws IllegalArgumentException {
        super(name, age, gender);
    }

    @Override
    public String produceSound() {
        return "Ribbit";
    }
}
