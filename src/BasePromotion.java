public class BasePromotion implements PromotionStrategy {

    private String promotionDescription;

    public BasePromotion(){
        this.promotionDescription = "No promotion";
    }

    @Override
    public double applyPromotion(Order order) {
        return order.getWrapper().getCost();
    }

    @Override
    public String getDescription(){
        return this.promotionDescription;
    }

}