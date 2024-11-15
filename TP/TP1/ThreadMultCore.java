public class ThreadMultCore extends Thread {
    
    private Matrice matA;
    private Matrice matB;
    private Matrice matC;
    private int ligneStart;
    private int ligneEnd;

    public ThreadMultCore(Matrice matA, Matrice matB, Matrice matC, int ligneStart, int ligneEnd) {
        this.matA = matA;
        this.matB = matB;
        this.matC = matC;
        this.ligneStart = ligneStart;
        this.ligneEnd = ligneEnd;
    }

    @Override
    public void run() {

        int taille = this.matA.getTaille();
        
        for (int i = this.ligneStart; i < this.ligneEnd; i++) {
            for (int j = 0; j < taille; j++) {
                int c = 0;
                for (int k = 0; k < taille; k++) {
                    c += this.matA.getMatrice()[i][k] * this.matB.getMatrice()[k][j];
                }
                this.matC.getMatrice()[i][j] = c;
            }
        }
    }
}
