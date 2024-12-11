package TPNote;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ZoneLivraison {


    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();
    
    public List<Commande> commandesEnAttenteLivraison;

    private int numZone;
    private static int MAX_ZONE = 2;

    private RandomWait rwait = new RandomWait();

    public ZoneLivraison(int numZone){
        this.numZone = numZone;
        this.commandesEnAttenteLivraison = new ArrayList<>();
    }

    public void put(Commande commande, int numCuisinier) {
        lock.lock();
        try {
            while(this.commandesEnAttenteLivraison.size() >= MAX_ZONE) {
                try {
                    System.out.println("Cuisinier"+numCuisinier+" en attente, emplacement " + numZone + " plein");
                    notFull.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.commandesEnAttenteLivraison.add(commande);
            System.out.println("Cuisinier" + numCuisinier + " dépose le plat " + commande.plats + " dans l'emplacement " + numZone + " :" + this.commandesEnAttenteLivraison);
            notEmpty.signal();

        } finally {
            lock.unlock();
        }
    }

    public void take() {
        lock.lock();
        try {
            while(this.commandesEnAttenteLivraison.size() <= 0) {
                try {
                    System.out.println("Livreur" + numZone + " en attente, emplacement " + numZone + " vide");
                    notEmpty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            rwait.attendre(1, 3);
            Commande commandeRetire = this.commandesEnAttenteLivraison.remove(0);
            System.out.println("Livreur" + numZone + " a retiré " + commandeRetire.plats + ", début de la livraison en zone " + numZone + "; Emplacement " + numZone + " : " + this.commandesEnAttenteLivraison);
            notFull.signal();
        } finally {
            lock.unlock();
        }
    }

}
