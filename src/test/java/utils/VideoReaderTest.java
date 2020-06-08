package utils;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class VideoReaderTest {
    @AfterClass
    public static void tearDown() throws Exception {
        System.out.println("VideoReader class tests ran successfully!");
    }

    @Test(expected = IOException.class)
    public void TestClassConstructor() throws IOException{
            new VideoReader("this_video_does_not_exist.mp4");
    }
}