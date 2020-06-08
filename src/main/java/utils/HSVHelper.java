package utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.stream.IntStream;

public class HSVHelper {
    public static float[] getHSVFromRGB(int R, int G, int B) {
        float[] hsv = new float[4];
        Color.RGBtoHSB(R, G, B, hsv);
        hsv[0] = hsv[0] * 360;

        return hsv;
    }

    public static double[] getHSVHistogram(BufferedImage bi) {
        double[] histogram = new double[70];
        for (int x = 0; x < bi.getWidth(); x++) {
            for (int y = 0; y < bi.getHeight(); y++) {
                Color RGB = new Color(bi.getRGB(x, y));
                float[] HSV = getHSVFromRGB(RGB.getRed(), RGB.getGreen(), RGB.getBlue());
                int result = quantizeHSV(HSV[0], HSV[1]);
                histogram[result] += 1;
            }
        }
        return histogram;
    }

    public static int quantizeHSV(float H, float S){
        int QH = quantizeH(H);
        int QS = quantizeS(S);

        return (3*3*QH + 3*QS);
    }

    public static int quantizeH(float H){
        if(H <= 24.0f || H >= 345.0f){
            return 0;
        }
        if(H > 24.0f && H <= 49.0f){
            return 1;
        }
        if(H > 49.0f && H <= 79.0f){
            return 2;
        }
        if(H > 79.0f && H <= 159.0f){
            return 3;
        }
        if(H > 159.0f && H <= 194.0f){
            return 4;
        }
        if(H > 194.0f && H <= 264.0f){
            return 5;
        }
        if(H > 264.0f && H <= 284.0f){
            return 6;
        }
        return 7;
    }
    public static int quantizeS(float S){
        if(S <= 0.15f){
            return 0;
        }
        if(S <= 0.8f){
            return 1;
        }
        return 2;
    }
}
