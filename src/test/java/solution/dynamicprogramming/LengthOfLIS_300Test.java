package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
import org.junit.jupiter.api.Test;

public class LengthOfLIS_300Test {

    private final LengthOfLIS_300 test = new LengthOfLIS_300();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        assertEquals(4, test.lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(1, test.lengthOfLIS(new int[]{7, 7, 7, 7, 7}));
        assertEquals(1, test.lengthOfLIS(new int[]{1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(6, test.lengthOfLIS(new int[]{1, 3, 5, 7, 9, 2, 4, 6, 8, 10}));
    }

    @Test
    public void testStrictlyIncreasing() {
        assertEquals(5, test.lengthOfLIS(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void testStrictlyDecreasing() {
        assertEquals(1, test.lengthOfLIS(new int[]{5, 4, 3, 2, 1}));
    }

    @Test
    public void testTwoElements() {
        assertEquals(2, test.lengthOfLIS(new int[]{1, 2}));
        assertEquals(1, test.lengthOfLIS(new int[]{2, 1}));
    }

    @Test
    public void testNegativeNumbers() {
        assertEquals(4, test.lengthOfLIS(new int[]{-5, -3, -1, 0}));
    }

    @Test
    public void testMixedNegativePositive() {
        assertEquals(5, test.lengthOfLIS(new int[]{-2, -1, 0, 1, 2}));
    }

    @Test
    public void testDuplicatesInSequence() {
        assertEquals(3, test.lengthOfLIS(new int[]{1, 1, 2, 2, 3, 3}));
    }

    @Test
    public void testGiantCase() {
        int[] arr = new int[1000];
        for (int i = 0; i < 1000; i++) {
            arr[i] = i;
        }
        assertEquals(1000, test.lengthOfLIS(arr));
    }

    @Test
    public void testEmptyArray() {
        assertEquals(0, test.lengthOfLIS(new int[]{}));
    }

    @Test
    public void testAllSameValue() {
        assertEquals(1, test.lengthOfLIS(new int[]{3, 3, 3, 3, 3, 3, 3}));
    }

    @Test
    public void testTwoEqualThenIncreasing() {
        assertEquals(4, test.lengthOfLIS(new int[]{5, 5, 6, 7, 8}));
    }

    @Test
    public void testNegativeToPositiveMix() {
        assertEquals(6, test.lengthOfLIS(new int[]{-10, -5, 0, 3, -2, 1, 4, 8}));
    }

    @Test
    public void testLeetCodeExample() {
        assertEquals(4, test.lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
    }

    @Test
    public void testAllDecreasingLong() {
        assertEquals(1, test.lengthOfLIS(new int[]{100, 90, 80, 70, 60, 50, 40, 30, 20, 10}));
    }

    @Test
    public void testAllIncreasingLong() {
        assertEquals(10, test.lengthOfLIS(new int[]{-5, -3, -1, 0, 2, 4, 6, 8, 10, 12}));
    }

    @Test
    public void testLargeRandomCrossCheck() {
        Random rand = new Random(42L);
        int n = 1500;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(10001) - 5000;
        }

        int result = test.lengthOfLIS(arr);

        // O(n^2) DP reference
        int[] dp = new int[n];
        int expected = 1;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            expected = Math.max(expected, dp[i]);
        }

        assertEquals(expected, result);
    }

    @Test
    public void testPropertyResultLessThanOrEqualN() {
        int[] arr = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5};
        int result = test.lengthOfLIS(arr);
        assertTrue(result > 0 && result <= arr.length);
    }

    @Test
    public void testPropertyLisOfReverseEqualsLds() {
        int[] arr = {3, 1, 8, 2, 5, 9, 4, 7};
        int lisResult = test.lengthOfLIS(arr);

        // Reverse the array
        int[] reversed = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            reversed[i] = arr[arr.length - 1 - i];
        }
        int lisOfReversed = test.lengthOfLIS(reversed);

        // LIS of reversed == LDS of original (longest decreasing subsequence)
        // Compute LDS of original via O(n^2) DP
        int n = arr.length;
        int[] dp = new int[n];
        int lds = 1;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            lds = Math.max(lds, dp[i]);
        }

        assertEquals(lds, lisOfReversed);
        assertEquals(lisResult, test.lengthOfLIS(arr)); // sanity
    }
}
