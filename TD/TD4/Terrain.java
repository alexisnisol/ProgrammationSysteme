
public class Terrain {

    int[][] land;

    public Terrain(int nrow, int ncol) {
        land = new int[nrow][ncol];
    }

    public boolean maybe_burn(int row, int col) {
        int nbr = 0;
        for (int drow = -1; drow < land.length; drow++) {
            for (int dcol = -1; dcol < land[0].length; dcol++) {
                if (drow == 0 && dcol == 0) {
                    continue;
                }
                if (intensity(row + drow, col + dcol) > 0) {
                    nbr++;
                }
            }
        }

        return nbr >= 6;
    }

    public int intensity(int row, int col) {
        if (row < 0 || row >= land.length || col < 0 || col >= land[0].length) {
            return 0;
        }
        return land[row][col];
    }

    public void display() {
        for (int row = 0; row < land.length; row++) {
            for (int col = 0; col < land[0].length; col++) {
                System.out.print(land[row][col] + " ");
            }
            System.out.println();
        }
    }

    public int get_state(int row, int col) {
        return land[row][col];
    }

    public int next_state(int row, int col) {
        if (land[row][col] == 0) {
            if (maybe_burn(row, col)) {
                return 1;
            }
        }
        return 0;
    }

    public void set_state(int row, int col, int state) {
        land[row][col] = state;
    }


}
