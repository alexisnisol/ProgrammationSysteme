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
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < this.taille; i++) {
            ThreadMultLigne thread = new ThreadMultLigne(this, other, result, i);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    /**
     * Multiplication parallèle de deux matrices, en utilisant des Threads pour chaque ligne.
     * Au lieu de créer un Thread par ligne, on crée un Thread par bloc de lignes.
     * Il y a autant de Threads que de Coeurs disponibles.
     * @param matrice
     * @return
     */
    public Matrice multCore(Matrice other) {
        Matrice result = new Matrice(this.taille, this.maxBorne);
        List<Thread> threads = new ArrayList<>();
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        int rowsPerThread = (int) Math.ceil((double) this.taille / availableProcessors);
        System.out.println("Available processors: " + availableProcessors);
        System.out.println("Matrix size: " + this.taille);
        System.out.println("Ratio: " + (double) this.taille / availableProcessors);
        System.out.println("Rows per thread: " + rowsPerThread);

        int neededProcessors = (int) Math.ceil((double) this.taille / rowsPerThread);
        System.out.println(neededProcessors + " processors needed");

        for (int i = 0; i < neededProcessors; i++) {
            int startRow = i * rowsPerThread;
            int endRow = Math.min(startRow + rowsPerThread, this.taille);

            System.out.println("Thread " + i + " will process rows " + startRow + " to " + endRow);

            ThreadMultCore thread = new ThreadMultCore(this, other, result, startRow, endRow);
            threads.add(thread);
            thread.start();
        }

        System.out.println("Threads created: " + threads.size());

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public Matrice multCoreV2(Matrice other) {
        Matrice result = new Matrice(this.taille, this.maxBorne);
        List<Thread> threads = new ArrayList<>();
        int availableProcessors = Runtime.getRuntime().availableProcessors();

        int nrows = this.taille / availableProcessors;

        for (int i = 0; i < availableProcessors; ++i) {
            int startRow = i * nrows;
            int endRow = (i == availableProcessors - 1) ? this.taille : startRow + nrows;
            ThreadMultCore thread = new ThreadMultCore(this, other, result, startRow, endRow);
            threads.add(thread);
            thread.start();
        }

        System.out.println("Threads created: " + threads.size());

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public Matrice multCoreBlock(Matrice other) {
        Matrice result = new Matrice(this.taille, this.maxBorne);
        List<Thread> threads = new ArrayList<>();
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        int blockSize = (int) Math.ceil((double) this.taille / availableProcessors);

        for (int i = 0; i < availableProcessors; i++) {
            int startRow = (i / availableProcessors) * blockSize;
            int endRow = Math.min(startRow + blockSize, this.taille);
            int startCol = (i % availableProcessors) * blockSize;
            int endCol = Math.min(startCol + blockSize, this.taille);

            ThreadMultBlock thread = new ThreadMultBlock(this, other, result, startRow, endRow, startCol, endCol);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}