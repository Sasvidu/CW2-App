package Payments;

import Order.Order;

public class CreditCardPayment implements PaymentStrategy{

    @Override
    public void processPayment(Order order) {
        System.out.println("Processing credit card payment for order: " + order.getOrderId());
    }

}
