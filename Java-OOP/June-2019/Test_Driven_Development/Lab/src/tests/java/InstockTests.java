import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.StreamSupport;

public class InstockTests {
    private ProductStock productStock;

    @Before
    public void initValues() {
        this.productStock = new Instock();
    }

    @Test
    public void addProductToCheckSize() {
        List<Product> products = createProducts(4);
        products.forEach(a -> productStock.add(a));
        Assert.assertEquals(products.size(), this.productStock.getCount());
    }

    @Test
    public void checkAddWithContainsMethod() {
        Product product = createProducts(1).get(0);
        this.productStock.add(product);
        Assert.assertTrue(this.productStock.contains(product));
    }

    @Test
    public void checkAddWithContainsMethodMissingProduct() {
        Product product = createProducts(1).get(0);
        Assert.assertFalse(this.productStock.contains(product));
    }

    @Test
    public void checkSizeWithEmptyCollection() {
        Assert.assertEquals(0, this.productStock.getCount());
    }

    @Test
    public void findExistProductInCollection() {
        Product product = createProducts(1).get(0);
        this.productStock.add(product);
        Assert.assertEquals(product, this.productStock.find(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void findWithNegativeIndexShouldThrowException() {
        Product product = createProducts(1).get(0);
        this.productStock.add(product);
        Assert.assertEquals(product, this.productStock.find(-1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void findWithOutOfBoundIndexShouldThrowException() {
        List<Product> products = createProducts(4);
        products.forEach(a -> this.productStock.add(a));
        this.productStock.find(4);
    }

    @Test
    public void changeProductQuantitySuccess() {
        List<Product> products = createProducts(4);
        products.forEach(a -> this.productStock.add(a));
        Product product = products.get(1);
        String labelOfProduct = product.getLabel();
        this.productStock.changeQuantity(labelOfProduct, 19);
        Assert.assertEquals(19, product.getQuantity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void changeProductQuantityOfNonExistProduct() {
        List<Product> products = createProducts(4);
        products.forEach(a -> this.productStock.add(a));
        this.productStock.changeQuantity(UUID.randomUUID().toString(), 19);
    }

    @Test
    public void findLabelForExistProduct() {
        List<Product> products = createProducts(4);
        products.forEach(a -> this.productStock.add(a));
        Product product = products.get(1);
        String labelOfProduct = product.getLabel();
        Assert.assertEquals(product, this.productStock.findByLabel(labelOfProduct));
    }

    @Test(expected = IllegalArgumentException.class)
    public void findLabelForNonExistProduct() {
        Product product = createProducts(1).get(0);
        Assert.assertEquals(product, this.productStock.findByLabel(product.getLabel()));
    }

    @Test
    public void findFirstByAlphabeticalOrderWithCorrectCount() {
        List<Product> products = createProducts(3);
        products.get(0).setLabel("B");
        products.get(1).setLabel("A");
        products.get(2).setLabel("C");

        products.forEach(a -> this.productStock.add(a));
        Collections.sort(products);
        Product[] actual = products.toArray(new Product[0]);
        Iterable<Product> expectedIterable = this.productStock.findFirstByAlphabeticalOrder(3);
        Product[] expected = StreamSupport
                .stream(expectedIterable.spliterator(), false)
                .toArray(Product[]::new);
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void findFirstByAlphabeticalOrderWithNegativeIndex() {
        List<Product> products = createProducts(3);
        products.get(0).setLabel("B");
        products.get(1).setLabel("A");
        products.get(2).setLabel("C");

        products.forEach(a -> this.productStock.add(a));
        Product[] actual = new Product[0];
        Iterable<Product> expectedIterable = this.productStock.findFirstByAlphabeticalOrder(-1);
        Product[] expected = StreamSupport
                .stream(expectedIterable.spliterator(), false)
                .toArray(Product[]::new);
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void findFirstByAlphabeticalOrderWithOutOfBoundIndex() {
        List<Product> products = createProducts(3);
        products.get(0).setLabel("B");
        products.get(1).setLabel("A");
        products.get(2).setLabel("C");

        products.forEach(a -> this.productStock.add(a));
        Product[] actual = new Product[0];
        Iterable<Product> expectedIterable = this.productStock.findFirstByAlphabeticalOrder(4);
        Product[] expected = StreamSupport
                .stream(expectedIterable.spliterator(), false)
                .toArray(Product[]::new);
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void findAllInPriceRangeWithCorrectRange() {
        List<Product> products = createProducts(3);
        products.get(0).setPrice(11);
        products.get(1).setPrice(19);
        products.get(2).setPrice(13);

        products.forEach(a -> this.productStock.add(a));
        products.sort((o1, o2) -> Double.compare(o2.getPrice(), o1.getPrice()));

        Product[] actual = new Product[] {
                products.get(0),
                products.get(1)
        };
        Iterable<Product> expectedIterable = this.productStock.findAllInRange(12, 19);
        Product[] expected = StreamSupport
                .stream(expectedIterable.spliterator(), false)
                .toArray(Product[]::new);

        Assert.assertArrayEquals(actual, expected);
    }

    @Test
    public void findAllInPriceRangeWithOutOfRange() {
        List<Product> products = createProducts(3);
        products.get(0).setPrice(11);
        products.get(1).setPrice(19);
        products.get(2).setPrice(13);

        products.forEach(a -> this.productStock.add(a));
        products.sort((o1, o2) -> Double.compare(o2.getPrice(), o1.getPrice()));

        Product[] actual = new Product[0];
        Iterable<Product> expectedIterable = this.productStock.findAllInRange(19, 40);
        Product[] expected = StreamSupport
                .stream(expectedIterable.spliterator(), false)
                .toArray(Product[]::new);

        Assert.assertArrayEquals(actual, expected);
    }

    @Test
    public void findAllByPriceWithCorrectValues() {
        List<Product> products = createProducts(5);
        products.get(0).setPrice(11);
        products.get(1).setPrice(12);
        products.get(2).setPrice(13);
        products.get(3).setPrice(12);
        products.get(4).setPrice(13);

        products.forEach(a -> this.productStock.add(a));

        Product[] actual = new Product[] {
                products.get(2),
                products.get(4)
        };
        Iterable<Product> expectedIterable = this.productStock.findAllByPrice(13);
        Product[] expected = StreamSupport
                .stream(expectedIterable.spliterator(), false)
                .toArray(Product[]::new);

        Assert.assertArrayEquals(actual, expected);
    }

    @Test
    public void findAllByPriceWithNonExistValue() {
        List<Product> products = createProducts(3);
        products.get(0).setPrice(11);
        products.get(1).setPrice(12);
        products.get(2).setPrice(13);

        products.forEach(a -> this.productStock.add(a));

        Product[] actual = new Product[0];
        Iterable<Product> expectedIterable = this.productStock.findAllByPrice(10);
        Product[] expected = StreamSupport
                .stream(expectedIterable.spliterator(), false)
                .toArray(Product[]::new);

        Assert.assertArrayEquals(actual, expected);
    }

    @Test
    public void findFirstMostExpensiveProductsWithCorrectValue() {
        List<Product> products = createProducts(5);
        products.get(0).setPrice(11);
        products.get(1).setPrice(19);
        products.get(2).setPrice(54);
        products.get(3).setPrice(1);
        products.get(4).setPrice(77);

        products.forEach(a -> this.productStock.add(a));
        products.sort((o1, o2) -> Double.compare(o2.getPrice(), o1.getPrice()));

        Product[] actual = new Product[] {
                products.get(0),
                products.get(1)
        };
        Iterable<Product> expectedIterable = this.productStock.findFirstMostExpensiveProducts(2);
        Product[] expected = StreamSupport
                .stream(expectedIterable.spliterator(), false)
                .toArray(Product[]::new);

        Assert.assertArrayEquals(actual, expected);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findFirstMostExpensiveProductsWithLessThanCount() {
        List<Product> products = createProducts(3);
        products.get(0).setPrice(54);
        products.get(1).setPrice(1);
        products.get(2).setPrice(77);

        products.forEach(a -> this.productStock.add(a));
        products.sort((o1, o2) -> Double.compare(o2.getPrice(), o1.getPrice()));

        this.productStock.findFirstMostExpensiveProducts(4);
    }

    @Test
    public void findAllByQuantityWithCorrectValues() {
        List<Product> products = createProducts(5);
        products.get(0).setQuantity(11);
        products.get(1).setQuantity(12);
        products.get(2).setQuantity(13);
        products.get(3).setQuantity(12);
        products.get(4).setQuantity(13);

        products.forEach(a -> this.productStock.add(a));

        Product[] actual = new Product[] {
                products.get(1),
                products.get(3)
        };
        Iterable<Product> expectedIterable = this.productStock.findAllByQuantity(12);
        Product[] expected = StreamSupport
                .stream(expectedIterable.spliterator(), false)
                .toArray(Product[]::new);

        Assert.assertArrayEquals(actual, expected);
    }

    @Test
    public void findAllByQuantityWithNonExistValue() {
        List<Product> products = createProducts(3);
        products.get(0).setQuantity(11);
        products.get(1).setQuantity(12);
        products.get(2).setQuantity(13);

        products.forEach(a -> this.productStock.add(a));

        Product[] actual = new Product[0];
        Iterable<Product> expectedIterable = this.productStock.findAllByQuantity(10);
        Product[] expected = StreamSupport
                .stream(expectedIterable.spliterator(), false)
                .toArray(Product[]::new);

        Assert.assertArrayEquals(actual, expected);
    }

    @Test
    public void testIteratorWithFullCollection() {
        List<Product> products = createProducts(3);
        products.get(0).setQuantity(11);
        products.get(1).setQuantity(12);
        products.get(2).setQuantity(13);

        products.forEach(a -> this.productStock.add(a));

        Product[] actual = products.toArray(new Product[0]);

        Iterator<Product> expectedIterator = this.productStock.iterator();
        Iterable<Product> expectedIterable = () -> expectedIterator;
        Product[] expected = StreamSupport
                .stream(expectedIterable.spliterator(), false)
                .toArray(Product[]::new);

        Assert.assertArrayEquals(actual, expected);
    }

    @Test
    public void testIteratorWithEmptyCollection() {
        Product[] actual = new Product[0];
        Iterator<Product> expectedIterator = this.productStock.iterator();
        Iterable<Product> expectedIterable = () -> expectedIterator;
        Product[] expected = StreamSupport
                .stream(expectedIterable.spliterator(), false)
                .toArray(Product[]::new);

        Assert.assertArrayEquals(actual, expected);
    }

    private List<Product> createProducts(int count) {
        List<Product> products = new ArrayList<>();

        for (int i = 1; i <= count; i++) {
            Product product = new Product(UUID.randomUUID().toString(), 16.2 * i, 2 * i);
            products.add(product);
        }

        return products;
    }
}
