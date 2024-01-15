public interface OrderState {

    void process(Order order);

    String getStateName();

}
