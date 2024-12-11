package TPNote;

public class Livreur extends Thread {
    
    private Restaurant restaurant;
    private int zone;

    public Livreur(Restaurant restaurant, int zone){
        this.restaurant = restaurant;
        this.zone = zone;
    }

    @Override
    public void run() {
        while (true) {
            this.restaurant.getZone(zone).take();
        }
    }
}
