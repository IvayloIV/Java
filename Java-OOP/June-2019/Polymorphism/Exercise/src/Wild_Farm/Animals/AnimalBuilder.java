package Wild_Farm.Animals;

public class AnimalBuilder {
    public static Animal createAnimal(String... params) {
        String type = params[0];
        String name = params[1];
        Double weight = Double.parseDouble(params[2]);
        String livingRegion = params[3];
        switch (type) {
            case "Cat":
                String breed = params[4];
                return new Cat(name, type, weight, livingRegion, breed);
            case "Tiger":
                return new Tiger(name, type, weight, livingRegion);
            case "Zebra":
                return new Zebra(name, type, weight, livingRegion);
            case "Mouse":
                return new Mouse(name, type, weight, livingRegion);
            default:
                throw new IllegalArgumentException("Invalid animal!");
        }
    }
}
