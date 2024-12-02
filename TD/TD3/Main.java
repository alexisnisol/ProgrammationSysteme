public class Main {


    public static void main(String[] args) {
        
        oldStock stock = new oldStock();

        Fournisseur fournisseur = new Fournisseur(stock);
        Assembleur assembleur = new Assembleur(stock);

        fournisseur.start();
        assembleur.start();

        try {
            fournisseur.join();
            assembleur.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
