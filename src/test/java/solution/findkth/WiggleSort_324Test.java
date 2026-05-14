package solution.findkth;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WiggleSort_324Test {
    private final WiggleSort_324 solver = new WiggleSort_324();

    private boolean isWiggle(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (i % 2 == 0) {
                if (nums[i] >= nums[i + 1]) return false;
            } else {
                if (nums[i] <= nums[i + 1]) return false;
            }
        }
        return true;
    }

    @Test public void testBasic() {
        int[] nums = {1, 5, 1, 1, 6, 4};
        solver.wiggleSort(nums);
        assertTrue(isWiggle(nums));
    }

    @Test public void testDuplicates() {
        int[] nums = {1, 3, 2, 2, 3, 1};
        solver.wiggleSort(nums);
        assertTrue(isWiggle(nums));
    }

    @Test public void testSorted() {
        int[] nums = {1, 2, 3, 4, 5, 6};
        solver.wiggleSort(nums);
        assertTrue(isWiggle(nums));
    }

    @Test public void testIndexMappingBasic() {
        int[] nums = {1, 5, 1, 1, 6, 4};
        solver.wiggleSortIndexMapping(nums);
        assertTrue(isWiggle(nums));
    }

    @Test public void testFindMedian() {
        int[] nums = {5, 2, 4, 1, 3};
        int median = solver.findMedian(nums);
        assertTrue(median == 3 || median == 4);
    }

    @Test public void testSingleElement() {
        int[] nums = {1};
        solver.wiggleSort(nums);
        // single element is trivially wiggle-sorted
        assertEquals(1, nums[0]);
    }

    @Test public void testTwoElements() {
        int[] nums = {2, 1};
        solver.wiggleSort(nums);
        assertTrue(nums[0] < nums[1]);
    }

    @Test public void testReverseSorted() {
        int[] nums = {6, 5, 4, 3, 2, 1};
        solver.wiggleSort(nums);
        assertTrue(isWiggle(nums));
    }

    @Test public void testIndexMappingDuplicates() {
        int[] nums = {1, 3, 2, 2, 3, 1};
        solver.wiggleSortIndexMapping(nums);
        assertTrue(isWiggle(nums));
    }

    @Test public void testIndexMappingSorted() {
        int[] nums = {1, 2, 3, 4, 5, 6};
        solver.wiggleSortIndexMapping(nums);
        assertTrue(isWiggle(nums));
    }

    @Test public void testFindMedianEven() {
        int[] nums = {3, 1, 4, 2};
        int median = solver.findMedian(nums);
        assertTrue(median >= 1 && median <= 4);
    }

    @Test public void testGiantCase() {
        int[] nums = new int[1000];
        for (int i = 0; i < 1000; i++) nums[i] = i;
        solver.wiggleSort(nums);
        assertTrue(isWiggle(nums));
    }
}
