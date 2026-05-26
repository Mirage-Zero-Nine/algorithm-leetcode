package solutions.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumSubarraysWithSum_930Test {

    private final NumSubarraysWithSum_930 solution = new NumSubarraysWithSum_930();

    @Test
    public void testBasicCase() {
        assertEquals(4, solution.numSubarraysWithSum(new int[]{1, 0, 1, 0, 1}, 2));
    }

    @Test
    public void testBasicCasePrefixSum() {
        assertEquals(4, solution.numSubarraysWithSumPrefixSum(new int[]{1, 0, 1, 0, 1}, 2));
    }

    @Test
    public void testSumZero() {
        assertEquals(27, solution.numSubarraysWithSum(new int[]{0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, 0));
    }

    @Test
    public void testSumZeroPrefixSum() {
        assertEquals(27, solution.numSubarraysWithSumPrefixSum(new int[]{0, 0, 0, 0, 0, 0, 1, 0, 0, 0}, 0));
    }

    @Test
    public void testAllOnes() {
        assertEquals(3, solution.numSubarraysWithSum(new int[]{1, 1, 1, 1}, 2));
    }

    @Test
    public void testAllOnesPrefixSum() {
        assertEquals(3, solution.numSubarraysWithSumPrefixSum(new int[]{1, 1, 1, 1}, 2));
    }

    @Test
    public void testSingleElement() {
        assertEquals(1, solution.numSubarraysWithSum(new int[]{1}, 1));
    }

    @Test
    public void testSumLargerThanArray() {
        assertEquals(0, solution.numSubarraysWithSum(new int[]{1, 0, 1}, 3));
    }

    @Test
    public void testAllZerosSumZero() {
        // [0],[0],[0],[0,0],[0,0],[0,0,0] = 6
        assertEquals(6, solution.numSubarraysWithSum(new int[]{0, 0, 0}, 0));
    }

    @Test
    public void testBothMethodsAgree() {
        int[] arr = {1, 0, 0, 1, 0, 1, 0, 0, 1};
        for (int s = 0; s <= 4; s++) {
            assertEquals(
                    solution.numSubarraysWithSumPrefixSum(arr, s),
                    solution.numSubarraysWithSum(arr, s)
            );
        }
    }

    @Test
    public void testGiantCase() {
        int[] data = new int[100000];
        for (int i = 0; i < 100000; i++) {
            data[i] = i % 2 == 0 ? 1 : 0;
        }
        int result = solution.numSubarraysWithSum(data, 2);
        int resultPrefix = solution.numSubarraysWithSumPrefixSum(data, 2);
        assertEquals(resultPrefix, result);
    }
}
