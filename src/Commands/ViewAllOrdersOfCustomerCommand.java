package Commands;

import Application.*;
import Repositories.*;
import Order.Order;
import Order.States.*;
import Order.IceCream.*;
import Payments.*;
import APIs.*;

public class ViewAllOrdersOfCustomerCommand implements Command {

    private String username;

    public ViewAllOrdersOfCustomerCommand(String username) {
        this.username = username;
    }

    @Override
    public void execute() {
        OrderRepository orderRepository = OrderRepository.getInstance();

        System.out.println("All Orders for Customer " + username + ":\n");

        for (Order order : orderRepository.getAllOrders()) {
            if (order.getUsername().equals(username)) {
                order.printOrderDetails();
                System.out.println("-----------------------");
            }
        }
    }

}
