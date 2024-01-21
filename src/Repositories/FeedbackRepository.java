package Repositories;

import java.util.HashMap;
import java.util.Map;

public class FeedbackRepository {

    private static FeedbackRepository instance;
    private Map<String, String> feedbackMap;

    private FeedbackRepository() {
        feedbackMap = new HashMap<>();
    }

    public static synchronized FeedbackRepository getInstance() {
        if (instance == null) {
            instance = new FeedbackRepository();
        }
        return instance;
    }

    public void addFeedback(String orderId, String feedback) {
        feedbackMap.put(orderId, feedback);
    }

    public String getFeedback(String orderId) {
        return feedbackMap.get(orderId);
    }

}
