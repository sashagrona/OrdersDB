package net.bigmir;

public class Order {
    private int id;
    private int customer;
    private int product;
    private int quantity;
    private static int n =0;

    public Order(int customer, int product, int quantity) {
        n++;
        setId(n);
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
