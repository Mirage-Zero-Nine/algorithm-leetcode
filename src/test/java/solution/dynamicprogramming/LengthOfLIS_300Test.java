package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LengthOfLIS_300Test {

    private final LengthOfLIS_300 test = new LengthOfLIS_300();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        assertEquals(4, test.lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(1, test.lengthOfLIS(new int[]{7, 7, 7, 7, 7}));
        assertEquals(1, test.lengthOfLIS(new int[]{1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(6, test.lengthOfLIS(new int[]{1, 3, 5, 7, 9, 2, 4, 6, 8, 10}));
    }

    @Test
    public void testStrictlyIncreasing() {
        assertEquals(5, test.lengthOfLIS(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void testStrictlyDecreasing() {
        assertEquals(1, test.lengthOfLIS(new int[]{5, 4, 3, 2, 1}));
    }

    @Test
    public void testTwoElements() {
        assertEquals(2, test.lengthOfLIS(new int[]{1, 2}));
        assertEquals(1, test.lengthOfLIS(new int[]{2, 1}));
    }

    @Test
    public void testNegativeNumbers() {
        assertEquals(4, test.lengthOfLIS(new int[]{-5, -3, -1, 0}));
    }

    @Test
    public void testMixedNegativePositive() {
        assertEquals(5, test.lengthOfLIS(new int[]{-2, -1, 0, 1, 2}));
    }

    @Test
    public void testDuplicatesInSequence() {
        assertEquals(3, test.lengthOfLIS(new int[]{1, 1, 2, 2, 3, 3}));
    }

    @Test
    public void testGiantCase() {
        int[] arr = new int[1000];
        for (int i = 0; i < 1000; i++) {
            arr[i] = i;
        }
        assertEquals(1000, test.lengthOfLIS(arr));
    }
}
