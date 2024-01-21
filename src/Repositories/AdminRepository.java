package Repositories;

import java.util.HashMap;
import java.util.Map;

public class AdminRepository {

    private static AdminRepository instance;
    private Map<String, String> admins;

    private AdminRepository() {
        admins = new HashMap<>();
    }

    public static synchronized AdminRepository getInstance() {
        if (instance == null) {
            instance = new AdminRepository();
        }
        return instance;
    }

    public void addAdmin(String username, String password) {
        admins.put(username, password);
    }

    public Map<String, String> getAdmins() {
        return admins;
    }

}