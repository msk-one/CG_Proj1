package lab1.Filters;

import javafx.scene.image.Image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

/**
 * Created by mskas on 02.03.2016.
 */
public class Filters {
    public static BufferedImage convBlurFilter(BufferedImage img) {
        int[][] matrix = {
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1},
        };

        int alpha = 0;
        int red = 0;
        int green = 0;
        int blue = 0;
        int newRGB = 0;

        int sumR = 0;
        int sumG = 0;
        int sumB = 0;

        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                alpha = new Color(img.getRGB(i, j)).getAlpha();
                red = new Color(img.getRGB(i, j)).getRed();
                green = new Color(img.getRGB(i, j)).getGreen();
                blue = new Color(img.getRGB(i, j)).getBlue();

                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        if(x+i >= 0 && x+i < img.getWidth() && y+j >= 0 && y+j < img.getHeight()) {
                            sumR += matrix[x][y] * new Color(img.getRGB(x + i, y + j)).getRed();
                            sumG += matrix[x][y] * new Color(img.getRGB(x + i, y + j)).getGreen();
                            sumB += matrix[x][y] * new Color(img.getRGB(x + i, y + j)).getBlue();
                        }
                    }
                }

                red = sumR/9;
                green = sumG/9;
                blue = sumB/9;

                newRGB = Helpers.colorToRGBint(alpha, red, green, blue);
                img.setRGB(i, j, newRGB);
            }
        }

        return img;
    }

    public static void convGaussSmoothFilter(Image img) {

    }

    public static void convSharpenFilter(Image img) {

    }

    public static void convEdgeDetectFilter(Image img) {

    }

    public static void convEmbossFilter(Image img) {

    }

    public static BufferedImage funcInversionFilter(BufferedImage img) {
        int alpha = 0;
        int red = 0;
        int green = 0;
        int blue = 0;
        int newRGB = 0;

        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                alpha = new Color(img.getRGB(i, j)).getAlpha();
                red = new Color(img.getRGB(i, j)).getRed();
                green = new Color(img.getRGB(i, j)).getGreen();
                blue = new Color(img.getRGB(i, j)).getBlue();

                red = 255 - red;
                green = 255 - green;
                blue = 255 - blue;

                newRGB = Helpers.colorToRGBint(alpha, red, green, blue);
                img.setRGB(i, j, newRGB);
            }
        }

        return img;
    }

    public static BufferedImage funcBrightCorrFilter(BufferedImage img) {
        int alpha = 0;
        int red = 0;
        int green = 0;
        int blue = 0;
        int newRGB = 0;

        //Defaults to: 10
        int brightCoeff = Helpers.brightCoeff;

        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                alpha = new Color(img.getRGB(i, j)).getAlpha();
                red = new Color(img.getRGB(i, j)).getRed();
                green = new Color(img.getRGB(i, j)).getGreen();
                blue = new Color(img.getRGB(i, j)).getBlue();

                red += brightCoeff;
                green += brightCoeff;
                blue += brightCoeff;

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
                img.setRGB(i, j, newRGB);
            }
        }

        return img;
    }

    public static BufferedImage funcContrastEnchFilter(BufferedImage img) {
        int alpha = 0;
        int red = 0;
        int green = 0;
        int blue = 0;
        int newRGB = 0;

        //Defaults to: 1.5
        double contrastCoeff = Helpers.contrastCoeff;

        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                alpha = new Color(img.getRGB(i, j)).getAlpha();
                red = new Color(img.getRGB(i, j)).getRed();
                green = new Color(img.getRGB(i, j)).getGreen();
                blue = new Color(img.getRGB(i, j)).getBlue();

                red = (int) (red*contrastCoeff);
                green = (int) (green*contrastCoeff);
                blue = (int) (blue*contrastCoeff);

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
                img.setRGB(i, j, newRGB);
            }
        }

        return img;
    }

    public static BufferedImage funcGammaCorrFilter(BufferedImage img) {
        int alpha = 0;
        int red = 0;
        int green = 0;
        int blue = 0;
        int newRGB = 0;

        //Defaults to: 0.5
        double gamma = Helpers.gamma;

        for (int i = 0; i < img.getWidth(); i++) {
            for (int j = 0; j < img.getHeight(); j++) {
                alpha = new Color(img.getRGB(i, j)).getAlpha();
                red = new Color(img.getRGB(i, j)).getRed();
                green = new Color(img.getRGB(i, j)).getGreen();
                blue = new Color(img.getRGB(i, j)).getBlue();

                red = Helpers.gammaFunction(red, gamma);
                green = Helpers.gammaFunction(green, gamma);
                blue = Helpers.gammaFunction(blue, gamma);

                newRGB = Helpers.colorToRGBint(alpha, red, green, blue);
                img.setRGB(i, j, newRGB);
            }
        }

        return img;
    }
}
