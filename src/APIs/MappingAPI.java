package APIs;

public class MappingAPI {

    private static MappingAPI instance;

    private MappingAPI() {}

    public static synchronized MappingAPI getInstance() {
        if (instance == null) {
            instance = new MappingAPI();
        }
        return instance;
    }

    public String getEstimatedTravelTime(String origin, String destination) {
        // Replace this with actual logic for estimating travel time
        // Fake return for demonstration purposes
        return "30 minutes";
    }

}
