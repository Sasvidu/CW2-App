package Commands;

import Application.*;
import Repositories.*;
import Order.Order;
import Order.States.*;
import Payments.*;
import APIs.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CompletePickupCommand implements Command {

    @Override
    public void execute() {
        OrderRepository orderRepository = OrderRepository.getInstance();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the Order.Order Id to Pick Up:");
        String orderId = scanner.nextLine().trim();
        Order order = orderRepository.getOrder(orderId);

        if (order != null && order.getState() instanceof OrderOutForPickupState) {
            try {
                order.process();
            }catch (Exception e){
                System.out.println(e);
            }
        } else {
            throw new NoSuchElementException("Order.Order not found or not ready for completion.");
        }
    }

}
