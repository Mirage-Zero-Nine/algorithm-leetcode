package solution.greedy;

import org.junit.jupiter.api.Test;

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
        solver.wiggleSort(null);
        // Just verify no exception
    }
}
