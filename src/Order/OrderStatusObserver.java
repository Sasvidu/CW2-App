package Order;

public class OrderStatusObserver implements OrderObserver {

    @Override
    public void update(Order order) {
        String message = getMessage(order.getState().getClass().getSimpleName(), order.getOrderId());
        System.out.println(message);
    }

    private String getMessage(String stateName, String orderId) {
        switch (stateName) {
            case "Order.States.OrderCreatedState":
                return "New Order.Order Created. Order.Order Id: " + orderId + "\n\n";
            case "Order.States.OrderPlacedState":
                return "Order.Order confirmed! Thank you for your purchase. Order.Order Id: " + orderId + "\n\n";
            case "Order.States.OrderCustomizedState":
                return "Order.Order customized successfully, Order.Order Id: " + orderId + "\n\n";
            case "Order.States.OrderInPreparationState":
                return "Order.Order is being prepared, Order.Order Id: " + orderId + "\n\n";
            case "Order.States.OrderOutForPickupState":
                return "Order.Order is out for customer pickup, Order.Order Id: " + orderId + "\n\n";
            case "Order.States.OrderOutForDeliveryState":
                return "Order.Order is out for pickup by a delivery person, Order.Order Id: " + orderId + "\n\n";
            case "Order.States.OrderInDeliveryState":
                return "Delivery accepted for order: " + orderId + "\n\n";
            case "Order.States.OrderCompletedState":
                return "Order.Order completed, Order.Order Id: " + orderId + "\n" + "Thank you!" + "\n\n";
            case "Order.States.OrderCancelledState":
                return "Order.Order cancelled, Order.Order Id:" + orderId + "\n" + "Thank you for visiting!" + "\n\n" ;
            default:
                return "Unknown Order.Order State, Order.Order Id: " + orderId;
        }
    }

}