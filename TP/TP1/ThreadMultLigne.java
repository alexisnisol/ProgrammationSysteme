public class ThreadMultLigne extends Thread {
    
    private Matrice matA;
    private Matrice matB;
    private Matrice matC;
    private int ligne;

    public ThreadMultLigne(Matrice matA, Matrice matB, Matrice matC, int ligne) {
        this.matA = matA;
        this.matB = matB;
        this.matC = matC;
        this.ligne = ligne;
    }

    @Override
    public void run() {
        for (int j = 0; j < this.matA.getTaille(); j++) {
            int c = 0;
            for (int k = 0; k < this.matA.getTaille(); k++) {
                c += this.matA.getMatrice()[this.ligne][k] * this.matB.getMatrice()[k][j];
            }
            this.matC.getMatrice()[this.ligne][j] = c;
        }
    }
}
