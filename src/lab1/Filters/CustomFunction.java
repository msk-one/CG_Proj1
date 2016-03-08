package lab1.Filters;

import javafx.scene.chart.XYChart;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by mskas on 02.03.2016.
 */
public class CustomFunction {

    public static BufferedImage transformImageWithSeries(XYChart.Series<Number, Number> series, BufferedImage img) {
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

                int a = 0;
                int r = 0;
                int g = 0;
                int b = 0;

                for (XYChart.Data<Number, Number> data : series.getData()) {
                    if(a+1 < series.getData().size()) {
                        if (red > (int) data.getXValue() && red <= (int) series.getData().get(a + 1).getXValue()) {
                            r = a;
                        }
                    }
                    a++;
                }

                // comes from y=(((yB-yA)(x-xA))/(xB-xA))+yA
                // equation for linear expression
                red = ((((series.getData().get(r+1).getYValue().intValue()-series.getData().get(r).getYValue().intValue())*(red - series.getData().get(r).getXValue().intValue()))/(series.getData().get(r+1).getXValue().intValue()-series.getData().get(r).getXValue().intValue()))+(series.getData().get(r).getYValue().intValue()));

                a = 0;
                for (XYChart.Data<Number, Number> data : series.getData()) {
                    if(a+1 < series.getData().size()) {
                        if (green > (int) data.getXValue() && green <= (int) series.getData().get(a + 1).getXValue()) {
                            g = a;
                        }
                    }
                    a++;
                }

                green = ((((series.getData().get(g+1).getYValue().intValue()-series.getData().get(g).getYValue().intValue())*(green - series.getData().get(g).getXValue().intValue()))/(series.getData().get(g+1).getXValue().intValue()-series.getData().get(g).getXValue().intValue()))+(series.getData().get(g).getYValue().intValue()));

                a = 0;
                for (XYChart.Data<Number, Number> data : series.getData()) {
                    if(a+1 < series.getData().size()) {
                        if (blue > (int) data.getXValue() && blue <= (int) series.getData().get(a + 1).getXValue()) {
                            b = a;
                        }
                    }
                    a++;
                }

                blue = ((((series.getData().get(b+1).getYValue().intValue()-series.getData().get(b).getYValue().intValue())*(blue - series.getData().get(b).getXValue().intValue()))/(series.getData().get(b+1).getXValue().intValue()-series.getData().get(b).getXValue().intValue()))+(series.getData().get(b).getYValue().intValue()));;

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
}
