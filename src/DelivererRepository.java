import java.util.HashMap;
import java.util.Map;

public class DelivererRepository {

    private static DelivererRepository instance;
    private Map<String, String> deliverers;

    private DelivererRepository() {
        deliverers = new HashMap<>();
    }

    public static synchronized DelivererRepository getInstance() {
        if (instance == null) {
            instance = new DelivererRepository();
        }
        return instance;
    }

    public void addDeliverer(String username, String password) {
        deliverers.put(username, password);
    }

    public Map<String, String> getDeliverers() {
        return deliverers;
    }

}