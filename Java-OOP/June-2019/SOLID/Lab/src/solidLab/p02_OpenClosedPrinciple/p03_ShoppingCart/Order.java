package solidLab.p02_OpenClosedPrinciple.p03_ShoppingCart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Order {
    private final List<OrderItem> items;

    protected Order() {
        this.items = new ArrayList<>();
    }

    public Iterable<OrderItem> getItems() {
        return Collections.unmodifiableList(this.items);
    }

    public void add(OrderItem item) { this.items.add(item); }
}
