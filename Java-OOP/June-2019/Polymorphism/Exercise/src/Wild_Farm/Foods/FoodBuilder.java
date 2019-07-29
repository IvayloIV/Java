package Wild_Farm.Foods;

public class FoodBuilder {
    public static Food createFood(String name, Integer quantity) {
        switch (name) {
            case "Vegetable":
                return new Vegetable(quantity);
            case "Meat":
                return new Meat(quantity);
            default:
                throw new IllegalArgumentException("Invalid food!");
        }
    }
}
