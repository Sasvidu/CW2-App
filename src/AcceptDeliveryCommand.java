import java.util.NoSuchElementException;
import java.util.Scanner;

public class AcceptDeliveryCommand implements Command {

    private String deliverer;

    public AcceptDeliveryCommand(String deliverer) {
        this.deliverer = deliverer;
    }

    @Override
    public void execute() {
        OrderRepository orderRepository = OrderRepository.getInstance();
        Scanner scanner = DeliveryApp.getInstance().getScanner();

        System.out.println("Enter the Order Id for delivery:");
        String orderId = scanner.nextLine().trim();
        Order order = orderRepository.getOrder(orderId);

        if (order != null && order.getState() instanceof OrderOutForDeliveryState) {
            order.process();
            OrderInDeliveryState state = (OrderInDeliveryState) order.getState();
            state.setDeliverer(deliverer);
            DeliveryApp.getInstance().AddDeliveryMapEntry(order);
            System.out.println("");
        } else {
            throw new NoSuchElementException("Order not found or not ready for delivery.");
        }
    }

}
