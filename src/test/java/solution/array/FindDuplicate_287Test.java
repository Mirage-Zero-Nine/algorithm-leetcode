package solution.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link FindDuplicate_287}.
 */
public class FindDuplicate_287Test {

    private final FindDuplicate_287 solver = new FindDuplicate_287();

    @Test
    public void testClassicDuplicate() {
        int[] nums = {1, 3, 4, 2, 2};
        assertEquals(2, solver.findDuplicate(nums));
    }

    @Test
    public void testDuplicateAtStart() {
        int[] nums = {2, 1, 3, 2};
        assertEquals(2, solver.findDuplicate(nums));
    }

    @Test
    public void testDuplicateAtEnd() {
        int[] nums = {1, 2, 3, 3};
        assertEquals(3, solver.findDuplicate(nums));
    }

    @Test
    public void testDuplicateRepeatedManyTimes() {
        int[] nums = {3, 3, 3, 3};
        assertEquals(3, solver.findDuplicate(nums));
    }

    @Test
    public void testMinimalArray() {
        int[] nums = {1, 1};
        assertEquals(1, solver.findDuplicate(nums));
    }

    @Test
    public void testDuplicateWithLargeGap() {
        int[] nums = {1, 5, 3, 4, 2, 5};
        assertEquals(5, solver.findDuplicate(nums));
    }

    @Test
    public void testDuplicateNearEnd() {
        int[] nums = {1, 2, 3, 4, 5, 5};
        assertEquals(5, solver.findDuplicate(nums));
    }

    @Test
    public void testDuplicateNearStart() {
        int[] nums = {2, 1, 3, 4, 5, 2};
        assertEquals(2, solver.findDuplicate(nums));
    }

    @Test
    public void testDuplicateInMiddle() {
        int[] nums = {1, 2, 3, 4, 3, 5, 6};
        assertEquals(3, solver.findDuplicate(nums));
    }

    @Test
    public void testGiantArray() {
        int n = 100000;
        int[] nums = new int[n + 1];
        for (int i = 0; i < n; i++) nums[i] = i + 1;
        nums[n] = 50000; // duplicate
        assertEquals(50000, solver.findDuplicate(nums));
    }
}
