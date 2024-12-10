import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class Engine {

    Terrain currentLand;
    Terrain nextLand;
    int nrows;
    int ncols;
    int phase;
    Random rand;
    boolean state_changed;
    int nworkers;
    boolean again;
    CyclicBarrier barrier;

    public Engine(int nrows, int ncols, int ini) {
        this.nrows = nrows;
        this.ncols = ncols;
        currentLand = new Terrain(nrows, ncols);
        nextLand = new Terrain(nrows, ncols);
        phase = 1;
        rand = new Random();

        for (int i = 0; i < ini; i++) {
            int row = rand.nextInt(nrows);
            int col = rand.nextInt(ncols);
            currentLand.set_state(row, col, 1);
        }

        state_changed = true;
        again = true;
        nworkers = 2;
        barrier = new CyclicBarrier(nworkers, new Action(this));

    }

    public void start() {
        int n = nrows / nworkers;
        for (int i = 0; i < nworkers; i++) {
            int beg = i * n;
            int end = i * (n + 1);
            if (i+1 == nworkers) {
                end = nrows;
            }
            new Worker(this, beg, end).start();
        }
    }

    public void display() {
        currentLand.display();
    }
    
    public boolean isFinished() {
        return !state_changed;
    }

    public void setChanged(boolean changed) {
        state_changed = changed;
    }

    public void compute_next_state(int row, int col) {
        int st = currentLand.get_state(row, col);
        int nx = currentLand.next_state(row, col);
        if (st != nx) {
            state_changed = true;
        }
        nextLand.set_state(row, col, nx);
    }

    public void swap() {
        Terrain tmp = currentLand;
        currentLand = nextLand;
        nextLand = tmp;
    }

    public void step() {
        System.out.println("Phase" + phase + " :");
        phase++;
        currentLand.display();
        System.out.println();
        compute_next_state(0, 0);
    }
    
}
