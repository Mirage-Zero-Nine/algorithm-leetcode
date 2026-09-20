package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.Test;

/** Tests for searching a rotated non-decreasing array that may contain duplicates. */
public class Search_81Test {

    @Test
    public void testOfficialExamples() {
        Search_81 solution = new Search_81();
        assertTrue(solution.search(new int[]{2, 5, 6, 0, 0, 1, 2}, 0));
        assertFalse(solution.search(new int[]{2, 5, 6, 0, 0, 1, 2}, 3));
    }

    @Test
    public void testEmptyArrayUsesDocumentedGuard() {
        assertFalse(new Search_81().search(new int[0], 1));
    }

    @Test
    public void testSingleElementFoundAndAbsent() {
        Search_81 solution = new Search_81();
        assertTrue(solution.search(new int[]{5}, 5));
        assertFalse(solution.search(new int[]{5}, 4));
    }

    @Test
    public void testTwoElementsAtBothRotationPositions() {
        Search_81 solution = new Search_81();
        for (int[] values : new int[][]{{1, 2}, {2, 1}}) {
            assertTrue(solution.search(values.clone(), values[0]));
            assertTrue(solution.search(values.clone(), values[1]));
            assertFalse(solution.search(values.clone(), 3));
        }
    }

    @Test
    public void testUnrotatedArrayWithDuplicateRuns() {
        Search_81 solution = new Search_81();
        int[] values = {1, 1, 2, 2, 3, 3, 4, 4};
        for (int target = 0; target <= 5; target++) {
            assertSearchMatchesLinear(solution, values, target);
        }
    }

    @Test
    public void testAllElementsSamePresentAndAbsent() {
        Search_81 solution = new Search_81();
        int[] values = {7, 7, 7, 7, 7};
        assertTrue(solution.search(values.clone(), 7));
        assertFalse(solution.search(values.clone(), 8));
        assertFalse(solution.search(values.clone(), Integer.MIN_VALUE));
    }

    @Test
    public void testDuplicateEndpointsMaskThePivot() {
        Search_81 solution = new Search_81();
        assertTrue(solution.search(new int[]{1, 3, 1, 1, 1}, 3));
        assertTrue(solution.search(new int[]{3, 1, 1, 1, 1}, 3));
        assertTrue(solution.search(new int[]{1, 1, 1, 3, 1}, 3));
        assertFalse(solution.search(new int[]{1, 3, 1, 1, 1}, 2));
    }

    @Test
    public void testEveryRotationAndTargetOfDuplicateSortedArray() {
        Search_81 solution = new Search_81();
        int[] sorted = {0, 0, 1, 1, 2, 2, 3, 3};
        for (int pivot = 0; pivot < sorted.length; pivot++) {
            int[] rotated = rotate(sorted, pivot);
            for (int target = -1; target <= 4; target++) {
                assertSearchMatchesLinear(solution, rotated, target);
            }
        }
    }

    @Test
    public void testUniqueValuesAtEveryRotation() {
        Search_81 solution = new Search_81();
        int[] sorted = {-5, -2, 0, 3, 9, 12};
        for (int pivot = 0; pivot < sorted.length; pivot++) {
            int[] rotated = rotate(sorted, pivot);
            for (int target : sorted) {
                assertTrue(solution.search(rotated.clone(), target));
            }
            assertFalse(solution.search(rotated.clone(), 4));
        }
    }

    @Test
    public void testAbsentTargetsOutsideAndInsideGaps() {
        Search_81 solution = new Search_81();
        int[] values = {8, 9, 10, 1, 2, 4, 4, 6};
        for (int target : new int[]{-1, 0, 3, 5, 7, 11, 100}) {
            assertFalse(solution.search(values.clone(), target));
        }
    }

    @Test
    public void testNegativeAndZeroValuesAcrossPivot() {
        Search_81 solution = new Search_81();
        int[] values = {0, 0, 4, -8, -8, -3, -1};
        for (int target : new int[]{-8, -3, -1, 0, 4}) {
            assertTrue(solution.search(values.clone(), target));
        }
        for (int target : new int[]{-9, -2, 1, 3, 5}) {
            assertFalse(solution.search(values.clone(), target));
        }
    }

    @Test
    public void testIntegerExtremesWithDuplicateBounds() {
        Search_81 solution = new Search_81();
        int[] sorted = {Integer.MIN_VALUE, Integer.MIN_VALUE, -1, 0, 1,
                Integer.MAX_VALUE, Integer.MAX_VALUE};
        for (int pivot = 0; pivot < sorted.length; pivot++) {
            int[] rotated = rotate(sorted, pivot);
            for (int target : new int[]{Integer.MIN_VALUE, -1, 0, 1, Integer.MAX_VALUE}) {
                assertSearchMatchesLinear(solution, rotated, target);
            }
            for (int target : new int[]{Integer.MIN_VALUE + 1, Integer.MAX_VALUE - 1}) {
                assertFalse(solution.search(rotated.clone(), target));
            }
        }
    }

    @Test
    public void testInputIsNotModified() {
        Search_81 solution = new Search_81();
        int[] values = {6, 6, 7, 0, 1, 1, 3};
        int[] original = values.clone();
        solution.search(values, 1);
        solution.search(values, 8);
        assertArrayEquals(original, values);
    }

    @Test
    public void testSameInstanceCanBeReusedWithoutStateLeak() {
        Search_81 solution = new Search_81();
        assertTrue(solution.search(new int[]{4, 5, 6, 0, 1, 2}, 5));
        assertFalse(solution.search(new int[]{1, 1, 1, 1}, 2));
        assertTrue(solution.search(new int[]{9}, 9));
        assertFalse(solution.search(new int[]{2, 3, 4, 0, 1}, -1));
    }

    @Test
    public void testOneDistinctMinimumAtEveryPositionAmongDuplicates() {
        Search_81 solution = new Search_81();
        for (int position = 0; position < 257; position++) {
            int[] values = new int[257];
            Arrays.fill(values, 9);
            values[position] = -7;
            assertTrue(solution.search(values.clone(), -7));
            assertFalse(solution.search(values.clone(), -6));
        }
    }

    @Test
    public void testExhaustiveSmallSortedMultisetsAndAllRotations() {
        Search_81 solution = new Search_81();
        int[] domain = {-2, -1, 0, 1};
        for (int length = 1; length <= 7; length++) {
            enumerateSortedArrays(domain, new int[length], 0, 0, solution);
        }
    }

    @Test
    public void testSeededRandomRotatedMultisetsAgainstLinearSearch() {
        Search_81 solution = new Search_81();
        Random random = new Random(812026L);
        for (int trial = 0; trial < 350; trial++) {
            int[] sorted = new int[1 + random.nextInt(100)];
            for (int i = 0; i < sorted.length; i++) {
                sorted[i] = random.nextInt(41) - 20;
            }
            Arrays.sort(sorted);
            int[] rotated = rotate(sorted, random.nextInt(sorted.length));
            for (int target = -22; target <= 22; target++) {
                assertSearchMatchesLinear(solution, rotated, target);
            }
        }
    }

    @Test
    public void testMaximumLengthAllEqualAbsentAndPresent() {
        Search_81 solution = new Search_81();
        int[] values = new int[5000];
        Arrays.fill(values, 1234);
        assertTrue(solution.search(values.clone(), 1234));
        assertFalse(solution.search(values.clone(), 1233));
        assertFalse(solution.search(values.clone(), -10000));
    }

    @Test
    public void testMaximumLengthWithThreeDuplicateBlocksAndRotation() {
        Search_81 solution = new Search_81();
        int[] sorted = new int[5000];
        Arrays.fill(sorted, 0, 1600, -10000);
        Arrays.fill(sorted, 1600, 3300, 0);
        Arrays.fill(sorted, 3300, 5000, 10000);
        int[] rotated = rotate(sorted, 2345);
        for (int target : new int[]{-10000, 0, 10000}) {
            assertTrue(solution.search(rotated.clone(), target));
        }
        for (int target : new int[]{-9999, -1, 1, 9999}) {
            assertFalse(solution.search(rotated.clone(), target));
        }
    }

    @Test
    public void testMaximumLengthSingleOutlierAtEachRepresentativePosition() {
        Search_81 solution = new Search_81();
        for (int position : new int[]{0, 1, 2499, 2500, 4998, 4999}) {
            int[] values = new int[5000];
            Arrays.fill(values, -1);
            values[position] = 1;
            assertTrue(solution.search(values.clone(), 1));
            assertFalse(solution.search(values.clone(), 0));
        }
    }

    @Test
    public void testOfficialValueBoundariesAndPivotPositions() {
        Search_81 solution = new Search_81();
        int[] sorted = {-10000, -9999, -1, 0, 1, 9999, 10000};
        for (int pivot = 0; pivot < sorted.length; pivot++) {
            int[] rotated = rotate(sorted, pivot);
            assertTrue(solution.search(rotated.clone(), -10000));
            assertTrue(solution.search(rotated.clone(), 10000));
            assertFalse(solution.search(rotated.clone(), -10001));
            assertFalse(solution.search(rotated.clone(), 10001));
        }
    }

    @Test
    public void testRepeatedCallsWithFreshArraysForEveryTarget() {
        Search_81 solution = new Search_81();
        int[] values = {5, 5, 5, 7, 8, 1, 2, 5};
        for (int target : new int[]{1, 2, 5, 7, 8, 0, 3, 9}) {
            assertSearchMatchesLinear(solution, values, target);
        }
    }

    @Test
    public void testLongDuplicatePrefixAndSuffixWithInteriorGap() {
        Search_81 solution = new Search_81();
        int[] values = new int[301];
        Arrays.fill(values, 0, 100, 0);
        Arrays.fill(values, 100, 200, 2);
        Arrays.fill(values, 200, 301, 0);
        assertTrue(solution.search(values.clone(), 2));
        assertTrue(solution.search(values.clone(), 0));
        assertFalse(solution.search(values.clone(), 1));
    }

    private static void enumerateSortedArrays(int[] domain, int[] values, int index,
            int minimumDomainIndex, Search_81 solution) {
        if (index == values.length) {
            for (int pivot = 0; pivot < values.length; pivot++) {
                int[] rotated = rotate(values, pivot);
                for (int target = -3; target <= 2; target++) {
                    assertSearchMatchesLinear(solution, rotated, target);
                }
            }
            return;
        }
        for (int domainIndex = minimumDomainIndex; domainIndex < domain.length; domainIndex++) {
            values[index] = domain[domainIndex];
            enumerateSortedArrays(domain, values, index + 1, domainIndex, solution);
        }
    }

    private static int[] rotate(int[] sorted, int pivot) {
        int[] rotated = new int[sorted.length];
        for (int i = 0; i < sorted.length; i++) {
            rotated[i] = sorted[(i + pivot) % sorted.length];
        }
        return rotated;
    }

    private static void assertSearchMatchesLinear(Search_81 solution, int[] values, int target) {
        boolean expected = false;
        for (int value : values) {
            if (value == target) {
                expected = true;
                break;
            }
        }
        int[] input = values.clone();
        if (expected) {
            assertTrue(solution.search(input, target),
                    () -> "target " + target + " should be found in " + Arrays.toString(values));
        } else {
            assertFalse(solution.search(input, target),
                    () -> "target " + target + " should be absent from " + Arrays.toString(values));
        }
        assertArrayEquals(values, input);
    }
}
