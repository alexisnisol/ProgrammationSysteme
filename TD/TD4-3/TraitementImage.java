import java.awt.image.BufferedImage;
import java.util.concurrent.RecursiveAction;

public class TraitementImage extends RecursiveAction {

    private BufferedImage m;
    private int xDebut, xFin, yDebut, yFin;

    public TraitementImage(BufferedImage m) {
        this(m, 0, m.getHeight(), 0, m.getWidth());
    }

    public TraitementImage(BufferedImage m, int i0, int iF, int j0, int jF) {
        this.m = m;
        this.xDebut = i0;
        this.xFin = iF;
        this.yDebut = j0;
        this.yFin = jF;
        //System.out.println(xDebut + " " + xFin + " " + yDebut + " " + yFin);
    }

    @Override
    public void compute() {
        
        if(shouldSplit()) {

            int iLim = subWidth();
            int jLim = subHeight();

            System.out.println("Splitting task with xDebut=" + xDebut + " xFin=" + xFin + " yDebut=" + yDebut + " yFin=" + yFin + " subHeight=" + iLim + " subWidth=" + jLim);

            TraitementImage subTask11 = new TraitementImage(m, xDebut, iLim, yDebut, jLim);
            TraitementImage subTask12 = new TraitementImage(m, iLim, xFin, yDebut, jLim);
            TraitementImage subTask21 = new TraitementImage(m, xDebut, iLim, jLim, yFin);
            TraitementImage subTask22 = new TraitementImage(m, iLim, xFin, jLim, yFin);

            
            subTask21.fork();
            subTask22.fork();
            subTask12.fork();
            subTask11.process();
        } else {
            process();
        }
    }

    public void process() {
        for (int i = xDebut; i < xFin; i++) {
            for (int j = yDebut; j < yFin; j++) {
                m.setRGB(i, j, pixelOperation(m.getRGB(i, j)));
            }
        }
    }


    /**
     * applique une trsnformation sur un pixel. Ici le pixel retourne est le pixel source, à modifier
     * @param pixel le pixel source
     * @return le pixel modifie
     */
    public int pixelOperation(int pixel){
        float rt = 0, gt = 0, bt = 0;


		// Chaque pixel est un entier (4 octets), alpha channel in bits 24-31, red channels in 16-23, green in 8-15 and blue in 0-7
		// l'operateur & fait le ET logique, donc permet de conserver que la composante qui nous interesse
		// x >> n effectue un décalage de n bit vers la droite de la valeur x
		// dans notre cas permet de ne conserver que les composantes qui nous interessent
		// par exemple x >> 16, décalage de 16 bits, soit 2 octets, permet de récupérer la composante rouge qui était sur le troisième octets            
        rt += (float) ((pixel & 0x00ff0000) >> 16) ;
        gt += (float) ((pixel & 0x0000ff00) >> 8);
        bt += (float) ((pixel & 0x000000ff) >> 0) ;
        

        // A COMPLETER POUR MODIFIER LE PIXEL
        //grayscale
        float gray = (rt + gt + bt) / 3;
        rt = gray;
        gt = gray;
        bt = gray;

        //reconstitution du pixel 
        int dpixel = (0xff000000)
            | (((int) rt) << 16)
            | (((int) gt) << 8)
            | (((int) bt) << 0);
        return dpixel;
    }

    private boolean shouldSplit() {
        return (xFin - xDebut > 5);
    }

    public int subHeight() {
        return (m.getHeight() - xDebut) / 2;
    }

    public int subWidth() {
        return (m.getWidth() - yDebut) / 2;
    }


}
