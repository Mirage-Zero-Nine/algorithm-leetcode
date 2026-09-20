package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2025/05/22 14:07
 * Created with IntelliJ IDEA
 */

public class MaximumCount_2529Test {

    private final MaximumCount_2529 test = new MaximumCount_2529();

    @Test
    public void test() {
        assertEquals(3, test.maximumCount(new int[]{-2, -1, -1, 1, 2, 3}));
        assertEquals(3, test.maximumCount(new int[]{-3, -2, -1, 0, 0, 1, 2}));
        assertEquals(4, test.maximumCount(new int[]{5, 20, 66, 1314}));
        assertEquals(4, test.maximumCount(new int[]{-52, -20, -6, -1}));
    }

    @Test
    public void testZero() {
        assertEquals(0, test.maximumCount(new int[]{}));
        assertEquals(0, test.maximumCount(new int[]{0}));
        assertEquals(0, test.maximumCount(new int[]{0, 0}));
        assertEquals(0, test.maximumCount(new int[]{0, 0, 0}));
        assertEquals(0, test.maximumCount(new int[]{0, 0, 0, 0}));
        assertEquals(0, test.maximumCount(new int[]{0, 0, 0, 0, 0}));
    }

    @Test
    public void testNullInput() {
        assertEquals(0, test.maximumCount(null));
    }

    @Test
    public void testMixedWithMoreNegatives() {
        assertEquals(5, test.maximumCount(new int[]{-10, -8, -6, -4, -2, 0, 1}));
    }

    @Test
    public void testMixedWithMorePositives() {
        assertEquals(6, test.maximumCount(new int[]{-1, 0, 2, 3, 4, 5, 6, 7}));
    }

    @Test
    public void testBoundaryAroundZero() {
        assertEquals(1, test.maximumCount(new int[]{-1, 0, 0, 0}));
        assertEquals(1, test.maximumCount(new int[]{0, 0, 0, 1}));
    }

    @Test
    public void testAllNegativeSingleElement() {
        assertEquals(1, test.maximumCount(new int[]{-1}));
    }

    @Test
    public void testAllPositiveSingleElement() {
        assertEquals(1, test.maximumCount(new int[]{1}));
    }

    @Test
    public void testGiantBalancedArray() {
        int[] nums = new int[2001];
        for (int i = 0; i < 1000; i++) {
            nums[i] = -2000 + i;
        }
        nums[1000] = 0;
        for (int i = 1001; i < nums.length; i++) {
            nums[i] = i - 1000;
        }
        assertEquals(1000, test.maximumCount(nums));
    }

    @Test
    public void testGiantMorePositives() {
        int[] nums = new int[3000];
        for (int i = 0; i < 900; i++) {
            nums[i] = -1000 + i;
        }
        for (int i = 900; i < 1000; i++) {
            nums[i] = 0;
        }
        for (int i = 1000; i < nums.length; i++) {
            nums[i] = i - 999;
        }
        assertEquals(2000, test.maximumCount(nums));
    }
@Test
    public void testEverySmallSignDistribution() {
        for (int negative = 0; negative <= 12; negative++) for (int zeros = 0; zeros <= 12; zeros++)
            for (int positive = 0; positive <= 12; positive++) {
                int[] values = new int[negative + zeros + positive];
                java.util.Arrays.fill(values, 0, negative, -1);
                java.util.Arrays.fill(values, negative + zeros, values.length, 1);
                assertEquals(Math.max(negative, positive), test.maximumCount(values));
            }
    }

    @Test
    public void testIntegerExtremesCountOnlyTheirSigns() {
        assertEquals(2, test.maximumCount(
                new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE, 0, Integer.MAX_VALUE}));
    }

    @Test
    public void testGiantZeroPlateauDoesNotContributeToEitherCount() {
        int[] values = new int[100000];
        java.util.Arrays.fill(values, 0, 7, -1);
        java.util.Arrays.fill(values, 99989, 100000, 1);
        assertEquals(11, test.maximumCount(values));
    }

    @Test
    public void testOfficialMaximumLengthAllNegative() {
        int[] values = new int[2000];
        Arrays.fill(values, -2000);
        assertEquals(2000, test.maximumCount(values));
    }

    @Test
    public void testOfficialMaximumLengthAllPositive() {
        int[] values = new int[2000];
        Arrays.fill(values, 2000);
        assertEquals(2000, test.maximumCount(values));
    }

    @Test
    public void testOfficialMaximumLengthBalancedWithZeroBlock() {
        int[] values = new int[2000];
        Arrays.fill(values, 0, 700, -2000);
        Arrays.fill(values, 700, 1300, 0);
        Arrays.fill(values, 1300, 2000, 2000);
        assertEquals(700, test.maximumCount(values));
    }

    @Test
    public void testOfficialValueBoundsWithDuplicates() {
        assertEquals(3, test.maximumCount(new int[]{-2000, -2000, -1, 0, 0, 1, 2000, 2000}));
        assertEquals(3, test.maximumCount(new int[]{-2000, -2000, -2000, 0, 2000}));
        assertEquals(3, test.maximumCount(new int[]{-2000, 0, 2000, 2000, 2000}));
    }

    @Test
    public void testZerosBetweenUnequalSignRuns() {
        assertEquals(4, test.maximumCount(new int[]{-4, -3, -2, -1, 0, 0, 1, 2}));
        assertEquals(4, test.maximumCount(new int[]{-4, -3, 0, 0, 0, 1, 2, 3, 4}));
        assertEquals(2, test.maximumCount(new int[]{-2, -1, 0, 0, 0, 1}));
    }

    @Test
    public void testOnlyOneSignAtValueBoundary() {
        assertEquals(1, test.maximumCount(new int[]{-2000, 0, 0, 0}));
        assertEquals(1, test.maximumCount(new int[]{0, 0, 0, 2000}));
        assertEquals(2, test.maximumCount(new int[]{-2000, -2000, 0, 0}));
        assertEquals(2, test.maximumCount(new int[]{0, 0, 2000, 2000}));
    }

    @Test
    public void testEqualSignCountsRemainEqualWithDifferentMagnitudes() {
        assertEquals(5, test.maximumCount(new int[]{-2000, -100, -2, -1, -1, 0, 1, 3, 100, 1999, 2000}));
        assertEquals(2, test.maximumCount(new int[]{-2000, -1, 0, 2000, 2000}));
    }

    @Test
    public void testInputIsNotMutated() {
        int[] values = {-2000, -7, -7, 0, 0, 3, 2000};
        int[] original = values.clone();
        assertEquals(3, test.maximumCount(values));
        assertArrayEquals(original, values);
    }

    @Test
    public void testRepeatedCallsAreIndependent() {
        assertEquals(3, test.maximumCount(new int[]{-3, -2, -1, 0, 1, 2}));
        assertEquals(5, test.maximumCount(new int[]{-9, -8, -7, -6, -5, 0, 1}));
        assertEquals(0, test.maximumCount(new int[]{0, 0, 0}));
        assertEquals(4, test.maximumCount(new int[]{1, 2, 3, 4}));
    }

    @Test
    public void testExhaustiveSortedArraysOverSmallValueDomain() {
        for (int length = 1; length <= 7; length++) {
            enumerateSortedArrays(new int[length], 0, -2, length);
        }
    }

    @Test
    public void testSeededSortedArraysAgainstIndependentOracle() {
        Random random = new Random(2529L);
        for (int caseNumber = 0; caseNumber < 250; caseNumber++) {
            int[] values = new int[1 + random.nextInt(2000)];
            for (int i = 0; i < values.length; i++) {
                values[i] = -2000 + random.nextInt(4001);
            }
            Arrays.sort(values);
            assertEquals(independentMaximumCount(values), test.maximumCount(values),
                    "random case " + caseNumber);
        }
    }

    @Test
    public void testJavaIntegerExtremesWithZeroAndDuplicates() {
        assertEquals(3, test.maximumCount(new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE, -1, 0, 0, Integer.MAX_VALUE}));
        assertEquals(3, test.maximumCount(new int[]{Integer.MIN_VALUE, -1, 0, 1, Integer.MAX_VALUE, Integer.MAX_VALUE}));
        assertEquals(3, test.maximumCount(new int[]{0, 0, 1, 1, Integer.MAX_VALUE}));
    }

    private void enumerateSortedArrays(int[] values, int position, int minimumValue, int length) {
        if (position == length) {
            assertEquals(independentMaximumCount(values), test.maximumCount(values),
                    "values=" + Arrays.toString(values));
            return;
        }
        for (int value = minimumValue; value <= 2; value++) {
            values[position] = value;
            enumerateSortedArrays(values, position + 1, value, length);
        }
    }

    private int independentMaximumCount(int[] values) {
        int negatives = 0;
        int positives = 0;
        for (int value : values) {
            if (value < 0) {
                negatives++;
            } else if (value > 0) {
                positives++;
            }
        }
        return Math.max(negatives, positives);
    }
}
