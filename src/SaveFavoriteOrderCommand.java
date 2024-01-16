import java.util.Map;
import java.util.Scanner;

public class SaveFavoriteOrderCommand implements Command {

    private Order order;
    private String username;

    public SaveFavoriteOrderCommand(Order order, String username) {
        this.order = order;
        this.username = username;
    }

    @Override
    public void execute() {
        CustomerApp app = CustomerApp.getInstance();
        Scanner scanner = app.getScanner();
        Map<String, Order> userOrderMap = app.getFavoriteOrderMap();

        System.out.print("Do you want to save this order as a favorite? (Yes/No): ");
        String response = scanner.nextLine().trim().toLowerCase();

        if (response.equals("yes")) {
            if (userOrderMap.containsKey(username)) {
                userOrderMap.replace(username, order);
            } else {
                userOrderMap.put(username, order);
            }
            System.out.println(order.getOrderId() + " saved as a favorite for " + username + "!\n\n");
        } else {
            System.out.println("");
        }
    }
}
