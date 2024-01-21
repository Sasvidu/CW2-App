package Repositories;

import Payments.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepository {

    private static PaymentRepository instance;
    private List<PaymentStrategy> paymentStrategies;

    private PaymentRepository() {
        paymentStrategies = new ArrayList<>();
        paymentStrategies.add(new CashPayment());
        paymentStrategies.add(new CreditCardPayment());
    }

    public static synchronized PaymentRepository getInstance() {
        if (instance == null) {
            instance = new PaymentRepository();
        }
        return instance;
    }

    public void addPaymentStrategy(PaymentStrategy paymentStrategy) {
        if (paymentStrategy != null) {
            paymentStrategies.add(paymentStrategy);
        } else {
            throw new IllegalArgumentException("Payment strategy cannot be null.");
        }
    }

    public PaymentStrategy getPaymentStrategy(int index) {
        if (index >= 0 && index < paymentStrategies.size()) {
            return paymentStrategies.get(index);
        } else {
            throw new IndexOutOfBoundsException("Invalid payment strategy index.");
        }
    }

    public List<PaymentStrategy> getAllPaymentStrategies() {
        return new ArrayList<>(paymentStrategies);
    }

}
