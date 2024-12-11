package TPNote;

public class RandomWait {
    
    public void attendre(int minSec, int maxSec) {
        int duree = (int) (Math.random() * (maxSec - minSec + 1) + minSec) * 1000;
        try {
            Thread.sleep(duree);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
