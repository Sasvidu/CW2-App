import java.util.NoSuchElementException;
import java.util.Scanner;

public class CompleteOrderPreparationCommand implements Command{

    @Override
    public void execute() {
        OrderRepository orderRepository = OrderRepository.getInstance();
        Scanner scanner = AdminApp.getInstance().getScanner();
        System.out.println("Enter the Order Id:");
        String orderId = scanner.nextLine().trim();
        Order order = orderRepository.getOrder(orderId);
        order.printOrderDetails();
        System.out.println("Do you want to dispatch this order? (Yes / No)");
        String response = scanner.nextLine().trim();

        if(!response.matches("yes")){
            System.out.println("\n");
            return;
        }

        if(order.getState() instanceof OrderInPreparationState){
            if(((OrderInPreparationState) order.getState()).getIsPickup() != null){
                order.process();
                System.out.println("");
            }else{
                System.out.println("Please make sure the customer has selected a collection method for: " + order.getOrderId());
                System.out.println("");
            }
        }else{
            System.out.println("Please make sure the order: " + order.getOrderId() + " is in preparation State.");
            order.printOrderDetails();
            System.out.println("");
        }
    }

}
