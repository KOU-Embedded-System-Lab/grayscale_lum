import java.awt.image.BufferedImage;

/**
 * Created by asus on 01-Aug-17.
 * this method relies in calculating the values based on luminosity .
 * is a more sophisticated version
 *  people are more sensitive to green so
 *  we give agmake green weight more
 */
import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;
public class luminance {
    public static int colorToRGB(int alpha, int red, int green, int blue) {

        int newPixel = 0;
        newPixel += alpha;
        newPixel = newPixel << 8;
        newPixel += red;
        newPixel = newPixel << 8;
        newPixel += green;
        newPixel = newPixel << 8;
        newPixel += blue;
        //  System.out.println("color2rgb "+newPixel);
        return newPixel;

    }
    public static void main (String []args){
            int alpha, red, green, blue;
            int newPixel;
            BufferedImage original  = null ;
            File f = null;

            try {
                f = new File("D:\\Image\\sunflower.jpg");
                // original image is inhere
                original  = ImageIO.read(f);
            } catch (IOException e) {
                System.out.println(e);
            }
            BufferedImage lum = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());

            for(int i=0; i<original.getWidth(); i++) {
                for(int j=0; j<original.getHeight(); j++) {

                    // Get pixels by R, G, B
                    alpha = new Color(original.getRGB(i, j)).getAlpha();
                    red = new Color(original.getRGB(i, j)).getRed();
                    green = new Color(original.getRGB(i, j)).getGreen();
                    blue = new Color(original.getRGB(i, j)).getBlue();

                    red = (int) (0.21 * red + 0.71 * green + 0.07 * blue);

                    newPixel = colorToRGB(alpha, red, red, red);

                    lum.setRGB(i, j, newPixel);

                }
            }
            try{
                f = new File("D:\\Image\\yes_done1.jpg");
                ImageIO.write(lum, "jpg", f);
            }catch(IOException e){
                System.out.println(e);
            }


    }
}
