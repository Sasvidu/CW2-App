public class CashPayment implements PaymentStrategy{

    @Override
    public void processPayment(Order order) {
        System.out.println("Cash Payment selected, make sure to be ready with cash to collect your order! " + order.getOrderId());
    }

}
