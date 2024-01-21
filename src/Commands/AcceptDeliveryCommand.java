package Commands;

import Application.*;
import Repositories.*;
import Order.Order;
import Order.States.*;
import APIs.*;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class AcceptDeliveryCommand implements Command {

    private String deliverer;
    private String destination;

    public AcceptDeliveryCommand(String deliverer) {
        this.deliverer = deliverer;
    }

    @Override
    public void execute() {
        OrderRepository orderRepository = OrderRepository.getInstance();
        Scanner scanner = DeliveryApp.getInstance().getScanner();

        System.out.println("Enter the Order.Order Id for delivery:");
        String orderId = scanner.nextLine().trim();
        Order order = orderRepository.getOrder(orderId);

        System.out.println("Enter the destination for the delivery: ");
        this.destination = scanner.nextLine().trim();

        if (order != null && order.getState() instanceof OrderOutForDeliveryState) {
            order.process();
            OrderInDeliveryState state = (OrderInDeliveryState) order.getState();
            state.setDeliverer(deliverer);
            DeliveryApp.getInstance().AddDeliveryMapEntry(order);
            String estimatedTime = MappingAPI.getInstance().getEstimatedTravelTime(Main.dummyOrigin, this.destination);
            System.out.println("Estimated Time: " + estimatedTime);
            System.out.println("");
        } else {
            throw new NoSuchElementException("Order.Order not found or not ready for delivery.");
        }
    }

}
