package Animals;

public class Cat extends Animal {
    public Cat(String name, int age, String gender) throws IllegalArgumentException {
        super(name, age, gender);
    }

    @Override
    public String produceSound() {
        return "Meow meow";
    }
}
