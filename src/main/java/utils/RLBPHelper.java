package utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.util.Vector;

public class RLBPHelper {
    private Vector<byte[]> patterns = new Vector<>();

    //Convert pixel from RGB to Gray
    public static double getGrayFromRGB(int R, int G, int B) {
        return (R + G + B) / 3.0;
    }

    //Convert the image from RGB to gray(IG)
    public static Vector<double[]> RGBtoGrayImage(BufferedImage bi) {
        Vector<double[]> result = new Vector<>();
        for (int x = 0; x < bi.getWidth(); x++) {
            double[] row = new double[bi.getHeight()];
            for (int y = 0; y < bi.getHeight(); y++) {
                Color RGB = new Color(bi.getRGB(x, y));
                double gray = getGrayFromRGB(RGB.getRed(), RGB.getGreen(), RGB.getBlue());
                row[y] = gray;
            }
            result.add(row);
        }

        return result;
    }

    //get the binary RLBP for a given pixel and it's neighbours
    public static byte[] getPattern(double C, double[] N){
        byte[] result = new byte[8];

        int indexMax = 0;
        double maxValue = N[0];

        for(int i = 1; i < N.length; i++){
            if(N[i] > maxValue){
                maxValue = N[i];
                indexMax = i;
            }
        }

        for(int i = indexMax; i <= indexMax + 7; i++){
            int k = i % N.length;
            byte b = C > N[k] ? (byte) 0 : (byte) 1;
            result[i-indexMax] = b;
        }

        return result;
    }

    //Apply RLBP on IG and get the IPattern
    public static Vector<Vector<byte[]>> ApplyRLBP(Vector<double[]> image) {
        Vector<Vector<byte[]>> result = new Vector<>();

        for (int x = 1; x < image.size() - 1; x++) {
            Vector<byte[]> row = new Vector<>();
            for (int y = 1; y < image.get(x).length - 1; y++) {
                double[] neighbours = new double[]{image.get(x-1)[y],image.get(x-1)[y+1],image.get(x)[y+1],image.get(x+1)[y+1],image.get(x+1)[y],image.get(x+1)[y-1],image.get(x)[y-1],image.get(x-1)[y-1]};
                byte[] pattern = getPattern(image.get(x)[y], neighbours);
                row.add(pattern);
            }
            result.add(row);
        }

        return result;
    }

    //Get Texture Features histogram from the
    public static Vector<int[]> getTextureHistogram(Vector<Vector<byte[]>> patterns){
        int[] features = new int[290];

        for(int x = 0; x < patterns.size(); x++){
            for(int y = 0; y < patterns.get(x).size(); y++){

            }
        }
    }
}
