package solutions.cyclicsort;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

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

    @Test
    public void testAllPresentFullRange() {
        // [1..5] all present -> []
        int[] nums = {5, 4, 3, 2, 1};
        List<Integer> result = solver.findDisappearedNumbers(nums);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testAllSameValue() {
        // n=5, all values are 1 -> missing [2,3,4,5]
        int[] nums = {1, 1, 1, 1, 1};
        List<Integer> result = solver.findDisappearedNumbers(nums);
        assertEquals(List.of(2, 3, 4, 5), result);
    }

    @Test
    public void testLeetCodeExample() {
        // Fresh instance to avoid mutation side effects
        FindDisappearedNumbers_448 s = new FindDisappearedNumbers_448();
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        List<Integer> result = s.findDisappearedNumbers(nums);
        assertEquals(List.of(5, 6), result);
    }

    @Test
    public void testSingleMissingAtStart() {
        // n=5, missing 1 -> [1]
        int[] nums = {2, 3, 4, 5, 2};
        List<Integer> result = solver.findDisappearedNumbers(nums);
        assertEquals(List.of(1), result);
    }

    @Test
    public void testSingleMissingAtEnd() {
        // n=5, missing 5 -> [5]
        int[] nums = {1, 2, 3, 4, 1};
        List<Integer> result = solver.findDisappearedNumbers(nums);
        assertEquals(List.of(5), result);
    }

    @Test
    public void testMultipleMissing() {
        // n=6, missing 2,4,6
        int[] nums = {1, 3, 5, 1, 3, 5};
        List<Integer> result = solver.findDisappearedNumbers(nums);
        assertEquals(List.of(2, 4, 6), result);
    }

    @Test
    public void testLargeN10000Seed42() {
        int n = 10000;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = i + 1;
        Random rng = new Random(42L);
        for (int i = 0; i < 500; i++) {
            int target = rng.nextInt(n);
            int source = rng.nextInt(n);
            nums[target] = nums[source];
        }
        // Cross-check via boolean array
        boolean[] seen = new boolean[n + 1];
        for (int v : nums) seen[v] = true;
        List<Integer> expected = new ArrayList<>();
        for (int i = 1; i <= n; i++) if (!seen[i]) expected.add(i);

        List<Integer> result = solver.findDisappearedNumbers(nums);
        assertEquals(expected, result);
    }

    @Test
    public void testPropertyResultSortedAscending() {
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        List<Integer> result = new FindDisappearedNumbers_448().findDisappearedNumbers(nums);
        for (int i = 1; i < result.size(); i++) {
            assertTrue(result.get(i) > result.get(i - 1), "Result should be sorted ascending");
        }
    }

    @Test
    public void testPropertyResultUnionInputCoversFullRange() {
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        int n = nums.length;
        Set<Integer> inputValues = new HashSet<>();
        for (int v : nums) inputValues.add(v);
        List<Integer> result = new FindDisappearedNumbers_448().findDisappearedNumbers(nums);
        Set<Integer> union = new HashSet<>(inputValues);
        union.addAll(result);
        Set<Integer> fullRange = new HashSet<>();
        IntStream.rangeClosed(1, n).forEach(fullRange::add);
        assertEquals(fullRange, union);
    }
}
