package solutions.hashmap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FindShortestSubArray_697Test {

    private final FindShortestSubArray_697 test = new FindShortestSubArray_697();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.findShortestSubArray(new int[]{1, 2, 2, 3, 1}));
        assertEquals(6, test.findShortestSubArray(new int[]{1, 2, 2, 3, 1, 4, 2}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.findShortestSubArray(new int[]{1}));
        assertEquals(1, test.findShortestSubArray(new int[]{1, 2, 3}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(1, test.findShortestSubArray(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
        assertEquals(10, test.findShortestSubArray(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1}));
    }

    @Test
    public void testTwoElementsSame() {
        assertEquals(2, test.findShortestSubArray(new int[]{1, 1}));
    }

    @Test
    public void testTwoElementsDifferent() {
        assertEquals(1, test.findShortestSubArray(new int[]{1, 2}));
    }

    @Test
    public void testMultipleCandidatesSameFrequency() {
        // degree is 2, both 1 and 2 appear twice; subarray for 2 is shorter
        assertEquals(2, test.findShortestSubArray(new int[]{1, 2, 2, 3, 1}));
    }

    @Test
    public void testDegreeAtEnds() {
        // 5 appears at index 0 and 4, length 5
        assertEquals(5, test.findShortestSubArray(new int[]{5, 1, 2, 3, 5}));
    }

    @Test
    public void testConsecutiveDuplicates() {
        assertEquals(2, test.findShortestSubArray(new int[]{3, 3}));
        assertEquals(3, test.findShortestSubArray(new int[]{1, 2, 2, 2}));
    }

    @Test
    public void testAllDistinct() {
        assertEquals(1, test.findShortestSubArray(new int[]{10, 20, 30, 40, 50}));
    }

    @Test
    public void testNegativeValues() {
        // degree 2 for element 0, span is index 0 to 4 = length 5
        assertEquals(5, test.findShortestSubArray(new int[]{0, 1, 2, 3, 0}));
    }

    @Test
    public void testGiantCase() {
        int[] arr = new int[10000];
        for (int i = 0; i < 10000; i++) arr[i] = i;
        // all distinct, degree 1, shortest subarray is 1
        assertEquals(1, test.findShortestSubArray(arr));
    }
}
