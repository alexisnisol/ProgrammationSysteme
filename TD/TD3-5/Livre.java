import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Livre {
    
    private RandomWait rwait = new RandomWait();
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    private String contenu;

    public Livre(String contenu) {
        this.contenu = contenu;
    }

    public void lire(int i) {
        lock.readLock().lock();;
        try {
            System.out.println("-> lecteur " + i);
            rwait.attendre(1, 2);
            System.out.println(contenu);
        } finally {
            lock.readLock().unlock();
        }
    }

    public void ecrire(int i) {
        lock.writeLock().lock();
        try {
            System.out.println("<- ecrivain " + i);
            rwait.attendre(1, 3);
            contenu = "Ecrivain " + i;
        } finally {
            lock.writeLock().lock();
        }
    }
}
