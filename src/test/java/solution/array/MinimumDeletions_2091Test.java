package solution.array;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MinimumDeletions_2091Test {
    private final MinimumDeletions_2091 solution = new MinimumDeletions_2091();

    @Test
    void testBasic() {
        assertEquals(5, solution.minimumDeletions(new int[]{2, 10, 7, 5, 4, 1, 8, 6}));
    }

    @Test
    void testMinMaxAtEnds() {
        assertEquals(3, solution.minimumDeletions(new int[]{0, -4, 19, 1, 8, -2, -3, 5}));
    }

    @Test
    void testTwoElements() {
        assertEquals(2, solution.minimumDeletions(new int[]{1, 2}));
    }

    @Test
    void testAllSame() {
        assertEquals(1, solution.minimumDeletions(new int[]{5, 5, 5, 5}));
    }

    @Test
    void testLargeArray() {
        assertEquals(2, solution.minimumDeletions(new int[]{-1, -2, -3, -4, -5}));
    }

    @Test
    void testSingleElement() {
        assertEquals(1, solution.minimumDeletions(new int[]{42}));
    }

    @Test
    void testMinAtFrontMaxAtBack() {
        assertEquals(2, solution.minimumDeletions(new int[]{1, 3, 5, 7, 9}));
    }

    @Test
    void testMaxAtFrontMinAtBack() {
        assertEquals(2, solution.minimumDeletions(new int[]{9, 7, 5, 3, 1}));
    }

    @Test
    void testMinMaxAdjacent() {
        assertEquals(3, solution.minimumDeletions(new int[]{3, 1, 10, 5, 7}));
    }

    @Test
    void testNegativeValues() {
        assertEquals(2, solution.minimumDeletions(new int[]{-10, 5, -3, 2}));
    }

    @Test
    void testGiantArray() {
        int n = 100000;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = i;
        // min=0 at index 0, max=99999 at index 99999
        // remove from left: 100000, remove from right: 100000, remove both sides: 1 + 1 = 2
        assertEquals(2, solution.minimumDeletions(nums));
    }
}
