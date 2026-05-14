package solution.array;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link FindDuplicates_442}.
 */
public class FindDuplicates_442Test {

    private final FindDuplicates_442 solver = new FindDuplicates_442();

    @Test
    public void testClassicDuplicates() {
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        List<Integer> result = solver.findDuplicates(nums);
        assertEquals(2, result.size());
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
    }

    @Test
    public void testNoDuplicates() {
        int[] nums = {1, 2, 3, 4};
        List<Integer> result = solver.findDuplicates(nums);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testAllDuplicates() {
        int[] nums = {2, 2};
        List<Integer> result = solver.findDuplicates(nums);
        assertEquals(1, result.size());
        assertTrue(result.contains(2));
    }

    @Test
    public void testNullInput() {
        List<Integer> result = solver.findDuplicates(null);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testEmptyArray() {
        int[] nums = {};
        List<Integer> result = solver.findDuplicates(nums);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testMultipleDuplicates() {
        int[] nums = {1, 1, 2, 2};
        List<Integer> result = solver.findDuplicates(nums);
        assertEquals(2, result.size());
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
    }

    @Test
    public void testSingleDuplicate() {
        int[] nums = {1, 1};
        List<Integer> result = solver.findDuplicates(nums);
        assertEquals(1, result.size());
        assertTrue(result.contains(1));
    }

    @Test
    public void testDuplicateAtEnd() {
        int[] nums = {1, 2, 3, 3};
        List<Integer> result = solver.findDuplicates(nums);
        assertEquals(1, result.size());
        assertTrue(result.contains(3));
    }

    @Test
    public void testDuplicateAtStart() {
        int[] nums = {2, 2, 3, 4};
        List<Integer> result = solver.findDuplicates(nums);
        assertEquals(1, result.size());
        assertTrue(result.contains(2));
    }

    @Test
    public void testSingleElement() {
        int[] nums = {1};
        List<Integer> result = solver.findDuplicates(nums);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGiantArray() {
        // array of size 2000: 1..1000 each appearing twice
        int[] nums = new int[2000];
        for (int i = 0; i < 1000; i++) {
            nums[2 * i] = i + 1;
            nums[2 * i + 1] = i + 1;
        }
        List<Integer> result = solver.findDuplicates(nums);
        assertEquals(1000, result.size());
    }
}
