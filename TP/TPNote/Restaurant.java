package TPNote;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Restaurant {

    public List<Commande> listeCommandes;

    public ZoneLivraison zone0;
    public ZoneLivraison zone1;
    public ZoneLivraison zone2;

    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();
    

    public Restaurant(List<Commande> commandes) {
        System.out.println("Commandes à réaliser : " + commandes);
        this.listeCommandes = commandes;
        this.zone0 = new ZoneLivraison(0);
        this.zone1 = new ZoneLivraison(1);
        this.zone2 = new ZoneLivraison(2);
    }

    public void ajouterCommande(Commande commande) {
        lock.lock();
        try {
            this.listeCommandes.add(commande);
            this.notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public Commande preparerCommande(int numCuisinier) {
        lock.lock();
        try {
            while (this.listeCommandes.isEmpty()) {
                try {
                    System.out.println("Cuisinier"+numCuisinier+" a terminé son service");
                    notEmpty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Commande commande = this.listeCommandes.removeFirst();
            System.out.println("Cuisinier" + numCuisinier + " prend la commande " + commande);
            notFull.signal();
            return commande;
        } finally {
            lock.unlock();
        }
    }


    public ZoneLivraison getZone(int numZone) {
        switch (numZone) {
            case 0:
                return this.zone0;
            case 1:
                return this.zone1;
            case 2:
                return this.zone2;
            default:
                return null;
        }
    }   
}