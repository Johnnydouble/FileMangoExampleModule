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
        PseudoColor totalColors = new PseudoColor(0,0,0);
        for (int i = 0; i < maxVerticalIncrements; i++) {
            for (int j = 0; j < maxHorizontalIncrements; j++) {
                int x = i * incrementX;
                int y = j * incrementY;
                Color c = new Color(img.getRGB(x,y));
                totalColors = addColor(totalColors, c);
            }
        }

        //return color average
        return divideColor(totalColors, incrementsX*incrementsX).toColor();
    }

    private PseudoColor addColor(PseudoColor x, Color y) {
        int r = x.r + y.getRed();
        int g = x.g + y.getGreen();
        int b = x.b + y.getBlue();
        return new PseudoColor(r,g,b);
    }

    private PseudoColor divideColor(PseudoColor x, int d){
        int r = x.r / d;
        int g = x.g / d;
        int b = x.b / d;
        return new PseudoColor(r,g,b);
    }
}


class PseudoColor{
    public int r;
    public int g;
    public int b;

    PseudoColor(int r, int g, int b){
        this.r = r;
        this.g = g;
        this.b = b;
    }

    PseudoColor(Color x){
        int r = x.getRed();
        int g = x.getGreen();
        int b = x.getBlue();
    }

    public Color toColor(){
        return new Color(r,g,b);
    }
}
