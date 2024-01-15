public class OrderStatusObserver implements OrderObserver{

    @Override
    public void update(Order order) {
        String message = getMessage(order.getState().getClass().getSimpleName(), order.getOrderId());
        System.out.println(message);
    }

    private String getMessage(String stateName, String orderId) {
        switch (stateName) {
            case "OrderCreatedState":
                return "New Order Created. Order Id: " + orderId + "\n\n";
            case "OrderPlacedState":
                return "Order confirmed! Thank you for your purchase. Order Id: " + orderId + "\n\n";
            case "OrderCustomizedState":
                return "Order customized successfully, Order Id: " + orderId + "\n\n";
            case "OrderInPreparationState":
                return "Order is being prepared, Order Id: " + orderId + "\n\n";
            case "OrderOutForPickupState":
                return "Order is out for customer pickup, Order Id: " + orderId + "\n\n";
            case "OrderOutForDeliveryState":
                return "Order is out for pickup by a delivery person, Order Id: " + orderId + "\n\n";
            case "OrderCancelledState":
                return "Order cancelled, Order Id:" + orderId + "\n" + "Thank you for visiting!" + "\n\n" ;
            default:
                return "Unknown Order State, Order Id: " + orderId;
        }
    }

}
