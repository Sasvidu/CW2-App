public interface PromotionStrategy {

    double applyPromotion(Order order);

    String getDescription();

}