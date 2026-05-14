package solution.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MaximumGap_164Test {
    private final MaximumGap_164 solution = new MaximumGap_164();

    @Test
    void testBasic() {
        assertEquals(3, solution.maximumGap(new int[]{3, 6, 9, 1}));
    }

    @Test
    void testNoGap() {
        assertEquals(0, solution.maximumGap(new int[]{10}));
    }

    @Test
    void testTwoElements() {
        assertEquals(5, solution.maximumGap(new int[]{1, 6}));
    }

    @Test
    void testLargeGap() {
        assertEquals(5, solution.maximumGap(new int[]{1, 10, 5}));
    }

    @Test
    void testConsecutive() {
        assertEquals(1, solution.maximumGap(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    void testEmptyArray() {
        assertEquals(0, solution.maximumGap(new int[]{}));
    }

    @Test
    void testAllSame() {
        assertEquals(0, solution.maximumGap(new int[]{5, 5, 5, 5}));
    }

    @Test
    void testDuplicates() {
        assertEquals(3, solution.maximumGap(new int[]{1, 1, 1, 4}));
    }

    @Test
    void testLargeValues() {
        assertEquals(999999999, solution.maximumGap(new int[]{1, 1000000000}));
    }

    @Test
    void testNegativeLike() {
        // array contains zeros
        assertEquals(10, solution.maximumGap(new int[]{0, 10, 20, 30}));
    }

    @Test
    void testGiantCase() {
        int[] arr = new int[10000];
        for (int i = 0; i < 10000; i++) arr[i] = i * 2;
        // sorted: 0,2,4,...,19998 => all gaps are 2
        assertEquals(2, solution.maximumGap(arr));
    }
}
