package Commands;

import Application.*;
import Repositories.*;
import Order.Order;
import Order.States.*;
import Payments.*;
import APIs.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CompleteDeliveryCommand implements Command {

    private String deliverer;

    public CompleteDeliveryCommand(String deliverer) {
        this.deliverer = deliverer;
    }

    @Override
    public void execute() {
        OrderRepository orderRepository = OrderRepository.getInstance();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the Order.Order Id for completed delivery:");
        String orderId = scanner.nextLine().trim();
        Order order = orderRepository.getOrder(orderId);

        if (order != null && order.getState() instanceof OrderInDeliveryState) {
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
