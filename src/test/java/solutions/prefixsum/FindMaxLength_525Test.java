package solutions.prefixsum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link FindMaxLength_525}.
 */
public class FindMaxLength_525Test {

    private final FindMaxLength_525 solver = new FindMaxLength_525();

    @Test
    public void testLeetCodeExample() {
        // arr = [0,1], max length with equal 0s and 1s = 2
        int[] nums = {0, 1};
        assertEquals(2, solver.findMaxLength(nums));
    }

    @Test
    public void testLongerExample() {
        // arr = [0,0,1,0,0,0,1,1], max = 6 ([0,0,1,0,0,0,1,1] → subarray [1,0,0,0,1,1] has 3 ones and 3 zeros)
        // Actually [0,0,1,0,0,0,1,1]: subarray [0,1,0,0,0,1] from index 2 to 7 has 3 ones and 3 zeros? No...
        // Let me use simpler example
        int[] nums = {0, 0, 1, 0, 0, 0, 1, 1};
        assertEquals(6, solver.findMaxLength(nums));
    }

    @Test
    public void testAllZeros() {
        int[] nums = {0, 0, 0};
        assertEquals(0, solver.findMaxLength(nums));
    }

    @Test
    public void testAllOnes() {
        int[] nums = {1, 1, 1};
        assertEquals(0, solver.findMaxLength(nums));
    }

    @Test
    public void testAlternating() {
        // arr = [0,1,0,1], max = 4
        int[] nums = {0, 1, 0, 1};
        assertEquals(4, solver.findMaxLength(nums));
    }

    @Test
    public void testEmptyArray() {
        int[] nums = {};
        assertEquals(0, solver.findMaxLength(nums));
    }

    @Test
    public void testSingleElement() {
        int[] nums = {0};
        assertEquals(0, solver.findMaxLength(nums));
    }

    @Test
    public void testSinglePair() {
        int[] nums = {1, 0};
        assertEquals(2, solver.findMaxLength(nums));
    }

    @Test
    public void testNoEqualSubarray() {
        int[] nums = {0, 0, 1};
        assertEquals(2, solver.findMaxLength(nums));
    }

    @Test
    public void testLargeBalanced() {
        // [0,0,1,1,0,0,1,1] → whole array has 4 zeros and 4 ones
        int[] nums = {0, 0, 1, 1, 0, 0, 1, 1};
        assertEquals(8, solver.findMaxLength(nums));
    }

    @Test
    public void testBalancedAtEnd() {
        // [1,1,1,1,0,0,0,0] → last 4 zeros + last 4 ones = balanced subarray of length 8
        int[] nums = {1, 1, 1, 1, 0, 0, 0, 0};
        assertEquals(8, solver.findMaxLength(nums));
    }

    @Test
    public void testBalancedAtStart() {
        // [0,0,0,0,1,1,1,1] → first 4 zeros + first 4 ones = balanced subarray of length 8
        int[] nums = {0, 0, 0, 0, 1, 1, 1, 1};
        assertEquals(8, solver.findMaxLength(nums));
    }
}
