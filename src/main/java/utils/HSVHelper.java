package utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Vector;

public class HSVHelper {
    public static float[] getHSVFromRGB(int R, int G, int B) {
        //double h = Math.acos((((R - G) + (R - B))/2.0) / Math.sqrt((Math.pow(R-G, 2)) + (R-B)*(G-B))) * (180/Math.PI);
        //double s = 1 - (3/(R + G + B)) * Math.min(R, Math.min(G,B));
        //double v = (R+G+B) / 3;

        //double[] hsv = {h,s,v};

        float[] hsv = new float[4];
        Color.RGBtoHSB(R, G, B, hsv);
        hsv[0] = hsv[0] * 360;

        //hsv[0] = (float)h;
        return hsv;
    }

    public static double[] RGBtoHSVImage(BufferedImage bi) {
        double[] histogram = new double[70];
        for (int x = 0; x < bi.getWidth(); x++) {
            for (int y = 0; y < bi.getHeight(); y++) {
                Color RGB = new Color(bi.getRGB(x, y));
                float[] HSV = getHSVFromRGB(RGB.getRed(), RGB.getGreen(), RGB.getBlue());
                int result = QuantizedHSV(HSV[0], HSV[1]);
                histogram[result] += 1;
            }
        }
        return histogram;
    }

    public static int QuantizedHSV(float H, float S){
        int QH = getQuantizedH(H);
        int QS = getQuantizedS(S);

        return (3*3*QH + 3*QS);
    }

    public static int getQuantizedH(float H){
        if(H <= 24.0 || H >= 345.0){
            return 0;
        }
        if(H > 24.0 && H <= 49.0){
            return 1;
        }
        if(H > 49.0 && H <= 79.0){
            return 2;
        }
        if(H > 79.0 && H <= 159.0){
            return 3;
        }
        if(H > 159.0 && H <= 194.0){
            return 4;
        }
        if(H > 194.0 && H <= 264.0){
            return 5;
        }
        if(H > 264.0 && H <= 284.0){
            return 6;
        }
        return 7;
    }
    public static int getQuantizedS(float S){
        if(S <= 0.15){
            return 0;
        }
        if(S <= 0.8){
            return 1;
        }
        return 2;
    }
}
