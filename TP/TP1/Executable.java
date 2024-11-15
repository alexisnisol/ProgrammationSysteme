public class Executable {
    
    public static void main(String[] args) {

        int taille = 50;
        int maxBorne = 9;
        Matrice matA = new Matrice(taille, maxBorne);
        Matrice matB = new Matrice(taille, maxBorne);

        matA.init();
        matB.init();

        long start = System.currentTimeMillis();
        System.out.println("MATRICE A MULT SEQ : Start");
        matA.multSeq(matB);
        System.out.println("MATRICE A MULT SEQ : " + String.valueOf(System.currentTimeMillis() - start) + "ms");
        matA.afficher();

        long startPar = System.currentTimeMillis();
        System.out.println("MATRICE A MULT PAR : Start");
        matA.multPar(matB);
        System.out.println("MATRICE A MULT PAR : " + String.valueOf(System.currentTimeMillis() - startPar) + "ms");
        matA.afficher();
    }
}
