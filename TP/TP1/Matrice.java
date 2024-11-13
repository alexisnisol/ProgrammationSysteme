import java.util.ArrayList;
import java.util.List;

public class Matrice {

    private int[][] matrice;
    private int taille;
    private int maxBorne;

    public Matrice(int taille, int maxBorne) {
        this.taille = taille;
        this.maxBorne = maxBorne;
        this.matrice = new int[taille][taille];
    }

    public int getTaille() {
        return this.taille;
    }

    public int[][] getMatrice() {
        return this.matrice;
    }

    public void init() {
        for (int i = 0; i < this.taille; i++) {
            for (int j = 0; j < this.taille; j++) {
                this.matrice[i][j] = (int) (Math.random() * this.maxBorne);
            }
        }
    }

    public void afficher() {
        for (int i = 0; i < this.taille; i++) {
            for (int j = 0; j < this.taille; j++) {
                System.out.print(this.matrice[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public Matrice multSeq(Matrice matrice) {
        Matrice result = new Matrice(this.taille, this.maxBorne);

        for (int i = 0; i < this.taille; i++) {
            for (int j = 0; j < this.taille; j++) {
                for (int k = 0; k < this.taille; k++) {
                    result.matrice[i][j] += this.matrice[i][k] * matrice.matrice[k][j];
                }
            }
        }
        return result;
    }

    /**
     * Multiplication parallèle de deux matrices, en utilisant des Threads pour chaque ligne.
     * @param matrice
     * @return
     */
    public Matrice multPar(Matrice other) {
        Matrice result = new Matrice(this.taille, this.maxBorne);
        List<ThreadMultLigne> threads = new ArrayList<>();

        for (int i = 0; i < this.taille; i++) {
            ThreadMultLigne thread = new ThreadMultLigne(this, other, result, i);
            threads.add(thread);
            thread.start();
        }

        for (ThreadMultLigne thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}