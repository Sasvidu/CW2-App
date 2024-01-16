public class LoyaltyPromotion implements PromotionStrategy {

    private String promotionDescription;
    private String username;

    public LoyaltyPromotion(String promotionDescription){
        this.promotionDescription = promotionDescription;
    }

    @Override
    public double applyPromotion(Order order) {
        this.username = order.getUsername();
        System.out.println("Applying loyalty promotion for order: " + order.getOrderId());

        OrderRepository orderRepository = OrderRepository.getInstance();
        int orderCount = 0;

        for (Order orderByUser : orderRepository.getAllOrders()) {
            if (orderByUser.getUsername().equals(username)) {
                orderCount++;
            }
        }

        double finalPriceMultiplier = calculateDiscountRate(orderCount);
        System.out.println("You have made " + orderCount + " total orders.\nThe price will be reduced down to: " + finalPriceMultiplier);
        return order.getWrapper().getCost() * finalPriceMultiplier;
    }

    @Override
    public String getDescription(){
        return this.promotionDescription;
    }

    private double calculateDiscountRate(int orderCount){
        int baseOrderCount = 5; // Orders needed for the initial discount
        int maxDiscountOrderCount = 20; // Maximum orders for full discount
        double initialDiscountRate = 0.02; // Initial discount rate per order
        double maxDiscountRate = 0.2; // Maximum discount rate

        // Calculate the discount rate based on the number of orders
        double discountRate = Math.min(initialDiscountRate * (orderCount - baseOrderCount + 1), maxDiscountRate);

        // Ensure the discount rate is within bounds
        return (1 - Math.max(0.0, discountRate));
    }

}