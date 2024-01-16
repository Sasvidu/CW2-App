public class PromotionContext {

    private PromotionStrategy promotionStrategy;

    public void setPromotionStrategy(PromotionStrategy promotionStrategy) {
        this.promotionStrategy = promotionStrategy;
    }

    public void applyPromotion(Order order) {
        if (promotionStrategy != null) {
            double finalPayment = promotionStrategy.applyPromotion(order);
            order.setFinalPayment(finalPayment);
        } else {
            System.out.println("No promotion strategy set.");
        }
    }

}