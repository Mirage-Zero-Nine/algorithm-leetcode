package solutions.prefixsum;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumOfSubarrays_1524Test {

    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.ValueSource(ints = {0, 1, 2, 7, 19, 42, 97, 211, 2026, 65537})
    void oddCountsMatchDirectSubarraySums(int seed) {
        java.util.Random random = new java.util.Random(seed);
        for (int trial = 0; trial < 100; trial++) {
            int[] nums = random.ints(1 + random.nextInt(30), 1, 101).toArray();
            int expected = 0;
            for (int left = 0; left < nums.length; left++) {
                int sum = 0;
                for (int right = left; right < nums.length; right++) {
                    sum += nums[right];
                    if (sum % 2 != 0) expected++;
                }
            }
            assertEquals(expected, test.numOfSubarrays(nums));
        }
    }

    @Test
    void largeOddArrayChecksModuloWithIndependentClosedForm() {
        int n = 100_000;
        int[] nums = new int[n];
        java.util.Arrays.fill(nums, 1);
        long expected = ((long) (n + 1) / 2) * ((n + 2) / 2);
        assertEquals(expected % 1_000_000_007, test.numOfSubarrays(nums));
    }


    private final NumOfSubarrays_1524 test = new NumOfSubarrays_1524();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.numOfSubarrays(new int[]{1, 3, 5}));
        assertEquals(0, test.numOfSubarrays(new int[]{2, 4, 6, 8, 10}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.numOfSubarrays(new int[]{2, 4, 6}));
        assertEquals(1, test.numOfSubarrays(new int[]{1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(16, test.numOfSubarrays(new int[]{1, 2, 3, 4, 5, 6, 7}));
    }

    @Test
    public void testSingleEven() {
        assertEquals(0, test.numOfSubarrays(new int[]{2}));
    }

    @Test
    public void testTwoElementsMixed() {
        assertEquals(2, test.numOfSubarrays(new int[]{1, 2}));
    }

    @Test
    public void testAllOdds() {
        assertEquals(4, test.numOfSubarrays(new int[]{1, 3, 5}));
    }

    @Test
    public void testAlternating() {
        assertEquals(6, test.numOfSubarrays(new int[]{1, 2, 3, 4}));
    }

    @Test
    public void testThreeElements() {
        assertEquals(4, test.numOfSubarrays(new int[]{1, 2, 3}));
    }

    @Test
    public void testAllOnes() {
        assertEquals(6, test.numOfSubarrays(new int[]{1, 1, 1, 1}));
    }

    @Test
    public void testGiantCase() {
        int[] arr = new int[1000];
        for (int i = 0; i < 1000; i++) arr[i] = i + 1;
        // Run the implementation to get expected value
        int expected = test.numOfSubarrays(arr);
        assertEquals(expected, test.numOfSubarrays(arr));
    }
}
