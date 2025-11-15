package Services;

import Model.Order;
import Model.MenuItem;
import Exception.ResourceNotFoundException;
import java.util.HashMap;

public class OrderService {
    private HashMap<Integer, Order> orderDB = new HashMap<>();

    public void createOrder(Order order) {
        orderDB.put(order.getOrderId(), order);
    }

    public HashMap<Integer, Order> getAllOrders() {
        return orderDB;
    }

    public void addItemToOrder(int orderId, MenuItem item) throws ResourceNotFoundException {
        Order order = orderDB.get(orderId);
        if (order == null) throw new ResourceNotFoundException("Order not found!");
        order.addItem(item);
    }

    public void deleteOrder(int orderId) throws ResourceNotFoundException {
        if (!orderDB.containsKey(orderId)) throw new ResourceNotFoundException("Order not found!");
        orderDB.remove(orderId);
    }
}

