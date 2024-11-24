package TP2.ex2;

public class Philosophe extends Thread {

    private EnumPhilosophe etat;
    private final int numero;

    //Ressource partagée
    private Diner diner;

    public Philosophe(int numero, Diner diner) {
        this.etat = EnumPhilosophe.REFLEXION;
        this.numero = numero;
        this.diner = diner;
    }

    public void manger() throws InterruptedException {
        this.etat = EnumPhilosophe.MANGER;
        this.afficherEtat();
        Thread.sleep((long) (200 + Math.random() * 1000)); // Temps de manger aléatoire

        // Poser les fourchettes
        this.diner.poserFourchettes(this.numero);
    }

    public void penser() throws InterruptedException {
        this.etat = EnumPhilosophe.REFLEXION;
        this.afficherEtat();
        Thread.sleep((long) (200 + Math.random() * 1500)); // Temps de réflexion aléatoire
    }

    public void affamer() throws InterruptedException {
        this.etat = EnumPhilosophe.AFFAME;
        this.afficherEtat();     

        // Essayer de prendre les fourchettes
        this.diner.prendreFourchettes(this.numero);
    }

    public void afficherEtat() {
        System.out.println("Philosophe " + this.numero + " : " + this.etat);
    }

    public EnumPhilosophe getEtat() {
        return this.etat;
    }

    @Override
    public void run() {
        try{
            while(true){
                // Réflexion
                this.penser();

                // Affamé
                this.affamer();
                
                // A obtenu les fourchettes

                // Manger
                this.manger();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    enum EnumPhilosophe {
        REFLEXION,
        MANGER,
        AFFAME;
    }
    
}
