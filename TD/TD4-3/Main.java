import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

public class Main {

    public static void main(String[] args) throws Exception {
        ForkJoinPool pool = new ForkJoinPool();

        //Lecture de l'image source
        String srcName = "lamantin.jpg";
        File srcFile = new File(srcName);
        BufferedImage image = ImageIO.read(srcFile);

        TraitementImage task = new TraitementImage(image);
        pool.invoke(task);

        //Ecriture de l'image resultat
        String dstName = "mon-image-filtre.jpg";
        writeImage(image, dstName); 
    }

    public static void writeImage(BufferedImage image, String dstName) throws Exception {
        File dstFile = new File(dstName); 
        ImageIO.write(image, "jpg", dstFile);      
    }
}