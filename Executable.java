public class Executable {
    
    public static void main(String[] args) {

        int taille = 2000;
        int maxBorne = 1000;
        Matrice matA = new Matrice(taille, maxBorne);
        Matrice matB = new Matrice(taille, maxBorne);

        matA.init();
        matB.init();

        Thread t1 = new Thread(() -> {
            long start = System.currentTimeMillis();
            System.out.println("MATRICE A MULT SEQ : Start");
            matA.multSeq(matB);
            System.out.println("MATRICE A MULT SEQ : " + String.valueOf(System.currentTimeMillis() - start) + "ms");
        });
        t1.start();
        
        Thread t2 = new Thread(() -> {
            long start = System.currentTimeMillis();
            System.out.println("MATRICE A MULT PAR : Start");
            matA.multPar(matB);
            System.out.println("MATRICE A MULT PAR : " + String.valueOf(System.currentTimeMillis() - start) + "ms");
        });
        t2.start();
    }
}
