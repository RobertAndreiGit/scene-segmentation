package utils;

import cern.colt.map.OpenIntIntHashMap;
import org.junit.Test;

import static org.junit.Assert.*;

public class URLBPHelperTest {

    @Test
    public void getGrayFromRGB() {
        assertEquals(100, URLBPHelper.getGrayFromRGB(100,100,100), 0);
        assertEquals(100, URLBPHelper.getGrayFromRGB(0,100,200), 0);
        assertEquals(95.666666, URLBPHelper.getGrayFromRGB(125,63,99), 0.00001);

        assertEquals(-1, URLBPHelper.getGrayFromRGB(125,-1,99), 0.00001);
        assertEquals(-1, URLBPHelper.getGrayFromRGB(125,63,265), 0.00001);
        assertEquals(-1, URLBPHelper.getGrayFromRGB(1215,63,99), 0.00001);
    }

    @Test
    public void RGBtoGrayImage() {
        assertEquals(null, URLBPHelper.RGBtoGrayImage(null));
    }

    @Test
    public void getURLBPFeatures() {
        assertEquals(null, URLBPHelper.getURLBPHistogram(null));
    }

    @Test
    public void isBinaryUniform() {
        int[] arr = new int[]{0,0,0,0,0,0,0,0};
        assertTrue(URLBPHelper.isBinaryUniform(arr));

        arr = new int[]{0,0,0,0,0,0,0,1};
        assertTrue(URLBPHelper.isBinaryUniform(arr));

        arr = new int[]{0,0,0,0,0,1,0,1};
        assertFalse(URLBPHelper.isBinaryUniform(arr));

        arr = new int[]{0,0,0,0,0,0};
        assertFalse(URLBPHelper.isBinaryUniform(arr));

        arr = new int[]{};
        assertFalse(URLBPHelper.isBinaryUniform(arr));

        arr = new int[]{0,0,0,0,0,0,0,0};
        assertFalse(URLBPHelper.isBinaryUniform(null));
    }

    @Test
    public void convertToBinary() {
        int[] arr = URLBPHelper.convertToBinary (10);
        assertEquals(0 ,arr[0]);
        assertEquals(0 ,arr[1]);
        assertEquals(0 ,arr[2]);
        assertEquals(0 ,arr[3]);
        assertEquals(1 ,arr[4]);
        assertEquals(0 ,arr[5]);
        assertEquals(1 ,arr[6]);
        assertEquals(0 ,arr[7]);
    }

    @Test
    public void getHistogramTemplate() {
        OpenIntIntHashMap map = URLBPHelper.getHistogramTemplate();
        assertTrue(map.containsKey(999));
        assertEquals(0, map.get(999));
    }
}