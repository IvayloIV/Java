package Shopping_Spree;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Person {
    private String name;
    private double money;
    private List<Product> products;

    public Person(String name, double money) {
        this.setName(name);
        this.setMoney(money);
        this.products = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    private void setMoney(double money) {
        if (money < 0) {
            throw new IllegalArgumentException("Money cannot be negative");
        }
        this.money = money;
    }

    public void buyProduct(Product product) {
        if (this.money < product.getCost()) {
            throw new IllegalArgumentException(this.getName() + " can't afford " + product.getName());
        }

        this.money -= product.getCost();
        this.products.add(product);
    }

    @Override
    public String toString() {
        return String.format("%s - %s", this.getName(),
                products.isEmpty() ? "Nothing bought" : this.products.stream()
                        .map(Product::getName)
                        .collect(Collectors.joining(", ")));
    }
}
