package solutions.math;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SampleStats_1093Test {

    private final SampleStats_1093 test = new SampleStats_1093();

    @Test
    public void testHappyCases() {
        int[] count = new int[256];
        count[0] = 3; count[255] = 2; count[100] = 1;
        double[] result = test.sampleStats(count);
        assertArrayEquals(new double[]{0, 255, 101.66666666666667, 50.0, 0.0}, result, 0.0001);
    }

    @Test
    public void testEdgeCases() {
        int[] count = new int[256];
        count[5] = 1;
        double[] result = test.sampleStats(count);
        assertArrayEquals(new double[]{5, 5, 5.0, 5.0, 5.0}, result, 0.0001);
    }

    @Test
    public void testLargeCase() {
        int[] count = new int[256];
        for (int i = 0; i < 256; i++) count[i] = 1;
        double[] result = test.sampleStats(count);
        assertArrayEquals(new double[]{0, 255, 127.5, 127.5, 0.0}, result, 0.5);
    }

    @Test
    public void testTwoElements() {
        int[] count = new int[256];
        count[10] = 1; count[20] = 1;
        double[] result = test.sampleStats(count);
        assertArrayEquals(new double[]{10, 20, 15.0, 15.0, 10.0}, result, 0.0001);
    }

    @Test
    public void testOddNumberOfElements() {
        int[] count = new int[256];
        count[1] = 1; count[2] = 1; count[3] = 1;
        double[] result = test.sampleStats(count);
        assertArrayEquals(new double[]{1, 3, 2.0, 2.0, 1.0}, result, 0.0001);
    }

    @Test
    public void testModeIsHighest() {
        int[] count = new int[256];
        count[5] = 2; count[10] = 5; count[15] = 3;
        double[] result = test.sampleStats(count);
        assertEquals(10.0, result[4], 0.0001); // mode
    }

    @Test
    public void testNullInput() {
        double[] result = test.sampleStats(null);
        assertArrayEquals(new double[5], result, 0.0001);
    }

    @Test
    public void testEmptyInput() {
        double[] result = test.sampleStats(new int[0]);
        assertArrayEquals(new double[5], result, 0.0001);
    }

    @Test
    public void testAllSameValue() {
        int[] count = new int[256];
        count[100] = 50;
        double[] result = test.sampleStats(count);
        assertArrayEquals(new double[]{100, 100, 100.0, 100.0, 100.0}, result, 0.0001);
    }

    @Test
    public void testGiantCounts() {
        int[] count = new int[256];
        count[0] = 1000000;
        count[255] = 1000000;
        double[] result = test.sampleStats(count);
        assertEquals(0.0, result[0], 0.0001); // min
        assertEquals(255.0, result[1], 0.0001); // max
        assertEquals(127.5, result[2], 0.0001); // mean
        assertEquals(127.5, result[3], 0.0001); // median
    }

    @Test
    public void testAdditional1() {
        assertArrayEquals(new double[]{0,0,0,0,0}, test.sampleStats(new int[]{1,0,0,0,0}), 0.0001);
    }

    @Test
    public void testAdditional2() {
        assertArrayEquals(new double[]{1,2,1.5,1.5,1}, test.sampleStats(new int[]{0,1,1}), 0.0001);
    }

    @Test
    public void testAdditional3() {
        assertArrayEquals(new double[]{2,4,2.6666666667,2,2}, test.sampleStats(new int[]{0,0,2,0,1}), 0.0001);
    }

    @Test
    public void testAdditional4() {
        assertArrayEquals(new double[]{3,3,3,3,3}, test.sampleStats(new int[]{0,0,0,5}), 0.0001);
    }

    @Test
    public void testAdditional5() {
        assertArrayEquals(new double[]{0,2,1,1,0}, test.sampleStats(new int[]{1,0,1}), 0.0001);
    }

    @Test
    public void testAdditional6() {
        assertArrayEquals(new double[]{1,3,2,2,1}, test.sampleStats(new int[]{0,2,0,2}), 0.0001);
    }

    @Test
    public void testAdditional7() {
        assertArrayEquals(new double[]{0,3,1.5,1.5,0}, test.sampleStats(new int[]{1,1,1,1}), 0.0001);
    }

    @Test
    public void testAdditional8() {
        assertArrayEquals(new double[]{4,4,4,4,4}, test.sampleStats(new int[]{0,0,0,0,10}), 0.0001);
    }

    @Test
    public void testAdditional9() {
        assertArrayEquals(new double[]{0,5,2.5,2.5,0}, test.sampleStats(new int[]{1,0,0,0,0,1}), 0.0001);
    }

    @Test
    public void testAdditional10() {
        assertArrayEquals(new double[]{1,6,3.5,3.5,1}, test.sampleStats(new int[]{0,1,0,0,0,0,1}), 0.0001);
    }
}
