package solution.orderedmap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ContainsNearbyAlmostDuplicate_220Test {

    private final ContainsNearbyAlmostDuplicate_220 test = new ContainsNearbyAlmostDuplicate_220();

    @Test
    public void testHappyCases() {
        assertTrue(test.containsNearbyAlmostDuplicate(new int[]{1, 2, 3, 1}, 3, 0));
        assertTrue(test.treeSet(new int[]{1, 0, 1, 1}, 1, 2));
    }

    @Test
    public void testNegativeCases() {
        assertFalse(test.containsNearbyAlmostDuplicate(new int[]{1, 5, 9, 1, 5, 9}, 2, 3));
        assertFalse(test.treeSet(new int[]{1, 5, 9, 1, 5, 9}, 2, 3));
    }

    @Test
    public void testInvalidAndEdgeCases() {
        assertFalse(test.containsNearbyAlmostDuplicate(new int[]{1, 2}, 0, 1));
        assertFalse(test.containsNearbyAlmostDuplicate(new int[]{1, 2}, 1, -1));
        assertFalse(test.treeSet(new int[0], 3, 1));
        assertTrue(test.treeSet(new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE + 1}, 1, 1));
    }

    @Test
    public void testLargeCase() {
        int[] nums = new int[200];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i * 10;
        }
        nums[199] = nums[198] + 1;
        assertTrue(test.containsNearbyAlmostDuplicate(nums, 2, 1));
        assertTrue(test.treeSet(nums, 2, 1));
    }
}
