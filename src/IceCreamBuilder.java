import java.util.ArrayList;
import java.util.List;

public class IceCreamBuilder implements OrderItemBuilder {
    private Flavor flavor;
    private List<Topping> toppings;
    private List<Syrup> syrups;
    private IceCreamSize size;

    private enum IceCreamSize {
        CUP,
        BAR,
        CONE,
        TUBE500,
        TUBE1000
    }

    public IceCreamBuilder() {
        this.toppings = new ArrayList<>();
        this.syrups = new ArrayList<>();
        System.out.println("Ice Cream Creation Started...");
    }

    public IceCreamBuilder setFlavor(String flavorName) {
        FlavorRepository flavorRepository = FlavorRepository.getInstance();
        this.flavor = flavorRepository.getFlavorByName(flavorName);
        if(this.flavor == null){
            throw new IllegalArgumentException("Flavor " + flavorName + " doesn't exist");
        }
        return this;
    }

    public IceCreamBuilder addToppings(List<String> toppingNames) {
        ToppingRepository toppingRepository = ToppingRepository.getInstance();

        for (String toppingName : toppingNames) {
            Topping topping = toppingRepository.getToppingByName(toppingName);
            if(topping == null){
                throw new IllegalArgumentException("Topping " + toppingName + " doesn't exist");
            }else{
                this.toppings.add(topping);
            }
        }
        return this;
    }

    public IceCreamBuilder addSyrups(List<String> syrupNames) {
        SyrupRepository syrupRepository = SyrupRepository.getInstance();

        for (String syrupName : syrupNames) {
            Syrup syrup = syrupRepository.getSyrupByName(syrupName);
            if(syrup == null){
                throw new IllegalArgumentException("Syrup " + syrupName + " doesn't exist");
            }else{
                this.syrups.add(syrup);
            }
        }
        return this;
    }

    public IceCreamBuilder setSize(String size) {
        this.size = validateSize(size);
        return this;
    }

    private IceCreamSize validateSize(String size) {
        try {
            return IceCreamSize.valueOf(size.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid ice cream size: " + size);
        }
    }


    @Override
    public OrderItem build() {
        IceCream iceCream = new IceCream(flavor, size.toString());
        for (Topping topping: toppings) {
           iceCream.addTopping(topping);
        }
        for (Syrup syrup: syrups) {
            iceCream.addSyrup(syrup);
        }
        return iceCream;
    }
}
