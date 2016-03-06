package lab1.Filters;

import java.awt.*;
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

    public static BufferedImage convolve2D(BufferedImage img, double[][] kernel) {
        int alpha = 0;
        int red = 0;
        int green = 0;
        int blue = 0;
        int newRGB = 0;

        int sumR = 0;
        int sumG = 0;
        int sumB = 0;
        double divisor = 0;

        BufferedImage workCpy = Helpers.copyBufferedImage(img);

        for (int a = 0; a < kernel.length; a++) {
            for (int b = 0; b < kernel.length; b++) {
                divisor += kernel[a][b];
            }
        }

        if(divisor == 0) {
            divisor = 1;
        }

        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                alpha = new Color(img.getRGB(i, j)).getAlpha();

                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {
                        if(x+i >= 0 && x+i < img.getWidth() && y+j >= 0 && y+j < img.getHeight()) {
                            sumR += kernel[x+1][y+1] * new Color(img.getRGB(x + i, y + j)).getRed();
                            sumG += kernel[x+1][y+1] * new Color(img.getRGB(x + i, y + j)).getGreen();
                            sumB += kernel[x+1][y+1] * new Color(img.getRGB(x + i, y + j)).getBlue();
                        }
                    }
                }

                red = (int) (sumR/divisor);
                green = (int) (sumG/divisor);
                blue = (int) (sumB/divisor);

                if (red > 255) {
                    red = 255;
                }
                if (green > 255) {
                    green = 255;
                }
                if (blue > 255) {
                    blue = 255;
                }

                if (red < 0) {
                    red = 0;
                }
                if (green < 0) {
                    green = 0;
                }
                if (blue < 0) {
                    blue = 0;
                }

                newRGB = Helpers.colorToRGBint(alpha, red, green, blue);
                workCpy.setRGB(i, j, newRGB);

                sumR = 0;
                sumG = 0;
                sumB = 0;
            }
        }

        return workCpy;
    }
}
