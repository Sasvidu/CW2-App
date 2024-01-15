import java.util.NoSuchElementException;
import java.util.Scanner;

public class ViewOrderCommand implements Command{
    @Override
    public void execute() {
        OrderRepository orderRepository = OrderRepository.getInstance();
        Scanner scanner = App.getInstance().getScanner();
        System.out.println("Enter the Order Id:");
        String orderId = scanner.nextLine().trim();
        Order order = orderRepository.getOrder(orderId);
        if(order == null){
            throw new NoSuchElementException();
        }
        System.out.println("");
        order.printOrderDetails();
    }
}
