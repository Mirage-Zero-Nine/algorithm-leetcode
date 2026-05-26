package solutions.cyclicsort;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

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

    @Test
    public void testAllDuplicatesThreePairs() {
        int[] nums = {1, 1, 2, 2, 3, 3};
        List<Integer> result = new FindDuplicates_442().findDuplicates(nums);
        assertEquals(Set.of(1, 2, 3), new HashSet<>(result));
    }

    @Test
    public void testLeetCodeExample() {
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        List<Integer> result = new FindDuplicates_442().findDuplicates(nums);
        assertEquals(Set.of(2, 3), new HashSet<>(result));
    }

    @Test
    public void testSingleDuplicateAtMiddle() {
        int[] nums = {1, 2, 3, 2};
        List<Integer> result = new FindDuplicates_442().findDuplicates(nums);
        assertEquals(Set.of(2), new HashSet<>(result));
    }

    @Test
    public void testDuplicateAdjacentAtStart() {
        int[] nums = {1, 1, 2, 3};
        List<Integer> result = new FindDuplicates_442().findDuplicates(nums);
        assertEquals(Set.of(1), new HashSet<>(result));
    }

    @Test
    public void testDuplicateAdjacentAtEnd() {
        int[] nums = {1, 2, 3, 3};
        List<Integer> result = new FindDuplicates_442().findDuplicates(nums);
        assertEquals(Set.of(3), new HashSet<>(result));
    }

    @Test
    public void testLargeN10000Seed42() {
        int n = 10000;
        Random rng = new Random(42L);
        // Build array: 1..n, then replace some positions to create duplicates
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = i + 1;
        for (int i = 0; i < 500; i++) {
            int src = rng.nextInt(n);
            int dst = rng.nextInt(n);
            if (src != dst) nums[dst] = nums[src];
        }
        // Cross-check: compute expected from frequency count (appears > 1)
        Map<Integer, Integer> freq = new HashMap<>();
        for (int v : nums) freq.merge(v, 1, Integer::sum);
        Set<Integer> expected = freq.entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        List<Integer> result = new FindDuplicates_442().findDuplicates(nums);
        assertEquals(expected, new HashSet<>(result));
        assertFalse(result.isEmpty());
    }

    @Test
    public void testPropertyResultHasNoDuplicates() {
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        List<Integer> result = new FindDuplicates_442().findDuplicates(nums);
        assertEquals(result.size(), new HashSet<>(result).size(),
                "Result should not contain duplicate entries");
    }

    @Test
    public void testPropertyEveryResultAppearsTwice() {
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        Map<Integer, Integer> freq = new HashMap<>();
        for (int v : nums) freq.merge(v, 1, Integer::sum);
        List<Integer> result = new FindDuplicates_442().findDuplicates(nums);
        for (int v : result) {
            assertTrue(freq.get(v) > 1,
                    "Element " + v + " should appear more than once in input");
        }
    }

    @Test
    public void testNoDuplicatesFullPermutation() {
        int[] nums = {3, 1, 4, 2, 5};
        List<Integer> result = new FindDuplicates_442().findDuplicates(nums);
        assertTrue(result.isEmpty());
    }
}
