package Commands;

import Application.*;
import Repositories.*;
import Order.Order;
import Order.States.*;
import Order.IceCream.*;
import Payments.*;
import APIs.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ViewOrderCommand implements Command {

    private String username;

    public ViewOrderCommand(String username) {
        this.username = username;
    }

    @Override
    public void execute() {
        OrderRepository orderRepository = OrderRepository.getInstance();
        Scanner scanner = CustomerApp.getInstance().getScanner();
        System.out.println("Enter the Order.Order Id:");
        String orderId = scanner.nextLine().trim();
        Order order = orderRepository.getOrder(orderId);

        if (order != null && order.getUsername().equals(username)) {
            System.out.println("");
            order.printOrderDetails();
        } else {
            throw new NoSuchElementException("Order.Order not found or the order does not belong to the current user.");
        }
    }

}
