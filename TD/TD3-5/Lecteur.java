public class Lecteur extends Thread {

    private int i;
    private Livre livre;
    RandomWait rwait;

    public Lecteur(int i, Livre livre) {
        this.i = i;
        this.livre = livre;
        rwait = new RandomWait();
    }

    @Override
    public void run() {
        while (true) {
            rwait.attendre(1, 2);
            livre.lire(i);
        }
    }
}