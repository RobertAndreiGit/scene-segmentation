package utils;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class HSVHelperTest {

    @AfterClass
    public static void tearDown() {
        System.out.println("HSVHelper tests ran successfully!");
    }

    @Test
    public void getHSVFromRGB() {
        float[] hsv = HSVHelper.getHSVFromRGB(73,162,26);
        assertEquals(99, hsv[0], 0.51);
        assertEquals(0.84, hsv[1], 0.001);
        assertEquals(.635, hsv[2], 0.001);

        hsv = HSVHelper.getHSVFromRGB(255,255,255);
        assertEquals(0, hsv[0], 0);
        assertEquals(0, hsv[1], 0);
        assertEquals(1, hsv[2], 0);
    }

    @Test
    public void quantizeHSV() {
        assertEquals(0, HSVHelper.quantizeHSV(0,0));

        assertEquals(3, HSVHelper.quantizeHSV(0,0.151f));
        assertEquals(6, HSVHelper.quantizeHSV(0,0.81f));

        assertEquals(9, HSVHelper.quantizeHSV(24.1f,0));
        assertEquals(18, HSVHelper.quantizeHSV(49.1f,0));
        assertEquals(27, HSVHelper.quantizeHSV(79.1f,0));
        assertEquals(36, HSVHelper.quantizeHSV(159.1f,0));
        assertEquals(45, HSVHelper.quantizeHSV(194.1f,0));
        assertEquals(54, HSVHelper.quantizeHSV(264.1f,0));
        assertEquals(63, HSVHelper.quantizeHSV(284.1f,0));

        assertEquals(69, HSVHelper.quantizeHSV(284.1f,0.81f), 0);
    }

    @Test
    public void quantizeH() {
        assertEquals(0, HSVHelper.quantizeH(0));
        assertEquals(0, HSVHelper.quantizeH(345f));
        assertEquals(1, HSVHelper.quantizeH(24.1f));
        assertEquals(2, HSVHelper.quantizeH(49.1f));
        assertEquals(3, HSVHelper.quantizeH(79.1f));
        assertEquals(4, HSVHelper.quantizeH(159.1f));
        assertEquals(5, HSVHelper.quantizeH(194.1f));
        assertEquals(6, HSVHelper.quantizeH(264.1f));
        assertEquals(7, HSVHelper.quantizeH(284.1f));
    }

    @Test
    public void quantizeS() {
        assertEquals(0, HSVHelper.quantizeS(0));
        assertEquals(0, HSVHelper.quantizeS(.15f));
        assertEquals(1, HSVHelper.quantizeS(.151f));
        assertEquals(1, HSVHelper.quantizeS(.8f));
        assertEquals(2, HSVHelper.quantizeS(.81f));
        assertEquals(2, HSVHelper.quantizeS(1f));
    }
}