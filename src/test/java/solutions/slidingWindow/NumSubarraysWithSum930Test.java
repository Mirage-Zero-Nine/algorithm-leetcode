package solutions.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumSubarraysWithSum930Test {

    private final NumSubarraysWithSum_930 test = new NumSubarraysWithSum_930();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.numSubarraysWithSum(new int[]{1, 0, 1, 0, 1}, 2));
        assertEquals(27, test.numSubarraysWithSum(new int[]{0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, 0));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(1, test.numSubarraysWithSum(new int[]{1, 1, 1}, 3));
        assertEquals(1, test.numSubarraysWithSum(new int[]{1}, 1));
    }

    @Test
    public void testLargeCase() {
        assertEquals(4, test.numSubarraysWithSum(new int[]{1, 1, 1, 1, 1, 1}, 3));
    }

    @Test
    public void testSumZeroAllZeros() {
        assertEquals(15, test.numSubarraysWithSum(new int[]{0, 0, 0, 0, 0}, 0));
    }

    @Test
    public void testSumZeroNoZeros() {
        assertEquals(0, test.numSubarraysWithSum(new int[]{1, 1, 1}, 0));
    }

    @Test
    public void testSingleElementMatchesSum() {
        assertEquals(1, test.numSubarraysWithSum(new int[]{0}, 0));
        assertEquals(1, test.numSubarraysWithSum(new int[]{1}, 1));
    }

    @Test
    public void testSingleElementDoesNotMatch() {
        assertEquals(0, test.numSubarraysWithSum(new int[]{0}, 1));
        assertEquals(0, test.numSubarraysWithSum(new int[]{1}, 0));
    }

    @Test
    public void testAllOnes() {
        assertEquals(5, test.numSubarraysWithSum(new int[]{1, 1, 1, 1, 1}, 1));
    }

    @Test
    public void testSumEqualsArrayLength() {
        assertEquals(1, test.numSubarraysWithSum(new int[]{1, 1, 1, 1}, 4));
    }

    @Test
    public void testGiantCase() {
        int[] arr = new int[10000];
        for (int i = 0; i < 10000; i++) {
            arr[i] = 1;
        }
        // number of subarrays of length 100 in array of 10000 = 9901
        assertEquals(9901, test.numSubarraysWithSum(arr, 100));
    }

    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.CsvSource({"0,0,1", "1,1,1", "00,0,3", "11,1,2", "11,2,1", "101,2,1", "010,1,4", "01010,2,4", "11111,0,0", "00100,1,9"})
    void additionalBoundaryCases(String values, int goal, int expected) {
        int[] nums = values.chars().map(c -> c - '0').toArray();
        assertEquals(expected, test.numSubarraysWithSum(nums, goal));
    }
}
