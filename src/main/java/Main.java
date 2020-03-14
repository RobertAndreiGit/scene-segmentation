import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import org.bytedeco.javacv.FrameGrabber.Exception;
import utils.FrameComparisson;
import utils.HSVHelper;
import utils.URLBPHelper;
import utils.VideoReader;
import org.apache.commons.lang3.ArrayUtils;

import javax.imageio.ImageIO;

public class Main {
    public static void main(String[] args) throws IOException {
        VideoReader videoReader = new VideoReader("E:\\1917.mp4");
        int shotCount = 0;
        int frame = 0;
        BufferedImage newImg = videoReader.getNextFrame();

        double[] prevHistogram = normalize(ArrayUtils.addAll(HSVHelper.RGBtoHSVImage(newImg), URLBPHelper.get_URLBP_Features(newImg)));

        newImg = videoReader.getNextFrame();
        ImageIO.write(newImg, "png", new File("F:\\movie2\\img" + shotCount + "_" + frame + ".png"));

        while(shotCount< 1){
            double[] newHistogram = normalize(ArrayUtils.addAll(HSVHelper.RGBtoHSVImage(newImg), URLBPHelper.get_URLBP_Features(newImg)));
            double difference = FrameComparisson.getEuclideanDistance(prevHistogram, newHistogram);

            prevHistogram = newHistogram;

            if(difference > 0.3) {
                shotCount++;
                ImageIO.write(newImg, "png", new File("F:\\movie2\\1.png"));
            }
            newImg = videoReader.getNextFrame();
        }

        ImageIO.write(newImg, "png", new File("F:\\movie2\\2.png"));
        System.out.println(shotCount);
    }

    public static double[] normalize(double[]arr){
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;


        for (int i = 0; i < arr.length; i++) {
                max = Math.max(arr[i], max);
                min = Math.min(arr[i], min);
        }

        for (int i = 0; i < arr.length; i++) {
                arr[i] = (arr[i] - min)/(max-min);
        }

        return arr;
    }
}