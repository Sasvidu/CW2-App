package Payments;

import Order.Order;

public interface PaymentStrategy {

    void processPayment(Order order);

}
