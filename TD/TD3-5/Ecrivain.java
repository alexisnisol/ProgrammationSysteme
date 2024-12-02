public class Ecrivain extends Thread {

    private int i;
    private Livre livre;
    RandomWait rwait;

    public Ecrivain(int i, Livre livre) {
        this.i = i;
        this.livre = livre;
        rwait = new RandomWait();
    }

    @Override
    public void run() {
        while (true) {
            rwait.attendre(1, 3);
            livre.ecrire(i);
        }
    }
}