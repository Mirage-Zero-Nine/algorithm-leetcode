package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Contract tests for the immutable range-sum data structure.
 *
 * <p>Expected values are calculated with an independent {@code long[]} prefix
 * sum. This keeps the test oracle separate from the implementation's map and
 * also makes the legal one-billion-element totals explicit.</p>
 */
public class NumArray_303Test {

    @Test
    public void testOfficialExample() {
        NumArray_303 numArray = new NumArray_303(new int[]{-2, 0, 3, -5, 2, -1});

        assertEquals(1, numArray.sumRange(0, 2));
        assertEquals(-1, numArray.sumRange(2, 5));
        assertEquals(-3, numArray.sumRange(0, 5));
    }

    @Test
    public void testPositiveSingleton() {
        assertEquals(1, new NumArray_303(new int[]{1}).sumRange(0, 0));
    }

    @Test
    public void testNegativeSingleton() {
        assertEquals(-100_000, new NumArray_303(new int[]{-100_000}).sumRange(0, 0));
    }

    @Test
    public void testZeroSingleton() {
        assertEquals(0, new NumArray_303(new int[]{0}).sumRange(0, 0));
    }

    @Test
    public void testWholeRangeWithMixedSigns() {
        int[] nums = {-9, 4, 0, 12, -7, 3};

        assertEquals(3, new NumArray_303(nums).sumRange(0, nums.length - 1));
    }

    @Test
    public void testFirstAndLastElementRanges() {
        int[] nums = {8, -3, 5, 2, -11, 7};
        NumArray_303 numArray = new NumArray_303(nums);

        assertEquals(8, numArray.sumRange(0, 0));
        assertEquals(7, numArray.sumRange(nums.length - 1, nums.length - 1));
        assertEquals(8 - 3 + 5 + 2 - 11 + 7, numArray.sumRange(0, nums.length - 1));
    }

    @Test
    public void testAdjacentRanges() {
        int[] nums = {5, -3, 2, 8, -4};
        NumArray_303 numArray = new NumArray_303(nums);

        assertEquals(2, numArray.sumRange(0, 1));
        assertEquals(-1, numArray.sumRange(1, 2));
        assertEquals(10, numArray.sumRange(2, 3));
        assertEquals(4, numArray.sumRange(3, 4));
    }

    @Test
    public void testSubrangesAtBothBoundaries() {
        int[] nums = {2, 4, 6, 8, 10, 12};
        NumArray_303 numArray = new NumArray_303(nums);

        assertEquals(12, numArray.sumRange(0, 2));
        assertEquals(30, numArray.sumRange(3, 5));
        assertEquals(28, numArray.sumRange(1, 4));
    }

    @Test
    public void testAllNegativeNumbers() {
        int[] nums = {-1, -2, -3, -4, -5};
        NumArray_303 numArray = new NumArray_303(nums);

        assertEquals(-15, numArray.sumRange(0, 4));
        assertEquals(-5, numArray.sumRange(1, 2));
        assertEquals(-12, numArray.sumRange(2, 4));
    }

    @Test
    public void testAllZeros() {
        int[] nums = {0, 0, 0, 0, 0};
        NumArray_303 numArray = new NumArray_303(nums);

        assertEquals(0, numArray.sumRange(0, 4));
        assertEquals(0, numArray.sumRange(2, 3));
        assertEquals(0, numArray.sumRange(1, 1));
    }

    @Test
    public void testAlternatingSignsAndCancellation() {
        int[] nums = {1, -1, 1, -1, 1, -1};
        NumArray_303 numArray = new NumArray_303(nums);

        assertEquals(0, numArray.sumRange(0, 5));
        assertEquals(1, numArray.sumRange(0, 4));
        assertEquals(0, numArray.sumRange(1, 4));
        assertEquals(-1, numArray.sumRange(3, 5));
    }

    @Test
    public void testDuplicateValues() {
        int[] nums = {7, 7, -2, 7, -2, 7};
        NumArray_303 numArray = new NumArray_303(nums);

        assertEquals(24, numArray.sumRange(0, 5));
        assertEquals(12, numArray.sumRange(1, 3));
        assertEquals(3, numArray.sumRange(2, 4));
    }

    @Test
    public void testContractValueExtremesMixed() {
        int[] nums = {-100_000, 100_000, 100_000, -100_000, 0};
        NumArray_303 numArray = new NumArray_303(nums);

        assertEquals(0, numArray.sumRange(0, 3));
        assertEquals(200_000, numArray.sumRange(1, 2));
        assertEquals(0, numArray.sumRange(0, 4));
    }

    @Test
    public void testMaximumLegalPositiveTotal() {
        int[] nums = filled(10_000, 100_000);
        NumArray_303 numArray = new NumArray_303(nums);

        assertEquals(1_000_000_000, numArray.sumRange(0, nums.length - 1));
        assertEquals(100_000, numArray.sumRange(9_999, 9_999));
        assertEquals(500_000_000, numArray.sumRange(0, 4_999));
    }

    @Test
    public void testMaximumLegalNegativeTotal() {
        int[] nums = filled(10_000, -100_000);
        NumArray_303 numArray = new NumArray_303(nums);

        assertEquals(-1_000_000_000, numArray.sumRange(0, nums.length - 1));
        assertEquals(-100_000, numArray.sumRange(9_999, 9_999));
        assertEquals(-500_000_000, numArray.sumRange(2_500, 7_499));
    }

    @Test
    public void testIndependentLongPrefixOracleForEveryRange() {
        int[] nums = {-100_000, 4, 0, 99_999, -7, 12, 100_000};
        NumArray_303 numArray = new NumArray_303(nums);

        for (int left = 0; left < nums.length; left++) {
            for (int right = left; right < nums.length; right++) {
                assertEquals(longPrefixOracle(nums, left, right), numArray.sumRange(left, right),
                        "range [" + left + ", " + right + "]");
            }
        }
    }

    @Test
    public void testIndependentOracleForIncreasingValues() {
        int[] nums = new int[25];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i - 12;
        }
        NumArray_303 numArray = new NumArray_303(nums);

        for (int left = 0; left < nums.length; left += 2) {
            for (int right = left; right < nums.length; right += 3) {
                assertEquals(longPrefixOracle(nums, left, right), numArray.sumRange(left, right));
            }
        }
    }

    @Test
    public void testSeededMixedValuesAgainstLongOracle() {
        int[] nums = new int[101];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = ((i * 73 + 19) % 200_001) - 100_000;
        }
        NumArray_303 numArray = new NumArray_303(nums);

        for (int query = 0; query < 250; query++) {
            int left = (query * 17) % nums.length;
            int right = left + ((query * 29) % (nums.length - left));
            assertEquals(longPrefixOracle(nums, left, right), numArray.sumRange(left, right));
        }
    }

    @Test
    public void testAllRangesOnShortArray() {
        int[] nums = {3, -1, 4, -1, 5, -9, 2};
        NumArray_303 numArray = new NumArray_303(nums);

        for (int left = 0; left < nums.length; left++) {
            for (int right = left; right < nums.length; right++) {
                assertEquals(longPrefixOracle(nums, left, right), numArray.sumRange(left, right));
            }
        }
    }

    @Test
    public void testRepeatedQueriesRemainStable() {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArray_303 numArray = new NumArray_303(nums);

        for (int iteration = 0; iteration < 20; iteration++) {
            assertEquals(1, numArray.sumRange(0, 2));
            assertEquals(-1, numArray.sumRange(2, 5));
            assertEquals(-3, numArray.sumRange(0, 5));
            assertEquals(0, numArray.sumRange(1, 1));
        }
    }

    @Test
    public void testQueriesCanBeIssuedInAnyOrder() {
        int[] nums = {10, -20, 30, -40, 50, -60, 70};
        NumArray_303 numArray = new NumArray_303(nums);

        assertEquals(longPrefixOracle(nums, 4, 6), numArray.sumRange(4, 6));
        assertEquals(longPrefixOracle(nums, 0, 0), numArray.sumRange(0, 0));
        assertEquals(longPrefixOracle(nums, 1, 5), numArray.sumRange(1, 5));
        assertEquals(longPrefixOracle(nums, 0, 6), numArray.sumRange(0, 6));
        assertEquals(longPrefixOracle(nums, 2, 2), numArray.sumRange(2, 2));
    }

    @Test
    public void testConstructorTakesSnapshotBeforeCallerMutation() {
        int[] nums = {1, 2, 3, 4};
        NumArray_303 numArray = new NumArray_303(nums);
        nums[0] = 100_000;
        nums[1] = -100_000;
        nums[2] = 99_999;
        nums[3] = -99_999;

        assertEquals(10, numArray.sumRange(0, 3));
        assertEquals(5, numArray.sumRange(1, 2));
    }

    @Test
    public void testSeparateInstancesDoNotSharePrefixState() {
        NumArray_303 first = new NumArray_303(new int[]{1, 2, 3});
        NumArray_303 second = new NumArray_303(new int[]{-10, 20, -30, 40});

        assertEquals(6, first.sumRange(0, 2));
        assertEquals(-20, second.sumRange(0, 2));
        assertEquals(2, first.sumRange(1, 1));
        assertEquals(30, second.sumRange(1, 3));
    }

    @Test
    public void testMinimumAndMaximumSignedValuesWithinJavaInt() {
        int[] nums = {Integer.MIN_VALUE, Integer.MAX_VALUE, 0};
        NumArray_303 numArray = new NumArray_303(nums);

        assertEquals(-1, numArray.sumRange(0, 1));
        assertEquals(Integer.MIN_VALUE, numArray.sumRange(0, 0));
        assertEquals(Integer.MAX_VALUE, numArray.sumRange(1, 2));
    }

    @Test
    public void testMaximumArraySizeWithMixedContractValues() {
        int[] nums = new int[10_000];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = switch (i % 5) {
                case 0 -> 100_000;
                case 1 -> -100_000;
                case 2 -> 1;
                case 3 -> -1;
                default -> 0;
            };
        }
        NumArray_303 numArray = new NumArray_303(nums);

        assertEquals(0, numArray.sumRange(0, nums.length - 1));
        assertEquals(longPrefixOracle(nums, 1, 8_999), numArray.sumRange(1, 8_999));
        assertEquals(longPrefixOracle(nums, 9_997, 9_999), numArray.sumRange(9_997, 9_999));
    }

    @Test
    public void testMaximumTenThousandQueriesAgainstLongOracle() {
        int[] nums = new int[10_000];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = ((i * 31 + 7) % 200_001) - 100_000;
        }
        NumArray_303 numArray = new NumArray_303(nums);

        for (int query = 0; query < 10_000; query++) {
            int left = (query * 9_973) % nums.length;
            int right = left + ((query * 7_919) % (nums.length - left));
            assertEquals(longPrefixOracle(nums, left, right), numArray.sumRange(left, right));
        }
    }

    @Test
    public void testSingleElementQueriesAtEveryMaximumArrayPosition() {
        int[] nums = new int[10_000];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = (i % 2 == 0) ? 100_000 : -100_000;
        }
        NumArray_303 numArray = new NumArray_303(nums);

        for (int i = 0; i < nums.length; i++) {
            assertEquals(nums[i], numArray.sumRange(i, i));
        }
    }

    @Test
    public void testContractBoundaryTotalsWithIndependentOracle() {
        int[] nums = {100_000, 100_000, -100_000, -100_000, 100_000, -100_000};
        NumArray_303 numArray = new NumArray_303(nums);

        for (int left = 0; left < nums.length; left++) {
            for (int right = left; right < nums.length; right++) {
                assertEquals(longPrefixOracle(nums, left, right), numArray.sumRange(left, right));
            }
        }
    }

    private static int longPrefixOracle(int[] nums, int left, int right) {
        long[] prefix = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        long result = prefix[right + 1] - prefix[left];
        // LeetCode 303's value and length bounds guarantee an int result.
        return Math.toIntExact(result);
    }

    private static int[] filled(int length, int value) {
        int[] nums = new int[length];
        for (int i = 0; i < length; i++) {
            nums[i] = value;
        }
        return nums;
    }
}
