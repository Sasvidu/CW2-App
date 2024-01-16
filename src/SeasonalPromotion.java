public class SeasonalPromotion implements PromotionStrategy {

    private double finalPriceMultiplier;
    private String promotionDescription;

    public SeasonalPromotion(double discountRate, String promotionDescription){
        this.finalPriceMultiplier = discountRate;
        this.promotionDescription = promotionDescription;
    }

    @Override
    public double applyPromotion(Order order) {
        System.out.println("Applying seasonal promotion for order: " + order.getOrderId());
        return (order.getWrapper().getCost() * finalPriceMultiplier);
    }

    @Override
    public String getDescription(){
        return this.promotionDescription;
    }

}