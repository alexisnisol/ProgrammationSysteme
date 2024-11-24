package TP2.ex2;

import java.util.ArrayList;
import java.util.List;

public class Diner {

    private List<Boolean> fourchettes;
    private List<Philosophe> philosophes;

    public Diner(int nbPhilosophes) {
        this.fourchettes = new ArrayList<Boolean>();
        this.philosophes = new ArrayList<Philosophe>();
        for (int i = 0; i < nbPhilosophes; i++) {
            this.fourchettes.add(true);
            this.philosophes.add(new Philosophe(i, this));
        }
    }

    public synchronized void prendreFourchettes(int numeroPhilosophe) throws InterruptedException {
        int[] fourchettesPhilo = this.getFourchettes(numeroPhilosophe);

        while(!canEat(numeroPhilosophe)){
            wait(); // Le philosophe attend que les fourchettes soient disponibles, il reste donc affamé.
        }

        this.fourchettes.set(fourchettesPhilo[0], false);
        this.fourchettes.set(fourchettesPhilo[1], false);
    }

    public synchronized void poserFourchettes(int numeroPhilosophe) throws InterruptedException {
        int[] fourchettesPhilo = this.getFourchettes(numeroPhilosophe);

        this.fourchettes.set(fourchettesPhilo[0], true);
        this.fourchettes.set(fourchettesPhilo[1], true);
        notifyAll();
    }

    public boolean canEat(int numeroPhilosophe) {
        int[] fourchettesPhilo = this.getFourchettes(numeroPhilosophe);

        return this.fourchettes.get(fourchettesPhilo[0]) && this.fourchettes.get(fourchettesPhilo[1]);
    }

    public int[] getFourchettes(int numeroPhilosophe) {
        int indexFourchetteGauche = numeroPhilosophe;
        int indexFourchetteDroite = (numeroPhilosophe + 1) % this.fourchettes.size();
        return new int[]{indexFourchetteGauche, indexFourchetteDroite};
    }


    public static void main(String[] args) {
        Diner diner = new Diner(5);

        for (Philosophe philosophe : diner.philosophes) {
            philosophe.start();
        }

        for (Philosophe philosophe : diner.philosophes) {
            try {
                philosophe.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
