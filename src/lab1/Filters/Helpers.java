package lab1.Filters;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

/**
 * Created by mskas on 05.03.2016.
 */
public class Helpers {
    //CHANGE VALUES FOR DEFAULT FUNCTION FILTERS HERE:
    //Defaults to: 10
    public static final int brightCoeff = 10;
    //Defaults to: 1.5
    public static final double contrastCoeff = 1.5;
    //Defaults to: 0.5
    public static final double gamma = 0.5;

    public static BufferedImage copyBufferedImage (BufferedImage buff) {
        ColorModel col = buff.getColorModel();
        WritableRaster raster = buff.copyData(null);

        return new BufferedImage(col, raster, col.isAlphaPremultiplied(), null);
    }

    public static int colorToRGBint(int alpha, int red, int green, int blue) {
        int RGBint = 0;

        RGBint += alpha;
        RGBint = RGBint << 8;
        RGBint += red;
        RGBint = RGBint << 8;
        RGBint += green;
        RGBint = RGBint << 8;
        RGBint += blue;

        return RGBint;
    }

    public static int gammaFunction(int x, double gamma) {
        return (int) (255 * (Math.pow((double) x / (double) 255, gamma)));
    }
}
