public class Terrain {
    
    private Case[][] landBefore;
    private Case[][] landAfter;

    public Terrain(int taille) {
        landBefore = new Case[taille][taille];
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                landBefore[i][j] = new Case(TypeCase.INTACTE);
            }
        }

        landAfter = landBefore.clone();
    }

    public Case[][] getLandBefore() {
        return landBefore;
    }

    public Case[][] getLandAfter() {
        return landAfter;
    }

    public void setChanged(boolean changed) {
        if (changed) {
            landBefore = landAfter.clone();
        }
    }

    public boolean maybe_burn(int row, int col) {
        int nbr = 0;
        for (int drow = -1; drow < landAfter.length; drow++) {
            nbr += intensity(row+drow, col-dcol);
            
        }

        return nbr >= 6;
    }

    /**
        La fonction calcule l'état de la case i,j au tour suivant
        this.carte contient l'état au temps t
        this.carteT1 contient l'état au temps t+1
        La fonction lit les valeur dans this.carte et écrit dans this.carteT1
        Si un changement a lieu, la fonction this.setChanged est appelée
    **/
    public void propagationCase(int i, int j) {
        int val = this.landBefore[i][j].getValue();

        //augmentation des flames
        if (val > 0 && val <= 3) {
        this.landAfter[i][j].incrementValue();
        this.setChanged(true);
        }
        //zone intact
        else if (val == 0) {
        for (int k = i - 1; k <= i + 1; k++) {
            if (k >= 0 && k < this.landBefore.length) {
            for (int l = j - 1; l <= j + 1; l++) {
                if (l >= 0 && l < this.landBefore[i].length) {
                if (this.landBefore[k][l].getValue() < 4) val += this.landBefore[k][l].getValue();
                }
            }
            }
        }
        // voisins suffisamment enflammes, la case s'enflamme
        if (val >= 6) {
            this.landAfter[i][j].setType(TypeCase.FEU_INTENSITE_1);
            this.setChanged(true);
        }
        // voisins pas assez en feu, la case reste intact, recopie de l'ancienne valeur
        else
        this.landAfter[i][j] = this.landBefore[i][j];
        }
        else
        this.landAfter[i][j] = this.landBefore[i][j];
    }

}
