public class GiftWrapper implements OrderDecorator{

    private OrderDecorator wrappee;

    public GiftWrapper(OrderDecorator wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public double getCost() {
        return wrappee.getCost() + 100.0;
    }

}
