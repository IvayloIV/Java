import java.util.*;
import java.util.stream.Collectors;

public class Instock implements ProductStock {
    private Map<String, Product> productMap;
    private List<Product> productList;

    public Instock() {
        this.productMap = new HashMap<>();
        this.productList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return this.productList.size();
    }

    @Override
    public boolean contains(Product product) {
        return this.productMap.containsKey(product.getLabel());
    }

    @Override
    public void add(Product product) {
        this.productMap.put(product.getLabel(), product);
        this.productList.add(product);
    }

    @Override
    public void changeQuantity(String product, int quantity) {
        if (!this.productMap.containsKey(product)) {
            throw new IllegalArgumentException();
        }
        this.productMap.get(product).setQuantity(quantity);
    }

    @Override
    public Product find(int index) {
        if (index < 0 || index > this.productList.size() - 1) {
            throw new IndexOutOfBoundsException();
        }
        return this.productList.get(index);
    }

    @Override
    public Product findByLabel(String label) {
        if (!this.productMap.containsKey(label)) {
            throw new IllegalArgumentException();
        }
        return this.productMap.get(label);
    }

    @Override
    public Iterable<Product> findFirstByAlphabeticalOrder(int count) {
        if (count < 0 || count > this.productList.size()) {
            return new ArrayList<>();
        }

        return this.productList.stream()
                .sorted(Product::compareTo)
                .limit(count)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findAllInRange(double lo, double hi) {
        return this.productList.stream()
                .filter(a -> a.getPrice() > lo && a.getPrice() <= hi)
                .sorted((o1, o2) -> Double.compare(o2.getPrice(), o1.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findAllByPrice(double price) {
        return this.productList.stream()
                .filter(a -> a.getPrice() == price)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findFirstMostExpensiveProducts(int count) {
        if (count > this.productList.size()) {
            throw new IllegalArgumentException();
        }

        return this.productList.stream()
                .sorted((o1, o2) -> Double.compare(o2.getPrice(), o1.getPrice()))
                .limit(count)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findAllByQuantity(int quantity) {
        return this.productList.stream()
                .filter(a -> a.getQuantity() == quantity)
                .collect(Collectors.toList());
    }

    @Override
    public Iterator<Product> iterator() {
        return this.productList.iterator();
    }
}
