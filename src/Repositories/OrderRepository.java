package Repositories;

import Order.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private static OrderRepository instance;
    private Map<String, Order> orders;

    private OrderRepository() {
        orders = new HashMap<>();
    }

    public static synchronized OrderRepository getInstance() {
        if (instance == null) {
            instance = new OrderRepository();
        }
        return instance;
    }

    public void addOrder(Order order) {
        if (order != null) {
            orders.put(order.getOrderId(), order);
        }else{
            throw new IllegalArgumentException();
        }
    }

    public Order getOrder(String orderId) {
        return orders.get(orderId);
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }

    public void removeOrder(String orderId) {
        orders.remove(orderId);
    }

    public void printAllOrders() {
        System.out.println("All Orders:\n");
        for (Order order : orders.values()) {
            order.printOrderDetails();
            System.out.println("-----------------------");
        }
    }
}
