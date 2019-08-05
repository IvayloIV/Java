package solidLab.p02_OpenClosedPrinciple.p03_ShoppingCart;

public class Cart {
    private String customerEmail;
    private Order order;

    public Cart(String customerEmail, Order order) {
        this.customerEmail = customerEmail;
        this.order = order;
    }

    public String getCustomerEmail() {
        return this.customerEmail;
    }
}
