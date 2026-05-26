package solutions.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinSwaps_1151Test {

    private final MinSwaps_1151 solution = new MinSwaps_1151();

    @Test
    public void testBasicCase() {
        assertEquals(1, solution.minSwaps(new int[]{1, 0, 1, 0, 1}));
    }

    @Test
    public void testNoSwapNeeded() {
        assertEquals(0, solution.minSwaps(new int[]{1, 1, 1, 0, 0}));
    }

    @Test
    public void testAllOnes() {
        assertEquals(0, solution.minSwaps(new int[]{1, 1, 1, 1}));
    }

    @Test
    public void testAllZeros() {
        assertEquals(0, solution.minSwaps(new int[]{0, 0, 0, 0}));
    }

    @Test
    public void testSingleElement() {
        assertEquals(0, solution.minSwaps(new int[]{1}));
    }

    @Test
    public void testTwoElements() {
        assertEquals(0, solution.minSwaps(new int[]{1, 0}));
    }

    @Test
    public void testOnesAtEnds() {
        assertEquals(1, solution.minSwaps(new int[]{1, 0, 0, 1}));
    }

    @Test
    public void testAlternating() {
        assertEquals(1, solution.minSwaps(new int[]{0, 1, 0, 1, 0}));
    }

    @Test
    public void testExample2() {
        assertEquals(3, solution.minSwaps(new int[]{1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1}));
    }

    @Test
    public void testSingleOne() {
        assertEquals(0, solution.minSwaps(new int[]{0, 0, 1, 0, 0}));
    }

    @Test
    public void testGiantCase() {
        int[] data = new int[100000];
        // Place 1s at even indices
        for (int i = 0; i < 100000; i++) {
            data[i] = i % 2 == 0 ? 1 : 0;
        }
        // 50000 ones, window size 50000, best window has at most 25000 ones
        int result = solution.minSwaps(data);
        assertEquals(25000, result);
    }
}
