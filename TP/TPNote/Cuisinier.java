package TPNote;

public class Cuisinier extends Thread {

    private Restaurant restaurant;
    private int numCuisinier;
    private RandomWait rwait = new RandomWait();

    public Cuisinier(Restaurant restaurant, int numCuisinier){
        this.restaurant = restaurant;
        this.numCuisinier = numCuisinier;
    }

    @Override
    public void run(){
        while (true) {
            rwait.attendre(1, 2);
            Commande commande = restaurant.preparerCommande(this.numCuisinier);
            rwait.attendre(1, 2);
            ZoneLivraison laZone = restaurant.getZone(commande.zone);
            laZone.put(commande, numCuisinier);
            
        }
    }
    
}
