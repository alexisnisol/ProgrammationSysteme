package TPNote;

import java.util.List;

public class Commande {
    
    public List<String> plats;
    public int zone;

    public Commande(List<String> plats, int zone) {
        this.plats = plats;
        this.zone = zone;
    }

    @Override
    public String toString() {
        return "("+this.plats+","+this.zone+")";
    }
}
