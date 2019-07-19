package Animals;

public class Tomcat extends Cat {
    public Tomcat(String name, int age) throws IllegalArgumentException {
        super(name, age, "Male");
    }

    @Override
    public String produceSound() {
        return "MEOW";
    }
}
