public class PaymentContext {

    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void executePayment(Order order) {
        if (paymentStrategy != null) {
            paymentStrategy.processPayment(order);
        } else {
            System.out.println("No payment strategy set.");
        }
    }

}
