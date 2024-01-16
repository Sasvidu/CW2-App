import java.util.Scanner;

public class RecordFeedbackCommand implements Command {

    private String username;

    public RecordFeedbackCommand(String username) {
        this.username = username;
    }

    @Override
    public void execute() {
        Scanner scanner = CustomerApp.getInstance().getScanner();
        FeedbackRepository feedbackRepository = FeedbackRepository.getInstance();

        System.out.print("Enter the Order ID: ");
        String orderId = scanner.nextLine().trim();

        OrderRepository orderRepository = OrderRepository.getInstance();
        Order order = orderRepository.getOrder(orderId);

        if (order != null && order.getUsername().equals(username)) {
            System.out.print("Enter your feedback: ");
            String feedback = scanner.nextLine().trim();
            feedbackRepository.addFeedback(orderId, feedback);
            System.out.println("Feedback added successfully!");
        } else {
            System.out.println("Order not found or does not belong to the current user.");
        }
    }

}
