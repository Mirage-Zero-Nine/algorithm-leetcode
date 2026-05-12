package solution.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link FirstMissingPositive_41}.
 */
public class FirstMissingPositive_41Test {

    private final FirstMissingPositive_41 solver = new FirstMissingPositive_41();

    @Test
    public void testLeetCodeExample1() {
        // [1,2,0] → missing 3
        int[] nums = {1, 2, 0};
        assertEquals(3, solver.firstMissingPositive(nums));
    }

    @Test
    public void testLeetCodeExample2() {
        // [3,4,-1,1] → missing 2
        int[] nums = {3, 4, -1, 1};
        assertEquals(2, solver.firstMissingPositive(nums));
    }

    @Test
    public void testLeetCodeExample3() {
        // [7,8,9,11,12] → missing 1
        int[] nums = {7, 8, 9, 11, 12};
        assertEquals(1, solver.firstMissingPositive(nums));
    }

    @Test
    public void testEmptyArray() {
        // Edge case: length 0
        int[] nums = {};
        assertEquals(1, solver.firstMissingPositive(nums));
    }

    @Test
    public void testSinglePositive() {
        // [1] → missing 2
        int[] nums = {1};
        assertEquals(2, solver.firstMissingPositive(nums));
    }

    @Test
    public void testSingleNonPositive() {
        // [0] → missing 1
        int[] nums = {0};
        assertEquals(1, solver.firstMissingPositive(nums));
    }

    @Test
    public void testSingleNegative() {
        // [-1] → missing 1
        int[] nums = {-1};
        assertEquals(1, solver.firstMissingPositive(nums));
    }

    @Test
    public void testAllNegative() {
        // [-1,-2,-3] → missing 1
        int[] nums = {-1, -2, -3};
        assertEquals(1, solver.firstMissingPositive(nums));
    }

    @Test
    public void testSequentialFrom1() {
        // [1,2,3] → missing 4
        int[] nums = {1, 2, 3};
        assertEquals(4, solver.firstMissingPositive(nums));
    }

    @Test
    public void testMissingMiddle() {
        // [1,3,4] → missing 2
        int[] nums = {1, 3, 4};
        assertEquals(2, solver.firstMissingPositive(nums));
    }

    @Test
    public void testWithZeros() {
        // [0,0,0] → missing 1
        int[] nums = {0, 0, 0};
        assertEquals(1, solver.firstMissingPositive(nums));
    }

    @Test
    public void testLargeGap() {
        // [1,2,3,100] → missing 4
        int[] nums = {1, 2, 3, 100};
        assertEquals(4, solver.firstMissingPositive(nums));
    }

    @Test
    public void testDuplicateAndNegative() {
        // [1,1,2,-1] → missing 3
        int[] nums = {1, 1, 2, -1};
        assertEquals(3, solver.firstMissingPositive(nums));
    }
}
