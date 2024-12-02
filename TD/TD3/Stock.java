public class Stock {

    private Substock motorStock;
    private Substock bodyStock;
    private Substock wheelStock;

    private final int MAX_MOTOR = 5;
    private final int MAX_BODY = 3;
    private final int MAX_WHEEL = 20;

    public Stock() {
        motorStock = new Substock("Motor", MAX_MOTOR, 0);
        bodyStock = new Substock("Body", MAX_BODY, 0);
        wheelStock = new Substock("Wheel", MAX_WHEEL, 0);
    }
    
}
