import java.util.HashMap;
import java.util.Map;

public class SyrupRepository {

    private static SyrupRepository instance;
    private Map<String, Syrup> syrups;

    private SyrupRepository() {
        this.syrups = new HashMap<>();
    }

    public static SyrupRepository getInstance() {
        if (instance == null) {
            instance = new SyrupRepository();
        }
        return instance;
    }

    public SyrupRepository initialize(String[] syrupData) {
        if (syrupData.length % 2 != 0) {
            throw new IllegalArgumentException("Invalid syrup data array. It should have pairs of name and price.");
        }

        for (int i = 0; i < syrupData.length; i += 2) {
            String name = syrupData[i];
            double price = Double.parseDouble(syrupData[i + 1]);
            Syrup syrup = new Syrup(name, price);
            syrups.put(name, syrup);
        }

        return this;
    }

    public Syrup getSyrupByName(String name) {
        return syrups.get(name);
    }

    public void addSyrup(String name, double price) {
        Syrup syrup = new Syrup(name, price);
        syrups.put(name, syrup);
    }

    public void removeSyrup(String name) {
        Syrup removedSyrup = syrups.remove(name);
        if (removedSyrup != null) {
            System.out.println("Syrup removed: " + removedSyrup);
        } else {
            System.out.println("Syrup not found: " + name);
        }
    }

    //Debug:
    public Map getSyrups(){return this.syrups;}

}
