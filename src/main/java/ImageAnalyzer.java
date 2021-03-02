import java.awt.*;
import java.awt.image.BufferedImage;


public class ImageAnalyzer {
    private static final int maxVerticalIncrements = 20;
    private static final int maxHorizontalIncrements = 20;

    ImageAnalyzer(){

    }
    public Color analyze(BufferedImage img){
        //get increments
        int incrementsX = maxHorizontalIncrements;
        int incrementsY = maxVerticalIncrements;

        if (img.getWidth() < maxHorizontalIncrements)
            incrementsX = img.getWidth();
        if (img.getHeight() < maxVerticalIncrements)
            incrementsY = img.getHeight();

        //get increment sizes
        int incrementX = img.getWidth() / incrementsX;
        int incrementY = img.getHeight() / incrementsY;

        //sample and sum colors
        Color totalColors = new Color(0,0,0);
        for (int i = 0; i < maxVerticalIncrements; i++) {
            for (int j = 0; j < maxHorizontalIncrements; j++) {
                int x = i * incrementX;
                int y = j * incrementY;
                Color c = new Color(img.getRGB(x,y));
                totalColors = addColor(totalColors, c);
            }
        }

        //return color average
        return divideColor(totalColors, incrementsX*incrementsX);
    }

    private Color addColor(Color x, Color y) {
        int a = x.getAlpha() + y.getAlpha();
        int r = x.getRed() + y.getRed();
        int g = x.getGreen() + y.getGreen();
        int b = x.getBlue() + y.getBlue();
        return new Color(a,r,g,b);
    }

    private Color divideColor(Color x, int d){
        int a = x.getAlpha() / d;
        int r = x.getRed() / d;
        int g = x.getGreen() / d;
        int b = x.getBlue() / d;
        return new Color(a,r,g,b);
    }
}
