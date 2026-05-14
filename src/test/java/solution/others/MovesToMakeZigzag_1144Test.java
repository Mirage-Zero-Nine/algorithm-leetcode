package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MovesToMakeZigzag_1144Test {

    private final MovesToMakeZigzag_1144 test = new MovesToMakeZigzag_1144();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.movesToMakeZigzag(new int[]{1, 2, 3}));
        assertEquals(4, test.movesToMakeZigzag(new int[]{9, 6, 1, 6, 2}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(0, test.movesToMakeZigzag(new int[]{1}));
        assertEquals(0, test.movesToMakeZigzag(new int[]{1, 2}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(4, test.movesToMakeZigzag(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void testAlreadyZigzag() {
        assertEquals(0, test.movesToMakeZigzag(new int[]{1, 5, 1, 5, 1}));
    }

    @Test
    public void testAllEqual() {
        assertEquals(2, test.movesToMakeZigzag(new int[]{2, 2, 2, 2}));
    }

    @Test
    public void testDecreasing() {
        assertEquals(2, test.movesToMakeZigzag(new int[]{3, 2, 1}));
    }

    @Test
    public void testTwoElements() {
        assertEquals(0, test.movesToMakeZigzag(new int[]{5, 3}));
    }

    @Test
    public void testNegativeValues() {
        // The problem states integers; implementation uses Math.max(0,...) so negative differences are fine
        assertEquals(0, test.movesToMakeZigzag(new int[]{1, 10, 1}));
    }

    @Test
    public void testLargeValues() {
        assertEquals(0, test.movesToMakeZigzag(new int[]{1, 1000, 1, 1000, 1}));
    }

    @Test
    public void testGiantCase() {
        int[] arr = new int[1000];
        for (int i = 0; i < 1000; i++) {
            arr[i] = 5;
        }
        // All equal array of size 1000: need to decrease every other element by 1
        // For even strategy: decrease even-indexed elements; for odd strategy: decrease odd-indexed elements
        // Both strategies cost 500 moves (500 elements each need -1)
        int result = test.movesToMakeZigzag(arr);
        assertEquals(500, result);
    }
}
