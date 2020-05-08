import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Vector;

import org.apache.commons.exec.*;
import org.bytedeco.javacv.FrameGrabber.Exception;
import utils.*;
import org.apache.commons.lang3.ArrayUtils;

import javax.imageio.ImageIO;

import static utils.FrameComparisson.normalize;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        VideoReader videoReader = new VideoReader("E:\\1917.mp4");
        int shotCount = 0;
        int frame = 0;
        BufferedImage newImg = videoReader.getNextFrame();

        double[] prevHistogram = normalize(ArrayUtils.addAll(HSVHelper.RGBtoHSVImage(newImg), URLBPHelper.get_URLBP_Features(newImg)));

        newImg = videoReader.getNextFrame();
        ImageIO.write(newImg, "png", new File("F:\\movie2\\img" + shotCount + "_" + frame + ".png"));

        while(shotCount < 1){
            double[] newHistogram = normalize(ArrayUtils.addAll(HSVHelper.RGBtoHSVImage(newImg), URLBPHelper.get_URLBP_Features(newImg)));
            double difference = FrameComparisson.getEuclideanDistance(prevHistogram, newHistogram);

            prevHistogram = newHistogram;

            if(difference > 0.3) {
                shotCount++;
                ImageIO.write(newImg, "png", new File("F:\\movie2\\img" + shotCount + "_" + frame + ".png"));
            }
            frame += 1;
            if(frame % 100 == 0)
                ImageIO.write(newImg, "png", new File("F:\\movie2\\img" + shotCount + "_" + frame + ".png"));
            newImg = videoReader.getNextFrame();
        }

        ImageIO.write(newImg, "png", new File("F:\\movie2\\2.png"));
        System.out.println(shotCount);

        PythonRunner.runDarknet();
        System.out.println("alo");
    }
}