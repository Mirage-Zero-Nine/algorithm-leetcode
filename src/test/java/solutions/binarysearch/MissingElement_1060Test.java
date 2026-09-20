package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;
import org.junit.jupiter.api.Test;

/** Tests for the binary-search solution of LeetCode 1060. */
public class MissingElement_1060Test {

    private final MissingElement_1060 test = new MissingElement_1060();

    @Test
    public void testOfficialExamples() {
        assertEquals(5, test.missingElement(new int[]{4, 7, 9, 10}, 1));
        assertEquals(8, test.missingElement(new int[]{4, 7, 9, 10}, 3));
        assertEquals(6, test.missingElement(new int[]{1, 2, 4}, 3));
    }

    @Test
    public void testFirstMissingNumberInInitialGap() {
        assertEquals(11, test.missingElement(new int[]{10, 12, 13}, 1));
        assertEquals(12, test.missingElement(new int[]{10, 13}, 2));
    }

    @Test
    public void testInitialGapAndInteriorGapBoundaries() {
        int[] nums = {10, 14, 20};
        assertEquals(11, test.missingElement(nums, 1));
        assertEquals(13, test.missingElement(nums, 3));
        assertEquals(15, test.missingElement(nums, 4));
        assertEquals(19, test.missingElement(nums, 8));
    }

    @Test
    public void testInteriorGapFirstAndLastValues() {
        int[] nums = {1, 2, 10, 11, 20};
        assertEquals(3, test.missingElement(nums, 1));
        assertEquals(9, test.missingElement(nums, 7));
        assertEquals(12, test.missingElement(nums, 8));
        assertEquals(19, test.missingElement(nums, 15));
    }

    @Test
    public void testMissingNumberImmediatelyAfterLastElement() {
        assertEquals(6, test.missingElement(new int[]{1, 2, 3, 4, 5}, 1));
        assertEquals(21, test.missingElement(new int[]{10, 12, 15, 20}, 8));
    }

    @Test
    public void testMissingNumbersFarBeyondLastElement() {
        int[] nums = {10, 12, 15, 20};
        assertEquals(27, test.missingElement(nums, 14));
        assertEquals(1_000_000_002, test.missingElement(new int[]{1_000_000_000}, 2));
    }

    @Test
    public void testNoMissingNumbersBetweenConsecutiveValues() {
        int[] nums = {4, 5, 6, 7, 8};
        assertEquals(9, test.missingElement(nums, 1));
        assertEquals(108, test.missingElement(nums, 100));
    }

    @Test
    public void testAlternatingGaps() {
        int[] nums = {1, 3, 5, 7, 9};
        for (int k = 1; k <= 8; k++) {
            assertEquals(oracle(nums, k), test.missingElement(nums, k));
        }
    }

    @Test
    public void testSingleElementArray() {
        assertEquals(1001, test.missingElement(new int[]{1000}, 1));
        assertEquals(1007, test.missingElement(new int[]{1000}, 7));
        assertEquals(100_000_001, test.missingElement(new int[]{1}, 100_000_000));
    }

    @Test
    public void testSmallestAllowedValueAndLargeK() {
        assertEquals(2, test.missingElement(new int[]{1}, 1));
        assertEquals(100_000_003, test.missingElement(new int[]{1, 2, 3}, 100_000_000));
    }

    @Test
    public void testLargestAllowedArrayValueAndK() {
        int[] nums = {9_999_998, 10_000_000};
        assertEquals(9_999_999, test.missingElement(nums, 1));
        assertEquals(10_000_001, test.missingElement(nums, 2));
        assertEquals(109_999_999, test.missingElement(nums, 100_000_000));
    }

    @Test
    public void testMainExampleWithLargeNumbers() {
        assertEquals(746431, test.missingElement(
            new int[]{746421, 1033196, 1647541, 4775111, 7769817, 8030384}, 10));
    }

    @Test
    public void testLargeSingleGap() {
        assertEquals(1001, test.missingElement(new int[]{1000, 5000}, 1));
        assertEquals(4999, test.missingElement(new int[]{1000, 5000}, 3999));
        assertEquals(5001, test.missingElement(new int[]{1000, 5000}, 4000));
    }

    @Test
    public void testLargeArrayWithRegularGaps() {
        int[] nums = new int[10_000];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = 100 + 3 * i;
        }
        assertEquals(101, test.missingElement(nums, 1));
        assertEquals(30_096, test.missingElement(nums, 19_998));
        assertEquals(30_098, test.missingElement(nums, 19_999));
    }

    @Test
    public void testMaximumAllowedLengthConsecutiveArray() {
        int[] nums = new int[50_000];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i + 1;
        }
        assertEquals(50_001, test.missingElement(nums, 1));
        assertEquals(100_000_000, test.missingElement(nums, 99_950_000));
    }

    @Test
    public void testMaximumAllowedLengthWithLargeFinalGap() {
        int[] nums = new int[50_000];
        for (int i = 0; i < nums.length - 1; i++) {
            nums[i] = i + 1;
        }
        nums[nums.length - 1] = 10_000_000;
        assertEquals(50_000, test.missingElement(nums, 1));
        assertEquals(9_999_999, test.missingElement(nums, 9_950_000));
        assertEquals(100_050_000, test.missingElement(nums, 100_000_000));
    }

    @Test
    public void testSignedValuesSupportedByTheJavaMethod() {
        int[] nums = {-10, -7, -3, 0, 4};
        assertEquals(-9, test.missingElement(nums, 1));
        assertEquals(-8, test.missingElement(nums, 2));
        assertEquals(-6, test.missingElement(nums, 3));
        assertEquals(5, test.missingElement(nums, 11));
    }

    @Test
    public void testLargeNegativeValuesWithoutOverflow() {
        int[] nums = {-2_000_000_000, -1_999_999_990, -1_999_999_980};
        assertEquals(-1_999_999_999, test.missingElement(nums, 1));
        assertEquals(-1_999_999_981, test.missingElement(nums, 18));
        assertEquals(-1_999_999_979, test.missingElement(nums, 19));
    }

    @Test
    public void testNearIntegerMinimumRange() {
        int[] nums = {Integer.MIN_VALUE, Integer.MIN_VALUE + 2, Integer.MIN_VALUE + 5};
        assertEquals(Integer.MIN_VALUE + 1, test.missingElement(nums, 1));
        assertEquals(Integer.MIN_VALUE + 3, test.missingElement(nums, 2));
        assertEquals(Integer.MIN_VALUE + 4, test.missingElement(nums, 3));
        assertEquals(Integer.MIN_VALUE + 6, test.missingElement(nums, 4));
    }

    @Test
    public void testInputIsNotMutated() {
        int[] nums = {4, 7, 9, 10};
        int[] original = nums.clone();
        test.missingElement(nums, 3);
        assertArrayEquals(original, nums);
    }

    @Test
    public void testRepeatedCallsDoNotShareState() {
        int[] first = {4, 7, 9, 10};
        int[] second = {1, 2, 4};
        assertEquals(8, test.missingElement(first, 3));
        assertEquals(6, test.missingElement(second, 3));
        assertEquals(5, test.missingElement(first, 1));
    }

    @Test
    public void testEverySmallUniqueSubsetAgainstIndependentOracle() {
        for (int mask = 1; mask < 256; mask++) {
            int[] values = new int[Integer.bitCount(mask)];
            int index = 0;
            for (int i = 0; i < 8; i++) {
                if ((mask & (1 << i)) != 0) {
                    values[index++] = 10 + i;
                }
            }
            for (int k = 1; k <= 12; k++) {
                assertEquals(oracle(values, k), test.missingElement(values, k),
                    "mask=" + mask + ", k=" + k);
            }
        }
    }

    @Test
    public void testExhaustiveSmallGapsWithDifferentStartingPoints() {
        for (int start = -3; start <= 5; start++) {
            for (int mask = 1; mask < 64; mask++) {
                int[] values = new int[Integer.bitCount(mask)];
                int index = 0;
                for (int i = 0; i < 6; i++) {
                    if ((mask & (1 << i)) != 0) {
                        values[index++] = start + i;
                    }
                }
                for (int k = 1; k <= 8; k++) {
                    assertEquals(oracle(values, k), test.missingElement(values, k));
                }
            }
        }
    }

    @Test
    public void testDeterministicRandomArraysAgainstLongOracle() {
        Random random = new Random(1060L);
        for (int caseNumber = 0; caseNumber < 200; caseNumber++) {
            int length = 1 + random.nextInt(30);
            int[] nums = new int[length];
            nums[0] = -100 + random.nextInt(200);
            for (int i = 1; i < length; i++) {
                nums[i] = nums[i - 1] + 1 + random.nextInt(8);
            }
            for (int k = 1; k <= 40; k++) {
                assertEquals(oracle(nums, k), test.missingElement(nums, k),
                    "case=" + caseNumber + ", k=" + k);
            }
        }
    }

    @Test
    public void testIndependentOracleHandlesEachGapBoundary() {
        int[] nums = {100, 103, 104, 110, 111, 120};
        int totalMissing = 0;
        for (int i = 1; i < nums.length; i++) {
            totalMissing += nums[i] - nums[i - 1] - 1;
            if (totalMissing > 0) {
                assertEquals(oracle(nums, totalMissing), test.missingElement(nums, totalMissing));
            }
        }
        assertEquals(oracle(nums, totalMissing + 1), test.missingElement(nums, totalMissing + 1));
    }

    private int oracle(int[] nums, int k) {
        long remaining = k;
        for (int i = 1; i < nums.length; i++) {
            long gap = (long) nums[i] - nums[i - 1] - 1;
            if (remaining <= gap) {
                return (int) ((long) nums[i - 1] + remaining);
            }
            remaining -= gap;
        }
        return (int) ((long) nums[nums.length - 1] + remaining);
    }
}
