package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.Test;

/** Contract and regression tests for searching a distinct rotated sorted array. */
public class Search_33Test {

    private final Search_33 test = new Search_33();

    @Test
    public void testOfficialExampleWithTargetInRotatedSuffix() {
        assertEquals(4, test.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
    }

    @Test
    public void testOfficialExampleWithAbsentTarget() {
        assertEquals(-1, test.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
    }

    @Test
    public void testOfficialSingletonAbsentExample() {
        assertEquals(-1, test.search(new int[]{1}, 0));
    }

    @Test
    public void testEmptyArrayUsesDocumentedImplementationGuard() {
        assertEquals(-1, test.search(new int[0], Integer.MIN_VALUE));
    }

    @Test
    public void testSingletonFound() {
        assertEquals(0, test.search(new int[]{9}, 9));
    }

    @Test
    public void testSingletonAbsent() {
        assertEquals(-1, test.search(new int[]{9}, 8));
    }

    @Test
    public void testBothTwoElementRotationsAndAbsentTargets() {
        for (int[] values : new int[][]{{1, 2}, {2, 1}}) {
            for (int target : new int[]{0, 1, 2, 3}) {
                assertExpectedIndex(values, target);
            }
        }
    }

    @Test
    public void testUnrotatedArrayFindsBothEndsAndMiddle() {
        int[] values = {1, 2, 3, 4, 5};
        assertEquals(0, test.search(values, 1));
        assertEquals(2, test.search(values, 3));
        assertEquals(4, test.search(values, 5));
    }

    @Test
    public void testLastPossibleRotation() {
        int[] values = {9, 0, 1, 2, 3, 4, 5, 6, 7, 8};
        for (int i = 0; i < values.length; i++) {
            assertEquals(i, test.search(values, values[i]));
        }
    }

    @Test
    public void testTargetsAtPivotAndBothArrayEnds() {
        int[] values = {6, 7, 8, 9, 10, 1, 2, 3, 4, 5};
        assertEquals(0, test.search(values, 6));
        assertEquals(5, test.search(values, 1));
        assertEquals(9, test.search(values, 5));
    }

    @Test
    public void testTargetAtEverySortedHalfBoundary() {
        int[] values = {6, 7, 8, 1, 2, 3, 4, 5};
        for (int i = 0; i < values.length; i++) {
            assertEquals(i, test.search(values, values[i]));
        }
    }

    @Test
    public void testNegativeAndZeroValuesAcrossPivot() {
        int[] values = {3, 7, 12, -9, -4, -1, 0, 1};
        for (int i = 0; i < values.length; i++) {
            assertEquals(i, test.search(values, values[i]));
        }
        assertEquals(-1, test.search(values, -10));
        assertEquals(-1, test.search(values, 13));
    }

    @Test
    public void testSignedIntegerBoundaryValuesAcrossEveryRotation() {
        int[] sorted = {Integer.MIN_VALUE, -100, -1, 0, 1, 100, Integer.MAX_VALUE};
        for (int pivot = 0; pivot < sorted.length; pivot++) {
            int[] values = rotate(sorted, pivot);
            for (int i = 0; i < values.length; i++) {
                assertEquals(i, test.search(values, values[i]));
            }
            assertEquals(-1, test.search(values, Integer.MIN_VALUE + 1));
            assertEquals(-1, test.search(values, Integer.MAX_VALUE - 1));
        }
    }

    @Test
    public void testAbsentTargetsImmediatelyOutsideBothEnds() {
        int[] values = {20, 30, 40, 50, 0, 10};
        assertEquals(-1, test.search(values, -1));
        assertEquals(-1, test.search(values, 51));
    }

    @Test
    public void testAbsentTargetsInsideGaps() {
        int[] values = {40, 60, 80, 100, 10, 20};
        for (int target : new int[]{0, 15, 25, 50, 70, 90, 101}) {
            assertEquals(-1, test.search(values, target));
        }
    }

    @Test
    public void testExhaustiveSmallRotationsAgainstLinearOracle() {
        for (int length = 1; length <= 8; length++) {
            int[] sorted = new int[length];
            for (int i = 0; i < length; i++) {
                sorted[i] = -10 + i * 2;
            }
            for (int pivot = 0; pivot < length; pivot++) {
                int[] values = rotate(sorted, pivot);
                for (int target = -12; target <= 6; target++) {
                    assertExpectedIndex(values, target);
                }
            }
        }
    }

    @Test
    public void testEveryRotationOfNineElementArray() {
        int[] sorted = {-40, -20, -7, 0, 3, 11, 25, 80, 100};
        for (int pivot = 0; pivot < sorted.length; pivot++) {
            int[] values = rotate(sorted, pivot);
            for (int value : sorted) {
                assertExpectedIndex(values, value);
            }
            assertExpectedIndex(values, -41);
            assertExpectedIndex(values, 101);
        }
    }

    @Test
    public void testInputIsNotMutatedAndInstanceCanBeReused() {
        int[] values = {5, 6, 7, 0, 1, 2, 3, 4};
        int[] original = values.clone();
        assertEquals(6, test.search(values, 3));
        assertEquals(3, test.search(values, 0));
        assertEquals(-1, test.search(values, 8));
        assertArrayEquals(original, values);
    }

    @Test
    public void testCallsOnDifferentArrayShapesDoNotShareState() {
        assertEquals(2, test.search(new int[]{4, 5, 1, 2, 3}, 1));
        assertEquals(0, test.search(new int[]{1, 2, 3}, 1));
        assertEquals(-1, test.search(new int[]{9}, 4));
        assertEquals(4, test.search(new int[]{8, 9, 10, 11, 12}, 12));
    }

    @Test
    public void testMaximumOfficialLengthWithPivotAndAbsentTargets() {
        int n = 5000;
        int pivot = 2711;
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = (i + pivot) % n - 2500;
        }
        assertEquals(1234, test.search(values, values[1234]));
        assertEquals(-1, test.search(values, 2500));
        assertEquals(-1, test.search(values, -2501));
    }

    @Test
    public void testMaximumOfficialLengthEveryTarget() {
        int n = 5000;
        int pivot = 4999;
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = (i + pivot) % n;
        }
        for (int i = 0; i < n; i++) {
            assertEquals(i, test.search(values, values[i]));
        }
    }

    @Test
    public void testMaximumOfficialValuesAndTargetBounds() {
        int[] values = {-10000, -9999, -1, 0, 1, 9999, 10000};
        for (int pivot = 0; pivot < values.length; pivot++) {
            int[] rotated = rotate(values, pivot);
            for (int i = 0; i < rotated.length; i++) {
                assertEquals(i, test.search(rotated, rotated[i]));
            }
            assertEquals(-1, test.search(rotated, -10001));
            assertEquals(-1, test.search(rotated, 10001));
        }
    }

    @Test
    public void testSeededRandomRotationsAgainstIndependentOracle() {
        Random random = new Random(33_2026L);
        for (int sample = 0; sample < 150; sample++) {
            int length = 1 + random.nextInt(80);
            int[] sorted = new int[length];
            int value = -500;
            for (int i = 0; i < length; i++) {
                value += 1 + random.nextInt(7);
                sorted[i] = value;
            }
            int[] values = rotate(sorted, random.nextInt(length));
            for (int target : sorted) {
                assertExpectedIndex(values, target);
            }
            for (int absent = 0; absent < 10; absent++) {
                assertExpectedIndex(values, -700 + random.nextInt(1400));
            }
        }
    }

    @Test
    public void testSparseLargeRotationAndGaps() {
        int n = 5000;
        int pivot = 3765;
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = ((i + pivot) % n) * 3 - 7000;
        }
        for (int index : new int[]{0, 1, 2499, 2500, 4999}) {
            assertEquals(index, test.search(values, values[index]));
            assertEquals(-1, test.search(values, values[index] + 1));
        }
    }

    @Test
    public void testLinearOracleReportsExactIndexForEveryHit() {
        int[] values = {15, 18, 21, -12, -9, -3, 0, 6, 9};
        for (int index = 0; index < values.length; index++) {
            assertEquals(linearIndex(values, values[index]), test.search(values, values[index]));
        }
    }

    @Test
    public void testSortedInputWithNegativeOnlyValues() {
        int[] values = {-9, -7, -5, -3, -1};
        assertEquals(0, test.search(values, -9));
        assertEquals(4, test.search(values, -1));
        assertEquals(-1, test.search(values, 0));
    }

    @Test
    public void testPivotNearMiddleWithOneElementHalves() {
        int[] values = {7, 8, 9, 10, 0, 1, 2, 3, 4, 5, 6};
        for (int index = 0; index < values.length; index++) {
            assertEquals(index, test.search(values, values[index]));
        }
    }

    @Test
    public void testNoMatchWhenTargetFallsBetweenPivotNeighbors() {
        int[] values = {50, 60, 70, 80, 5, 10, 20, 30};
        assertEquals(-1, test.search(values, 1));
        assertEquals(-1, test.search(values, 35));
        assertEquals(-1, test.search(values, 75));
    }

    private void assertExpectedIndex(int[] nums, int target) {
        assertEquals(linearIndex(nums, target), test.search(nums, target),
                () -> "nums=" + Arrays.toString(nums) + ", target=" + target);
    }

    private int linearIndex(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }

    private int[] rotate(int[] sorted, int pivot) {
        int[] rotated = new int[sorted.length];
        for (int i = 0; i < sorted.length; i++) {
            rotated[i] = sorted[(pivot + i) % sorted.length];
        }
        return rotated;
    }
}
