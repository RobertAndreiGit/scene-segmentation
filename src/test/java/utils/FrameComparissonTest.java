package utils;

import org.junit.AfterClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class FrameComparissonTest {

    @AfterClass
    public static void printResults() {
        System.out.println("FrameComparisson tests ran successfully!");
    }

    @Test
    public void TestGetEuclideanDistance() {
        //Test Valid Cases
        assertEquals(1, FrameComparisson.getEuclideanDistance(new double[]{1,1}, new double[] {1, 2}), 0);
        assertEquals(Math.sqrt(105), FrameComparisson.getEuclideanDistance(new double[]{7, 4, 3}, new double[] {17, 6, 2}), 0);
        assertEquals(Math.sqrt(676), FrameComparisson.getEuclideanDistance(new double[]{-7, -4}, new double[] {17, 6}), 0);

        //Test Invalid - Different Size
        assertEquals(-1, FrameComparisson.getEuclideanDistance(new double[]{1}, new double[] {1, 2}), 0);

        //Test Invalid - Different Size + Empty
        assertEquals(-1, FrameComparisson.getEuclideanDistance(new double[]{1}, new double[] {}), 0);

        //Test Invalid - Empty + Same Size
        assertEquals(-1, FrameComparisson.getEuclideanDistance(new double[]{}, new double[] {}), 0);
    }

    @Test
    public void TestNormalize() {
        //Test Basic Array
        double[] normalized = FrameComparisson.normalize(new double[]{1,5,1});
        assertEquals(normalized[0], normalized[2], 0);
        assertEquals(0, normalized[0], 0);
        assertEquals(1, normalized[1], 0);

        //Test Basic Array
        normalized = FrameComparisson.normalize(new double[]{1,3,5});
        assertEquals(0, normalized[0], 0);
        assertEquals(0.5, normalized[1], 0);
        assertEquals(1, normalized[2], 0);

        //Test Complex Array
        normalized = FrameComparisson.normalize(new double[]{1.08, 1.76, 2.64, 3.69, 4.20, 5.55, 6.12, 9.17, 54.4});
        assertEquals(9, normalized.length);
        assertEquals(0, normalized[0], 0);
        assertEquals((1.76-1.08)/(53.32), normalized[1], 0);
        assertEquals((2.64-1.08)/(53.32), normalized[2], 0);
        assertEquals((3.69-1.08)/(53.32), normalized[3], 0);
        assertEquals((4.20-1.08)/(53.32), normalized[4], 0);
        assertEquals((5.55-1.08)/(53.32), normalized[5], 0);
        assertEquals((6.12-1.08)/(53.32), normalized[6], 0);
        assertEquals((9.17-1.08)/(53.32), normalized[7], 0);
        assertEquals((54.4-1.08)/(53.32), normalized[8], 0);

        //Test Empty Array
        assertEquals(0, FrameComparisson.normalize(new double[]{}).length);
    }
}