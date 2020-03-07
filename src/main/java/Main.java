import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Vector;

import org.bytedeco.javacv.FrameGrabber.Exception;
import utils.HSVHelper;
import utils.RLBPHelper;
import utils.VideoReader;

public class Main {
    public static void main(String[] args) throws IOException, Exception {
//        VideoReader videoReader = new VideoReader("F:\\demo.mkv");
//
//        BufferedImage bi = videoReader.getNextFrame();
//        while (bi == null)
//            bi = videoReader.getNextFrame();
//
//        //System.out.println(new Color(bi.getRGB(0, 0)).getRed());
//        //System.out.println(new Color(bi.getRGB(0, 0)).getGreen());
//        //System.out.println(new Color(bi.getRGB(0, 0)).getBlue());
//
//        //float[] result = (HSVHelper.getHSVFromRGB(new Color(bi.getRGB(0, 0)).getRed(), new Color(bi.getRGB(0, 0)).getGreen(), new Color(bi.getRGB(0, 0)).getBlue()));
//        //System.out.println(result[0] * (360));
//        //System.out.println(result[1]);
//        //System.out.println(result[2]);
//
//        Vector<float[]> img = HSVHelper.RGBtoHSVImage(bi);
//        //System.out.println(img.get(0)[0]);
//        //System.out.println(img.get(0)[1]);
//        //System.out.println(img.get(0)[2]);
//        for(int i =0; i<img.size(); i++)
//        System.out.println(img.get(i)[3]);

        double[] arr = new double[]{44,52,221,70,78,53,221,43};
        System.out.println(RLBPHelper.getPattern(63,arr));
    }
}