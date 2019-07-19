package Animals;

public class AnimalFactory {
    public static Animal createAnimal(String type, String... params) {
        switch (type) {
            case "Cat":
                return new Cat(params[0], Integer.parseInt(params[1]), params[2]);
            case "Dog":
                return new Dog(params[0], Integer.parseInt(params[1]), params[2]);
            case "Frog":
                return new Frog(params[0], Integer.parseInt(params[1]), params[2]);
            case "Kitten":
                return new Kitten(params[0], Integer.parseInt(params[1]));
            case "Tomcat":
                return new Tomcat(params[0], Integer.parseInt(params[1]));
            default:
                throw new IllegalArgumentException("Invalid input!");
        }
    }
}
