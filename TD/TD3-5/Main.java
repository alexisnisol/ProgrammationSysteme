public class Main {
    public static void main(String[] args) {
        Livre livre = new Livre("Contenu initial");

        for (int i = 0; i < 5; i++) {
            new Lecteur(i, livre).start();
        }

        for (int i = 0; i < 3; i++) {
            new Ecrivain(i, livre).start();
        }
    }    
}
