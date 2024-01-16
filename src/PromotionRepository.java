import java.util.ArrayList;
import java.util.List;

public class PromotionRepository {

    private static PromotionRepository instance;
    private List<PromotionStrategy> promotionStrategies;

    private PromotionRepository() {
        promotionStrategies = new ArrayList<>();
        promotionStrategies.add(new BasePromotion());
        promotionStrategies.add(new LoyaltyPromotion("Loyalty Promotion"));
    }

    public static synchronized PromotionRepository getInstance() {
        if (instance == null) {
            instance = new PromotionRepository();
        }
        return instance;
    }

    public void addPromotionStrategy(PromotionStrategy promotionStrategy) {
        if (promotionStrategy != null) {
            promotionStrategies.add(promotionStrategy);
        } else {
            throw new IllegalArgumentException("Invalid promotion strategy.");
        }
    }

    public List<PromotionStrategy> getAllPromotionStrategies() {
        return new ArrayList<>(promotionStrategies);
    }

}
