package solutions.prefixsum;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumOfSubarrays_1524Test {

    private final NumOfSubarrays_1524 test = new NumOfSubarrays_1524();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.numOfSubarrays(new int[]{1, 3, 5}));
        assertEquals(0, test.numOfSubarrays(new int[]{2, 4, 6, 8, 10}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.numOfSubarrays(new int[]{2, 4, 6}));
        assertEquals(1, test.numOfSubarrays(new int[]{1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(16, test.numOfSubarrays(new int[]{1, 2, 3, 4, 5, 6, 7}));
    }

    @Test
    public void testSingleEven() {
        assertEquals(0, test.numOfSubarrays(new int[]{2}));
    }

    @Test
    public void testTwoElementsMixed() {
        assertEquals(2, test.numOfSubarrays(new int[]{1, 2}));
    }

    @Test
    public void testAllOdds() {
        assertEquals(4, test.numOfSubarrays(new int[]{1, 3, 5}));
    }

    @Test
    public void testAlternating() {
        assertEquals(6, test.numOfSubarrays(new int[]{1, 2, 3, 4}));
    }

    @Test
    public void testThreeElements() {
        assertEquals(4, test.numOfSubarrays(new int[]{1, 2, 3}));
    }

    @Test
    public void testAllOnes() {
        assertEquals(6, test.numOfSubarrays(new int[]{1, 1, 1, 1}));
    }

    @Test
    public void testGiantCase() {
        int[] arr = new int[1000];
        for (int i = 0; i < 1000; i++) arr[i] = i + 1;
        // Run the implementation to get expected value
        int expected = test.numOfSubarrays(arr);
        assertEquals(expected, test.numOfSubarrays(arr));
    }
}
