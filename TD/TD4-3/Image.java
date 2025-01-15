import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {

    final BufferedImage img;
    final ColorModel cm;

    public Image(BufferedImage img) {
        this.img = img;
        this.cm = img.getColorModel();
    }

    public Image(File f) throws IOException {
        this(ImageIO.read((f)));
    }

    public class Pixel {

        final int px;

        public Pixel(int p) {
            this.px = p;
        }

        public Pixel(int r, int g, int b) {
            this(cm.getDataElement(new int[] {r, g, b}, 0));
        }

        

    }
    
    public Pixel get(int i, int j) {
        return new Pixel(img.getRGB(i, j));
    }

    public void set(int i, int j, Pixel p) {
        img.setRGB(i, j, p.px);
    }

    public Image getSameButEmpty() {
        return new Image(new BufferedImage(img.getWidth(), img.getHeight(), img.getType()));
    }

    public void write(String fmt, File f) throws IOException {
        ImageIO.write(img, fmt, f);
    }

}
