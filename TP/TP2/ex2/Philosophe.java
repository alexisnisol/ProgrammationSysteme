package TP2.ex2;

public class Philosophe {

    private EnumPhilosophe etat;

    public Philosophe() {
        this.etat = EnumPhilosophe.REFLEXION;
    }

    public void manger() {
        this.etat = EnumPhilosophe.MANGER;
    }

    public void penser() {
        this.etat = EnumPhilosophe.REFLEXION;
    }

    public void affamer() {
        this.etat = EnumPhilosophe.AFFAME;
    }

    public void afficherEtat() {
        System.out.println("Philosophe : " + this.etat);
    }

    public EnumPhilosophe getEtat() {
        return this.etat;
    }

    enum EnumPhilosophe {
        REFLEXION,
        MANGER,
        AFFAME;
    }
    
}
