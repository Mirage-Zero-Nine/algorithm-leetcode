package solutions.prefixsum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * Unit tests for {@link RunningSum_1480}.
 */
public class RunningSum_1480Test {

    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.ValueSource(ints = {0, 1, 2, 7, 19, 42, 97, 211, 2026, 65537})
    void independentRangeSumsMatchEveryOutputAndPreserveInput(int seed) {
        java.util.Random random = new java.util.Random(seed);
        int[] nums = random.ints(200, -1000, 1001).toArray();
        int[] snapshot = nums.clone();
        int[] expected = new int[nums.length];
        for (int i = 0; i < nums.length; i++) expected[i] = java.util.Arrays.stream(nums, 0, i + 1).sum();
        assertArrayEquals(expected, solver.runningSum(nums));
        assertArrayEquals(snapshot, nums);
    }

    @Test
    void largeAlternatingSignsHaveKnownEveryElementResult() {
        int[] nums = new int[1000];
        int[] expected = new int[1000];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i % 2 == 0 ? 1000 : -1000;
            expected[i] = i % 2 == 0 ? 1000 : 0;
        }
        assertArrayEquals(expected, solver.runningSum(nums));
    }


    private final RunningSum_1480 solver = new RunningSum_1480();

    @Test
    public void testLeetCodeExample() {
        // nums = [1,2,3,4]
        // runningSum = [1,3,6,10]
        int[] nums = {1, 2, 3, 4};
        int[] expected = {1, 3, 6, 10};
        assertArrayEquals(expected, solver.runningSum(nums));
    }

    @Test
    public void testSingleElement() {
        int[] nums = {1};
        int[] expected = {1};
        assertArrayEquals(expected, solver.runningSum(nums));
    }

    @Test
    public void testTwoElements() {
        int[] nums = {1, 1};
        int[] expected = {1, 2};
        assertArrayEquals(expected, solver.runningSum(nums));
    }

    @Test
    public void testWithZeros() {
        // nums = [0,0,0,0]
        // runningSum = [0,0,0,0]
        int[] nums = {0, 0, 0, 0};
        int[] expected = {0, 0, 0, 0};
        assertArrayEquals(expected, solver.runningSum(nums));
    }

    @Test
    public void testWithNegativeValues() {
        // nums = [1,-2,3,4,-5]
        // runningSum = [1,-1,2,6,1]
        int[] nums = {1, -2, 3, 4, -5};
        int[] expected = {1, -1, 2, 6, 1};
        assertArrayEquals(expected, solver.runningSum(nums));
    }

    @Test
    public void testAllNegativeValues() {
        // nums = [-1,-2,-3,-4]
        // runningSum = [-1,-3,-6,-10]
        int[] nums = {-1, -2, -3, -4};
        int[] expected = {-1, -3, -6, -10};
        assertArrayEquals(expected, solver.runningSum(nums));
    }

    @Test
    public void testLargeValues() {
        int[] nums = {100000, 100000, 100000, 100000};
        int[] expected = {100000, 200000, 300000, 400000};
        assertArrayEquals(expected, solver.runningSum(nums));
    }

    @Test
    public void testIncreasingSequence() {
        int[] nums = {1, 2, 3, 4, 5};
        int[] expected = {1, 3, 6, 10, 15};
        assertArrayEquals(expected, solver.runningSum(nums));
    }

    @Test
    public void testDecreasingSequence() {
        int[] nums = {5, 4, 3, 2, 1};
        int[] expected = {5, 9, 12, 14, 15};
        assertArrayEquals(expected, solver.runningSum(nums));
    }

    @Test
    public void testMixedPositiveNegative() {
        int[] nums = {3, -1, 2, -4, 5};
        int[] expected = {3, 2, 4, 0, 5};
        assertArrayEquals(expected, solver.runningSum(nums));
    }

    @Test
    public void testGiantArray() {
        int[] nums = new int[1000];
        int[] expected = new int[1000];
        for (int i = 0; i < 1000; i++) {
            nums[i] = 1;
            expected[i] = i + 1;
        }
        assertArrayEquals(expected, solver.runningSum(nums));
    }
}
