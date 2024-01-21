package Payments;

import Order.Order;

public interface PromotionStrategy {

    double applyPromotion(Order order);

    String getDescription();

}