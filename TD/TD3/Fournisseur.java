public class Fournisseur extends Thread {

    private Stock stock;
    private TypeFournisseur type;

    public Fournisseur(Stock stock, TypeFournisseur type) {
        this.stock = stock;
        this.type = type;
    }

    @Override
    public void run() {
        try {
            switch (type) {
                case CARROSSERIE:
                    stock.putBody();
                    break;

                case MOTEUR:
                    stock.putMotor();
                    break;

                case ROUE:
                    stock.putWheel();
                    break;
                default:
                    break;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
