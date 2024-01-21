package Repositories;

import Order.IceCream.*;
import java.util.HashMap;
import java.util.Map;

public class FlavorRepository {

    private static FlavorRepository instance;
    private Map<String, Flavor> flavors;

    private FlavorRepository() {
        this.flavors = new HashMap<>();
    }

    public static FlavorRepository getInstance() {
        if (instance == null) {
            instance = new FlavorRepository();
        }
        return instance;
    }

    public FlavorRepository initialize(String[] flavorData) {
        if (flavorData.length % 2 != 0) {
            throw new IllegalArgumentException("Invalid flavor data array. It should have pairs of name and price.");
        }

        for (int i = 0; i < flavorData.length; i += 2) {
            String name = flavorData[i];
            double price = Double.parseDouble(flavorData[i + 1]);
            Flavor flavor = new Flavor(name, price);
            flavors.put(name, flavor);
        }

        return this;
    }

    public Flavor getFlavorByName(String name) {
        return flavors.get(name);
    }

    public void addFlavor(String name, double price) {
        Flavor flavor = new Flavor(name, price);
        flavors.put(name, flavor);
    }

    public void removeFlavor(String name) {
        Flavor removedFlavor = flavors.remove(name);
        if (removedFlavor != null) {
            System.out.println("Order.IceCream.Flavor removed: " + removedFlavor);
        } else {
            System.out.println("Order.IceCream.Flavor not found: " + name);
        }
    }

    //Debug:
    public Map getFlavors(){return this.flavors;}

}
