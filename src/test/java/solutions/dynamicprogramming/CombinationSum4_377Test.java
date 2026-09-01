package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class CombinationSum4_377Test {

    private final CombinationSum4_377 test = new CombinationSum4_377();

    @Test
    public void testHappyCases() {
        assertEquals(7, test.combinationSum4(new int[]{1, 2, 3}, 4));
        assertEquals(0, test.combinationSum4(new int[]{9}, 3));
    }

    @Test
    public void testSmallTargets() {
        assertEquals(2, test.combinationSum4(new int[]{1, 2, 3}, 2));
        assertEquals(1, test.combinationSum4(new int[]{1}, 1));
    }

    @Test
    public void testLargeCase() {
        assertEquals(274, test.combinationSum4(new int[]{1, 2, 3}, 10));
    }

    @Test
    public void testSingleElementEqualsTarget() {
        assertEquals(1, test.combinationSum4(new int[]{5}, 5));
    }

    @Test
    public void testSingleElementCannotReachTarget() {
        assertEquals(0, test.combinationSum4(new int[]{3}, 2));
    }

    @Test
    public void testTwoElements() {
        assertEquals(3, test.combinationSum4(new int[]{1, 2}, 3));
    }

    @Test
    public void testAllElementsLargerThanTarget() {
        assertEquals(0, test.combinationSum4(new int[]{5, 6, 7}, 4));
    }

    @Test
    public void testTargetOne() {
        assertEquals(1, test.combinationSum4(new int[]{1, 2, 3}, 1));
    }

    @Test
    public void testLargerTarget() {
        assertEquals(121415, test.combinationSum4(new int[]{1, 2, 3}, 20));
    }

    @Test
    public void testTwoElementsTarget4() {
        assertEquals(5, test.combinationSum4(new int[]{1, 2}, 4));
    }

    @Test
    public void testAllSmallDistinctPositiveInputs() {
        int[] candidateValues = {1, 2, 3, 4, 5};

        // Every non-empty subset is a legal nums input, and every target is positive.
        for (int mask = 1; mask < (1 << candidateValues.length); mask++) {
            int[] nums = subset(candidateValues, mask);
            for (int target = 1; target <= 8; target++) {
                assertEquals(bruteForceCount(nums, target), test.combinationSum4(nums, target),
                        "nums=" + Arrays.toString(nums) + ", target=" + target);
            }
        }
    }

    private int[] subset(int[] values, int mask) {
        int[] result = new int[Integer.bitCount(mask)];
        int index = 0;
        for (int i = 0; i < values.length; i++) {
            if ((mask & (1 << i)) != 0) {
                result[index++] = values[i];
            }
        }
        return result;
    }

    /** Counts ordered sequences by trying every available value at each position. */
    private int bruteForceCount(int[] nums, int remaining) {
        if (remaining == 0) {
            return 1;
        }

        int count = 0;
        for (int num : nums) {
            if (num <= remaining) {
                count += bruteForceCount(nums, remaining - num);
            }
        }
        return count;
    }
}
