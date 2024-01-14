import java.util.*;

public class Order {

    private String orderId;
    private Map<OrderItem, Integer> orderItems;
    private Scanner scanner;

    public enum OrderStatus {
        PLACED,
        IN_PREPARATION,
        OUT_FOR_DELIVERY,
        DELIVERED
    }

    public Order(String orderId) {
        this.orderId = orderId;
        this.orderItems = new HashMap<>();
        this.scanner = new Scanner(System.in);
        System.out.println("New Order Created. Order Id: " + this.orderId);
    }

    private void addItem(OrderItem item, int quantity) {
        if (item == null || quantity <= 0) {
            return;
        }
        orderItems.put(item, quantity);
    }

    public String getOrderId() {
        return orderId;
    }

    public void printOrderDetails() {
        if (orderItems.isEmpty()) {
            System.out.println("No items in the order.");
            return;
        }

        System.out.println("Order Details (Order ID: " + orderId + "):");
        for (Map.Entry<OrderItem, Integer> entry : orderItems.entrySet()) {
            OrderItem item = entry.getKey();
            int quantity = entry.getValue();

            System.out.println(" - " + item.getDescription() + " x " + quantity);
            System.out.println("   Price per unit: $" + item.getPrice());
            System.out.println("   Subtotal: $" + item.getPrice() * quantity);
        }

        System.out.println("\nTotal Order Price: $" + calculateTotalPrice());
    }

    private double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (Map.Entry<OrderItem, Integer> entry : orderItems.entrySet()) {
            OrderItem item = entry.getKey();
            int quantity = entry.getValue();
            totalPrice += item.getPrice() * quantity;
        }
        return totalPrice;
    }

    public void createIceCream() {
        IceCreamDirector iceCreamDirector = new IceCreamDirector();
        IceCream iceCream = iceCreamDirector.createIceCream();

        if (iceCream != null) {
            System.out.print("Enter the quantity of ice cream needed: ");
            int quantity = Integer.parseInt(scanner.nextLine());

            // Add the created ice cream to the order
            addItem(iceCream, quantity);
            System.out.println("Ice Cream added to order: " + iceCream);
        }

        //iceCreamDirector.closeScanner();
    }

}