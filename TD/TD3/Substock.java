import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Substock {

    private String name;
    private int max;
    private int qte;
    private int count = 0;
    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();


    public Substock(String name, int max, int qte) {
        this.name = name;
        this.max = max;
        this.qte = qte;
    }    

    void message(String s) {
        System.out.println("[" + s + "] " + name);
    }

    public void put() {
        lock.lock();
        try {
            while(count >= max) {
                try {
                    notFull.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            count++;
            message("+1");
            notEmpty.signal();

        } finally {
            lock.unlock();
        }
    }

    public void get() {
        lock.lock();
        try {
            while(count < qte) {
                try {
                    notEmpty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            count-= qte;
            message(String.valueOf(-qte));
            for(int i = 0; i < qte; i++) {
                notFull.signal();
            }

        } finally {
            lock.unlock();
        }
    }
}
