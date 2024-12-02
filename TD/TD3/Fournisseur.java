public class Fournisseur extends Thread {

    private oldStock stock;
    private TypeFournisseur type;

    public Fournisseur(oldStock stock, TypeFournisseur type) {
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

    public void attendre() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void fournir() {
        this.start();
        this.attendre();
    }

}
