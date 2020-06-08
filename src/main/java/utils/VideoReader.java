package utils;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameConverter;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class VideoReader {
    FFmpegFrameGrabber frameGrabber;
    Frame currentFrame;
    Java2DFrameConverter converter;

    public VideoReader(String filename) throws IOException {
        frameGrabber = new FFmpegFrameGrabber(filename);
        frameGrabber.start();
        converter = new Java2DFrameConverter();
    }

    public BufferedImage getNextFrame() throws FrameGrabber.Exception {
        currentFrame = frameGrabber.grabImage();
        BufferedImage bi = converter.getBufferedImage(currentFrame);
        return converter.getBufferedImage(currentFrame);
    }
}
