import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import javax.imageio.ImageIO;

public class ImageFiltre {

    private int[] mSource;
    private int[] mDestination;

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

        //reconstitution du pixel 
        int dpixel = (0xff000000)
            | (((int) rt) << 16)
            | (((int) gt) << 8)
            | (((int) bt) << 0);
        return dpixel;
    }


    /**
     * Application d'un filtre sur une image
     * @param srcImage image source
     * @return image modifie
     */
    public BufferedImage filter(BufferedImage srcImage){
        int w = srcImage.getWidth();
        int h = srcImage.getHeight();

        mSource = srcImage.getRGB(0, 0, w, h, null, 0, w);
        mDestination = new int[mSource.length];

        for (int index = 0; index < mSource.length; index++) {
    	    mDestination[index] = pixelOperation(mSource[index]);    
        }

        BufferedImage dstImage = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
        dstImage.setRGB(0, 0, w, h, mDestination, 0, w);

        return dstImage;
    }
    
}
