public class DummyData {

    private static DummyData instance;

    private final String[] ICECREAM_FLAVORS = {
            "Banana Nut Fudge", "798.00",
            "Black Walnut", "850.00",
            "Burgundy Cherry", "900.00",
            "Butterscotch Ribbon", "840.00",
            "Cherry Macaron", "950.00",
            "Chocolate", "798.00",
            "Chocolate Almond", "850.00",
            "Chocolate Chip", "840.00",
            "Chocolate Fudge", "830.00",
            "Chocolate Mint", "900.00",
            "Chocolate Ribbon", "840.00",
            "Coffee", "850.00",
            "Coffee Candy", "900.00",
            "Date Nut", "840.00",
            "Eggnog", "950.00",
            "French Vanilla", "830.00",
            "Green Mint Stick", "900.00",
            "Lemon Crisp", "850.00",
            "Lemon Custard", "840.00",
            "Lemon Sherbet", "830.00",
            "Maple Nut", "900.00",
            "Orange Sherbet", "850.00",
            "Peach", "840.00",
            "Peppermint Fudge Ribbon", "950.00",
            "Peppermint Stick", "900.00",
            "Pineapple Sherbet", "840.00",
            "Raspberry Sherbet", "850.00",
            "Rocky Road Nut", "900.00",
            "Strawberry", "830.00",
            "Vanilla", "798.00",
            "Vanilla Burnt Almond", "850.00"
    };

    private final String[] ICECREAM_TOPPINGS = {
            "None", "0.00",
            "Sprinkles", "100.00",
            "Peanut Butter", "120.00",
            "Chocolate Covered Pretzels", "150.00",
            "Toasted Marshmallows", "130.00",
            "Waffle Cone", "100.00",
            "Sour Watermelon", "140.00",
            "Toasted Coconut Flakes", "110.00",
            "Chocolate Bark", "160.00",
            "Fruit", "90.00",
            "Reese's", "130.00",
            "Strawberry Pop-Tart", "120.00",
            "Toasted Almonds", "110.00",
            "Pretzels", "100.00",
            "Oreos", "120.00",
            "Rum-Soaked Raisins", "170.00",
            "Malted Milk Powder", "140.00",
            "Whoppers", "130.00",
            "Jam", "80.00",
            "Jelly", "80.00",
            "Honey", "90.00",
            "Honey-Roasted Peanuts", "120.00",
            "Gummy Bears", "100.00",
            "Graham Crackers", "110.00",
            "Golden Grahams", "110.00",
            "Fruity Pebbles", "110.00",
            "Crumbled Birthday Cake", "120.00",
            "Fruit Loops", "100.00",
            "Espresso", "130.00",
            "Donuts", "140.00",
            "Wafer Cookies", "100.00",
            "Muffin", "110.00",
            "Chocolate-Covered Raisins", "120.00",
            "Chocolate Chips", "110.00",
            "Cassis Blackberries", "150.00",
            "Chopped Butterfinger", "130.00",
            "Crumbled Brownie", "140.00",
            "Bourbon-Soaked Cherries", "170.00"
    };

    private final String[] ICECREAM_SYRUPS = {
            "None", "0.00",
            "Chocolate", "120.00",
            "Caramel", "100.00",
            "Butterscotch", "110.00",
            "Lemon", "90.00",
            "Strawberry", "130.00",
            "Pomegranate", "140.00",
            "Cola", "80.00",
            "Raspberry", "110.00",
            "Blueberry", "120.00",
            "Mint", "100.00",
            "Peach", "110.00"
    };

    private DummyData() {}

    public static DummyData getInstance(){
        if (instance == null) {
            instance = new DummyData();
        }
        return instance;
    }

    public String[] getIceCreamFlavors() {
        return this.ICECREAM_FLAVORS;
    }

    public String[] getIceCreamToppings() {
        return this.ICECREAM_TOPPINGS;
    }

    public String[] getIceCreamSyrups() {
        return this.ICECREAM_SYRUPS;
    }

}
