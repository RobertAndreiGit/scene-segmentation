import org.apache.commons.lang3.ArrayUtils;
import utils.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;

import static utils.FrameComparisson.normalize;

public class AppHandler {
    public static void start(String video_file) {
        try {
            long startTime = System.nanoTime();
            VideoReader videoReader = new VideoReader(video_file);
            int shotCount = 0;
            int frame = 0;
            BufferedImage newImg = videoReader.getNextFrame();

            double[] prevHistogram = normalize(ArrayUtils.addAll(normalize(HSVHelper.getHSVHistogram(newImg)), normalize(URLBPHelper.getURLBPHistogram(newImg))));

            newImg = videoReader.getNextFrame();
            ImageIO.write(newImg, "png", new File("F:\\movie2\\shot" + shotCount + "_" + frame + ".png"));

            ArrayList<String> imgs = new ArrayList<>();
            ArrayList<Hashtable<String, Integer>> shotsData = new ArrayList<>();
            boolean addToLast = false;

            while (true) {
                double[] newHistogram = normalize(ArrayUtils.addAll(normalize(HSVHelper.getHSVHistogram(newImg)), normalize(URLBPHelper.getURLBPHistogram(newImg))));
                double difference = FrameComparisson.getEuclideanDistance(prevHistogram, newHistogram);

                prevHistogram = newHistogram;

                if (difference > 0.5) {
                    if (frame < 30) {
                        addToLast = true;
                    } else {
                        addToLast = false;
                    }

                    FileHandler.writeFile("F:\\Scoala\\Licenta\\yolov3\\2\\darknet\\data\\train.txt", imgs);
                    PythonRunner.runDarknet();
                    Hashtable<String, Integer> content = PythonRunner.getOutputContent("F:/Scoala/Licenta/result.txt");
                    FolderCleaner.cleanFolder("F:\\movie2");
                    imgs.clear();

                    if (addToLast) {
                        shotsData.set(shotsData.size() - 1, PythonRunner.combine(shotsData.get(shotsData.size() - 1), content));
                    } else {
                        shotsData.add(content);
                    }

                    shotCount++;
                    frame = 0;

                    String filename = "F:\\movie2\\shot" + shotCount + "_" + frame + ".png";
                    ImageIO.write(newImg, "png", new File(filename));
                    imgs.add(filename);
                }
                frame += 1;
                if (frame % 50 == 0) {
                    String filename = "F:\\movie2\\shot" + shotCount + "_" + frame + ".png";
                    ImageIO.write(newImg, "png", new File(filename));
                    imgs.add(filename);
                }

                newImg = videoReader.getNextFrame();
                if (newImg == null) {
                    if (frame < 30) {
                        addToLast = true;
                    } else {
                        addToLast = false;
                    }

                    FileHandler.writeFile("F:\\Scoala\\Licenta\\yolov3\\2\\darknet\\data\\train.txt", imgs);
                    PythonRunner.runDarknet();
                    Hashtable<String, Integer> content = PythonRunner.getOutputContent("F:/Scoala/Licenta/result.txt");
                    FolderCleaner.cleanFolder("F:\\movie2");
                    imgs.clear();

                    if (addToLast) {
                        shotsData.set(shotsData.size() - 1, PythonRunner.combine(shotsData.get(shotsData.size() - 1), content));
                    } else {
                        shotsData.add(content);
                    }

                    shotCount++;
                    frame = 0;

                    String filename = "F:\\movie2\\shot" + shotCount + "_" + frame + ".png";
                    ImageIO.write(newImg, "png", new File(filename));
                    imgs.add(filename);
                    break;
                }
            }

            BoundaryDetector.getNumberOfScenes(shotsData);
        } catch (Exception e) {
            System.out.println("SOMETHING WENT WRONG!");

        }
    }
}