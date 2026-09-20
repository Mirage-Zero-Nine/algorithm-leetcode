package solutions.hashmap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class UniqueOccurrences_1207Test {

    private final UniqueOccurrences_1207 test = new UniqueOccurrences_1207();

    @Test
    public void testHappyCases() {
        assertTrue(test.uniqueOccurrences(new int[]{1, 2, 2, 1, 1, 3}));
    }

    @Test
    public void testRepeatedValueEdges() {
        assertTrue(test.uniqueOccurrences(new int[]{1}));
    }

    @Test
    public void testLargeCase() {
        assertFalse(test.uniqueOccurrences(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void testEmptyAndEqualFrequencyCases() {
        assertTrue(test.uniqueOccurrences(new int[]{}));
        assertTrue(test.uniqueOccurrences(new int[]{1, 1}));
        assertFalse(test.uniqueOccurrences(new int[]{1, 2}));
    }

    @Test
    public void testEdgeCases() {
        assertTrue(test.uniqueOccurrences(new int[]{5, 5, 5}));
        assertTrue(test.uniqueOccurrences(new int[]{4, 4, 5}));
        assertFalse(test.uniqueOccurrences(new int[]{9, 8, 7, 6}));
        assertTrue(test.uniqueOccurrences(new int[]{-1}));
    }

    @Test
    public void testGiantCase() {
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
    @Test void extra01() { assertTrue(test.uniqueOccurrences(new int[]{1,1,2})); }
    @Test void extra02() { assertFalse(test.uniqueOccurrences(new int[]{1,1,2,2})); }
    @Test void extra03() { assertTrue(test.uniqueOccurrences(new int[]{-1,-1,0})); }
    @Test void extra04() { assertTrue(test.uniqueOccurrences(new int[]{1,2,2,3,3,3})); }
    @Test void extra05() { assertFalse(test.uniqueOccurrences(new int[]{1,2,3,4})); }
    @Test void extra06() { assertTrue(test.uniqueOccurrences(new int[]{5,5,5,6,6})); }
    @Test void extra07() { assertFalse(test.uniqueOccurrences(new int[]{0,0,1,1,2,2})); }
    @Test void extra08() { assertTrue(test.uniqueOccurrences(new int[]{1,1,1,2,2,3})); }
    @Test void extra09() { assertFalse(test.uniqueOccurrences(new int[]{-2,-1,0})); }
    @Test void extra10() { assertTrue(test.uniqueOccurrences(new int[]{7})); }
}
