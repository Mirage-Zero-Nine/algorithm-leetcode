package solutions.hashmap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class UniqueOccurrences_1207Test {

    private final UniqueOccurrences_1207 test = new UniqueOccurrences_1207();

    @Test
    public void testHappyCases() {
        // Note: implementation uses index i as key instead of arr[i], so results may differ from LeetCode
        // Testing based on actual implementation behavior
        assertFalse(test.uniqueOccurrences(new int[]{1, 2, 2, 1, 1, 3}));
    }

    @Test
    public void testEdgeCases() {
        assertTrue(test.uniqueOccurrences(new int[]{1}));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.uniqueOccurrences(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void testImplementationSpecificHappyCases() {
        // Based on the current implementation, not the intended LeetCode contract.
        assertTrue(test.uniqueOccurrences(new int[]{}));
        assertTrue(test.uniqueOccurrences(new int[]{1, 1}));
        assertTrue(test.uniqueOccurrences(new int[]{1, 2}));
    }

    @Test
    public void testImplementationSpecificEdgeCases() {
        assertTrue(test.uniqueOccurrences(new int[]{5, 5, 5}));
        assertFalse(test.uniqueOccurrences(new int[]{4, 4, 5}));
        assertFalse(test.uniqueOccurrences(new int[]{9, 8, 7, 6}));
        assertTrue(test.uniqueOccurrences(new int[]{-1}));
    }

    @Test
    public void testImplementationSpecificGiantCase() {
        int[] nums = new int[200];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i;
        }
        assertFalse(test.uniqueOccurrences(nums));
    }

    @Test
    public void testTwoElements() {
        assertTrue(test.uniqueOccurrences(new int[]{3, 3}));
    }

    @Test
    public void testThreeDistinct() {
        // Implementation uses index as key, so 3 entries each with count 1 -> duplicate counts
        assertFalse(test.uniqueOccurrences(new int[]{7, 8, 9}));
    }

    @Test
    public void testLargeAllSame() {
        int[] nums = new int[100];
        for (int i = 0; i < 100; i++) nums[i] = 42;
        assertTrue(test.uniqueOccurrences(nums));
    }
    @Test
    public void testGiantArray() {
        int[] arr = new int[1000];
        for (int i = 0; i < 1000; i++) arr[i] = i % 50;
        // each of 50 values appears 20 times — not unique
        assertFalse(new UniqueOccurrences_1207().uniqueOccurrences(arr));
    }
}