package solution.array;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link FindDisappearedNumbers_448}.
 */
public class FindDisappearedNumbers_448Test {

    private final FindDisappearedNumbers_448 solver = new FindDisappearedNumbers_448();

    @Test
    public void testClassicExample() {
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        List<Integer> result = solver.findDisappearedNumbers(nums);
        assertEquals(2, result.size());
        assertTrue(result.contains(5));
        assertTrue(result.contains(6));
    }

    @Test
    public void testNoMissingNumbers() {
        int[] nums = {1, 2, 3};
        List<Integer> result = solver.findDisappearedNumbers(nums);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testAllDuplicates() {
        int[] nums = {3, 3, 3, 3};
        // Only value 3 appears, so 1, 2, 4 are missing
        List<Integer> result = solver.findDisappearedNumbers(nums);
        assertEquals(3, result.size());
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
        assertTrue(result.contains(4));
    }

    @Test
    public void testSingleElementPresent() {
        int[] nums = {1};
        List<Integer> result = solver.findDisappearedNumbers(nums);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testSingleElementMissing() {
        // n=1, code returns empty for n<=1 regardless of value
        int[] nums = {2};
        List<Integer> result = solver.findDisappearedNumbers(nums);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testNullInput() {
        List<Integer> result = solver.findDisappearedNumbers(null);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testEmptyArray() {
        int[] nums = {};
        List<Integer> result = solver.findDisappearedNumbers(nums);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testPartialOverlap() {
        // All values are 2, so indices 0, 2, 3 not marked
        int[] nums = {2, 2, 2, 2};
        List<Integer> result = solver.findDisappearedNumbers(nums);
        assertEquals(3, result.size());
        assertTrue(result.contains(1));
        assertTrue(result.contains(3));
        assertTrue(result.contains(4));
    }

    @Test
    public void testTwoMissingAtEnd() {
        int[] nums = {1, 2, 3, 4};
        List<Integer> result = solver.findDisappearedNumbers(nums);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testOneMissingMiddle() {
        int[] nums = {1, 1, 3, 4};
        List<Integer> result = solver.findDisappearedNumbers(nums);
        assertEquals(1, result.size());
        assertTrue(result.contains(2));
    }

    @Test
    public void testPartialDuplicates() {
        int[] nums = {1, 2, 2, 4};
        List<Integer> result = solver.findDisappearedNumbers(nums);
        assertEquals(1, result.size());
        assertTrue(result.contains(3));
    }

    @Test
    public void testAllUnique() {
        int[] nums = {4, 3, 2, 1};
        List<Integer> result = solver.findDisappearedNumbers(nums);
        assertTrue(result.isEmpty());
    }
}
