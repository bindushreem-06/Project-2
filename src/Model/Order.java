package Model;

import java.util.LinkedList;

public class Order {
    private int orderId;
    private int customerId;
    private LinkedList<MenuItem> items = new LinkedList<>();

    public Order(int orderId, int customerId) {
        this.orderId = orderId;
        this.customerId = customerId;
    }

    public int getOrderId() { return orderId; }
    public int getCustomerId() { return customerId; }
    public LinkedList<MenuItem> getItems() { return items; }

    public void addItem(MenuItem item) { items.add(item); }
    public void removeItem(int id) {
        items.removeIf(i -> i.getId() == id);
    }

    @Override
    public String toString() {
        return "Order: " + orderId + " | Customer: " + customerId + " | Items: " + items.size();
    }
}
