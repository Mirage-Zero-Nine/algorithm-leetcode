package solutions.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FindMaxConsecutiveOnes485Test {

    private final FindMaxConsecutiveOnes_485 test = new FindMaxConsecutiveOnes_485();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.findMaxConsecutiveOnes(new int[]{1, 1, 0, 1, 1, 1}));
        assertEquals(2, test.findMaxConsecutiveOnes(new int[]{1, 0, 1, 1, 0, 1}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.findMaxConsecutiveOnes(new int[]{0}));
        assertEquals(1, test.findMaxConsecutiveOnes(new int[]{1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(5, test.findMaxConsecutiveOnes(new int[]{1, 1, 1, 1, 1, 0, 1, 1, 1, 1}));
    }

    @Test
    public void testAllZeros() {
        assertEquals(0, test.findMaxConsecutiveOnes(new int[]{0, 0, 0, 0}));
    }

    @Test
    public void testAllOnes() {
        assertEquals(4, test.findMaxConsecutiveOnes(new int[]{1, 1, 1, 1}));
    }

    @Test
    public void testAlternating() {
        assertEquals(1, test.findMaxConsecutiveOnes(new int[]{1, 0, 1, 0, 1, 0}));
    }

    @Test
    public void testConsecutiveAtEnd() {
        assertEquals(3, test.findMaxConsecutiveOnes(new int[]{0, 0, 1, 1, 1}));
    }

    @Test
    public void testConsecutiveAtStart() {
        assertEquals(3, test.findMaxConsecutiveOnes(new int[]{1, 1, 1, 0, 0}));
    }

    @Test
    public void testEmptyArray() {
        assertEquals(0, test.findMaxConsecutiveOnes(new int[]{}));
    }

    @Test
    public void testGiantCase() {
        int[] arr = new int[100000];
        for (int i = 0; i < 100000; i++) arr[i] = 1;
        arr[50000] = 0;
        assertEquals(50000, test.findMaxConsecutiveOnes(arr));
    }
}
