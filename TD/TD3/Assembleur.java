public class Assembleur extends Thread {
    
    private Stock stock;

    public Assembleur(Stock stock) {
        this.stock = stock;
    }

    @Override
    public void run() {
        try {
            assembler();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void assembler() throws InterruptedException {
        stock.takeMotor();
        stock.takeBody();
        stock.takeWheel();
    }
}
