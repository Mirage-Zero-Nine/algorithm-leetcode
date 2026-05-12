package solution.math;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class SampleStats1093Test {

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
}
