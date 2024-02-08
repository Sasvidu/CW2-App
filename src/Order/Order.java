package Order;

import Commands.*;
import Order.*;
import Order.States.*;
import Order.Decorators.*;
import Order.Handlers.*;
import Payments.*;
import Application.*;
import java.util.*;

public class Order extends OrderObservable {

    private String orderId;
    private String username;
    private Map<OrderItem, Integer> orderItems;
    private Scanner scanner;
    private OrderState state;
    private OrderDecorator wrapper;
    private Boolean isPaid;
    private double finalPayment;
    private PaymentContext paymentContext;

    public enum OrderStatus {
        PLACED,
        IN_PREPARATION,
        OUT_FOR_DELIVERY,
        DELIVERED
    }

    public Order(String orderId, String username) {
        this.orderId = orderId;
        this.username = username;
        this.orderItems = new HashMap<>();
        this.scanner = CustomerApp.getInstance().getScanner();
        this.state = new OrderCreatedState();
        this.wrapper = new RegularOrderWrapper(this);
        this.isPaid = false;
        addObserver(new OrderStatusObserver());
        notifyObservers(this);
    }

    public Order(String orderId, String username, Order originalOrder) {
        this.orderId = orderId;
        this.username = username;
        this.orderItems = originalOrder.getOrderItems();
        this.scanner = CustomerApp.getInstance().getScanner();
        this.state = new OrderCreatedState();
        this.wrapper = new RegularOrderWrapper(this);
        this.isPaid = false;
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
            System.out.println("Net Order Price: $" + this.wrapper.getCost());
        }

        if (finalPayment > 0) {
            System.out.println("Final Payment: $" + finalPayment);
        }

        System.out.println("");

        if(isPaid){
            System.out.println("Paid!");
        }else{
            System.out.println("Pending Payment...");
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

    public void process() {
        state.process(this);
        notifyObservers(this);
    }

    public void pay(PaymentContext paymentContext){
        this.paymentContext = paymentContext;
        paymentContext.executePayment(this);
        this.isPaid = true;
    }

    public OrderState getState(){
        return this.state;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getUsername() {
        return username;
    }

    public Map getOrderItems(){
        return this.orderItems;
    }

    public OrderDecorator getWrapper(){
        return this.wrapper;
    }

    public Boolean getIsPaid(){
        return this.isPaid;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public void addWrapper(OrderDecorator wrapper){
        this.wrapper = wrapper;
    }

    public void setFinalPayment(double finalPayment){
        this.finalPayment = finalPayment;
    }

    private void createIceCream() {
        Command createIceCreamCommand = new CreateIceCreamCommand(this);
        createIceCreamCommand.execute();
    }

}