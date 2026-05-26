package solutions.greedy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WiggleSort_280Test {
    private final WiggleSort_280 solver = new WiggleSort_280();

    private boolean isWiggle(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (i % 2 == 1) {
                if (nums[i - 1] > nums[i]) return false;
            } else {
                if (nums[i - 1] < nums[i]) return false;
            }
        }
        return true;
    }

    @Test public void testBasic() {
        int[] nums = {3, 5, 2, 1, 6, 4};
        solver.wiggleSort(nums);
        assertTrue(isWiggle(nums));
    }

    @Test public void testSorted() {
        int[] nums = {1, 2, 3, 4, 5};
        solver.wiggleSort(nums);
        assertTrue(isWiggle(nums));
    }

    @Test public void testEmpty() {
        int[] nums = {};
        solver.wiggleSort(nums);
        assertTrue(isWiggle(nums));
    }

    @Test public void testSingle() {
        int[] nums = {1};
        solver.wiggleSort(nums);
        assertTrue(isWiggle(nums));
    }

    @Test public void testNull() {
        assertDoesNotThrow(() -> solver.wiggleSort(null));
    }

    @Test public void testTwoElements() {
        int[] nums = {5, 3};
        solver.wiggleSort(nums);
        assertTrue(isWiggle(nums));
    }

    @Test public void testDescending() {
        int[] nums = {6, 5, 4, 3, 2, 1};
        solver.wiggleSort(nums);
        assertTrue(isWiggle(nums));
    }

    @Test public void testAllSame() {
        int[] nums = {3, 3, 3, 3, 3};
        solver.wiggleSort(nums);
        assertTrue(isWiggle(nums));
    }

    @Test public void testNegativeNumbers() {
        int[] nums = {-3, -1, -5, -2, -4};
        solver.wiggleSort(nums);
        assertTrue(isWiggle(nums));
    }

    @Test public void testDuplicates() {
        int[] nums = {1, 1, 2, 2, 3, 3};
        solver.wiggleSort(nums);
        assertTrue(isWiggle(nums));
    }

    @Test public void testGiant() {
        int[] nums = new int[10000];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums.length - i;
        }
        solver.wiggleSort(nums);
        assertTrue(isWiggle(nums));
    }
}
