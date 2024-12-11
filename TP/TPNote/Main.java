package TPNote;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        
        List<Commande> lesCommandes = new ArrayList<>();
        lesCommandes.add(new Commande(Arrays.asList("Hamburger"), 0));
        lesCommandes.add(new Commande(Arrays.asList("Pizza"), 1));
        lesCommandes.add(new Commande(Arrays.asList("Tacos"), 0));
        lesCommandes.add(new Commande(Arrays.asList("Chili"), 0));
        lesCommandes.add(new Commande(Arrays.asList("Pokebowl"), 0));
        lesCommandes.add(new Commande(Arrays.asList("Pizza"), 2));
        lesCommandes.add(new Commande(Arrays.asList("Sushi"), 1));
        lesCommandes.add(new Commande(Arrays.asList("Hamburger"), 0));


        Restaurant restaurant = new Restaurant(lesCommandes);


        List<Cuisinier> lesCuisiniers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            lesCuisiniers.add(new Cuisinier(restaurant, i));
        }

        for (Cuisinier cuisinier : lesCuisiniers) {
            cuisinier.start();
        }

        List<Livreur> lesLivreurs = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            lesLivreurs.add(new Livreur(restaurant, i));
        }

        for (Livreur livreur : lesLivreurs) {
            livreur.start();
        }

        try {
            for (Cuisinier cuisinier : lesCuisiniers) {
                cuisinier.join();
            }

            for (Livreur livreur : lesLivreurs) {
                livreur.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
