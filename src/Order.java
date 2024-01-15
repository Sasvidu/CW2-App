import java.util.*;

public class Order extends OrderObservable {

    private String orderId;
    private Map<OrderItem, Integer> orderItems;
    private Scanner scanner;
    private OrderState state;
    private OrderDecorator wrapper;

    public enum OrderStatus {
        PLACED,
        IN_PREPARATION,
        OUT_FOR_DELIVERY,
        DELIVERED
    }

    public Order(String orderId) {
        this.orderId = orderId;
        this.orderItems = new HashMap<>();
        this.scanner = App.getInstance().getScanner();
        this.state = new OrderCreatedState();
        this.wrapper = new RegularOrderWrapper(this);
        addObserver(new OrderStatusObserver());
        notifyObservers(this);
    }

    public double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (Map.Entry<OrderItem, Integer> entry : orderItems.entrySet()) {
            OrderItem item = entry.getKey();
            int quantity = entry.getValue();
            totalPrice += item.getPrice() * quantity;
        }
        return totalPrice;
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

        System.out.println("\nGross Order Price: $" + calculateTotalPrice());
        System.out.println("Order State: " + getState().getStateName());
        if(!(this.wrapper instanceof RegularOrderWrapper)){
            System.out.println("Net Order Price: " + this.wrapper.getCost());
        }
        System.out.println("");
    }

    public void addItem(OrderItem item, int quantity) {
        if (item == null || quantity <= 0) {
            return;
        }
        orderItems.put(item, quantity);
    }

    public void createOrderItem() {
        System.out.print("Enter the type of item you want to add (e.g., Ice Cream): ");
        String itemType = scanner.nextLine().trim().toLowerCase();

        switch (itemType) {
            case "ice cream":
                createIceCream();
                break;
            default:
                System.out.println("Invalid item type. Please try again.\n");
        }
    }

    private void createIceCream() {
        Command createIceCreamCommand = new CreateIceCreamCommand(this);
        createIceCreamCommand.execute();
    }

    public OrderState getState(){
        return this.state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public void process() {
        state.process(this);
        notifyObservers(this);
    }

    public String getOrderId() {
        return orderId;
    }

    public OrderDecorator getWrapper(){
        return this.wrapper;
    }

    public void addWrapper(OrderDecorator wrapper){
        this.wrapper = wrapper;
    }

}