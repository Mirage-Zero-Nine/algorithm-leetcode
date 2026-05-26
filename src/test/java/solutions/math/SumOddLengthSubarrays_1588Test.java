package solutions.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SumOddLengthSubarrays_1588Test {

    private final SumOddLengthSubarrays_1588 test = new SumOddLengthSubarrays_1588();

    @Test
    public void testHappyCases() {
        assertEquals(58, test.sumOddLengthSubarrays(new int[]{1, 4, 2, 5, 3}));
        assertEquals(3, test.sumOddLengthSubarrays(new int[]{1, 2}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(10, test.sumOddLengthSubarrays(new int[]{10}));
        assertEquals(1, test.sumOddLengthSubarrays(new int[]{1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(57, test.sumOddLengthSubarrays(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void testTwoElements() {
        assertEquals(5, test.sumOddLengthSubarrays(new int[]{2, 3}));
    }

    @Test
    public void testThreeElements() {
        assertEquals(12, test.sumOddLengthSubarrays(new int[]{1, 2, 3}));
    }

    @Test
    public void testAllSameElements() {
        assertEquals(12, test.sumOddLengthSubarrays(new int[]{2, 2, 2}));
    }

    @Test
    public void testAllOnes() {
        assertEquals(6, test.sumOddLengthSubarrays(new int[]{1, 1, 1}));
    }

    @Test
    public void testSingleZero() {
        assertEquals(0, test.sumOddLengthSubarrays(new int[]{0}));
    }

    @Test
    public void testWithZeros() {
        assertEquals(14, test.sumOddLengthSubarrays(new int[]{0, 1, 0, 1, 0, 1}));
    }

    @Test
    public void testGiantCase() {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) arr[i] = i + 1;
        // Each element arr[i] is added ((100-i)*(i+1)+1)/2 times
        long expected = 0;
        for (int i = 0; i < 100; i++) {
            expected += (long) (((100 - i) * (i + 1) + 1) / 2) * arr[i];
        }
        assertEquals((int) expected, test.sumOddLengthSubarrays(arr));
    }
}
