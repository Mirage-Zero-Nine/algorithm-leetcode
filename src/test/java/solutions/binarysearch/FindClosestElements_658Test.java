package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

/** Contract and regression tests for all three implementations of LeetCode 658. */
public class FindClosestElements_658Test {

    private final FindClosestElements_658 solution = new FindClosestElements_658();

    @Test
    void officialExamples() {
        assertAllMethods(new int[]{1, 2, 3, 4, 5}, 4, 3, List.of(1, 2, 3, 4));
        assertAllMethods(new int[]{1, 1, 2, 3, 4, 5}, 4, -1, List.of(1, 1, 2, 3));
    }

    @Test
    void targetBelowEveryElement() {
        assertAllMethods(new int[]{10, 20, 30, 40}, 2, -5, List.of(10, 20));
        assertAllMethods(new int[]{-8, -3, 0, 4}, 3, -100, List.of(-8, -3, 0));
    }

    @Test
    void targetAboveEveryElement() {
        assertAllMethods(new int[]{1, 2, 3, 4, 5}, 3, 100, List.of(3, 4, 5));
        assertAllMethods(new int[]{-9, -4, -1, 2}, 2, 100, List.of(-1, 2));
    }

    @Test
    void targetInsideArrayAndExactHit() {
        assertAllMethods(new int[]{1, 2, 3, 4, 5, 6}, 3, 4, List.of(3, 4, 5));
        assertAllMethods(new int[]{-10, -5, 0, 5, 10}, 1, 0, List.of(0));
        assertAllMethods(new int[]{-10, -5, 0, 5, 10}, 4, 5, List.of(-5, 0, 5, 10));
    }

    @Test
    void tiesAlwaysPreferSmallerValues() {
        assertAllMethods(new int[]{1, 2, 3, 4, 5}, 4, 3, List.of(1, 2, 3, 4));
        assertAllMethods(new int[]{1, 2, 4, 5}, 2, 3, List.of(2, 4));
        assertAllMethods(new int[]{-6, -4, -2, 0, 2}, 2, -1, List.of(-2, 0));
    }

    @Test
    void duplicateValuesAreSelectedAndRetained() {
        assertAllMethods(new int[]{1, 2, 2, 2, 3, 4}, 3, 2, List.of(2, 2, 2));
        assertAllMethods(new int[]{1, 1, 1, 2, 2, 3}, 5, 2, List.of(1, 1, 1, 2, 2));
        assertAllMethods(new int[]{-2, -2, -1, 0, 0, 0, 5}, 4, 0, List.of(-1, 0, 0, 0));
    }

    @Test
    void negativeAndMixedSignValues() {
        assertAllMethods(new int[]{-10, -5, -2, 0, 3}, 2, -4, List.of(-5, -2));
        assertAllMethods(new int[]{-100, -50, -1, 1, 50, 100}, 3, 0, List.of(-50, -1, 1));
        assertAllMethods(new int[]{-9, -7, -3, 2, 6, 11}, 5, 4, List.of(-7, -3, 2, 6, 11));
    }

    @Test
    void kOneChoosesTheCorrectTieAndBoundary() {
        assertAllMethods(new int[]{1, 2, 3, 4, 5}, 1, 3, List.of(3));
        assertAllMethods(new int[]{1, 2, 4, 5}, 1, 3, List.of(2));
        assertAllMethods(new int[]{1, 2, 4, 5}, 1, 100, List.of(5));
    }

    @Test
    void kEqualsArrayLengthReturnsTheWholeSortedArray() {
        assertAllMethods(new int[]{1, 2, 3}, 3, -100, List.of(1, 2, 3));
        assertAllMethods(new int[]{-4, -4, 0, 9}, 4, 100, List.of(-4, -4, 0, 9));
    }

    @Test
    void smallestArrays() {
        assertAllMethods(new int[]{7}, 1, Integer.MIN_VALUE, List.of(7));
        assertAllMethods(new int[]{-1, 1}, 1, 0, List.of(-1));
        assertAllMethods(new int[]{-1, 1}, 2, 0, List.of(-1, 1));
    }

    @Test
    void windowsAtBothEnds() {
        assertAllMethods(new int[]{1, 2, 3, 4, 5, 6}, 3, -50, List.of(1, 2, 3));
        assertAllMethods(new int[]{1, 2, 3, 4, 5, 6}, 3, 50, List.of(4, 5, 6));
        assertAllMethods(new int[]{-10, -5, 0, 5, 10}, 4, -10, List.of(-10, -5, 0, 5));
    }

    @Test
    void resultIsAlwaysSortedEvenWhenSelectionExpandsBothDirections() {
        assertAllMethods(new int[]{-20, -11, -5, 0, 7, 13, 25}, 5, 2, List.of(-11, -5, 0, 7, 13));
        assertAllMethods(new int[]{-30, -10, -1, 0, 1, 10, 30}, 6, 0, List.of(-30, -10, -1, 0, 1, 10));
    }

    @Test
    void officialValueBoundariesDoNotOverflowDistanceArithmetic() {
        assertAllMethods(new int[]{-10_000, -1, 0, 1, 10_000}, 3, 10_000, List.of(0, 1, 10_000));
        assertAllMethods(new int[]{-10_000, -1, 0, 1, 10_000}, 3, -10_000, List.of(-10_000, -1, 0));
        assertAllMethods(new int[]{-10_000, -5_000, 0, 5_000, 10_000}, 2, 0, List.of(-5_000, 0));
    }

    @Test
    void fullIntRangeDistancesUseLongArithmetic() {
        assertAllMethods(new int[]{Integer.MIN_VALUE, -1, 0, 1, Integer.MAX_VALUE}, 3, 0,
                List.of(-1, 0, 1));
        assertAllMethods(new int[]{Integer.MIN_VALUE, -1, 0, Integer.MAX_VALUE}, 2, Integer.MAX_VALUE,
                List.of(0, Integer.MAX_VALUE));
        assertAllMethods(new int[]{Integer.MIN_VALUE, -1, 0, Integer.MAX_VALUE}, 2, Integer.MIN_VALUE,
                List.of(Integer.MIN_VALUE, -1));
    }

    @Test
    void independentOracleCoversManySmallSortedArrays() {
        for (int n = 1; n <= 9; n++) {
            int[] arr = IntStream.range(0, n).map(i -> 2 * i - 8).toArray();
            for (int x = -12; x <= 12; x += 3) {
                for (int k = 1; k <= n; k++) {
                    assertAllMethods(arr, k, x, oracle(arr, k, x));
                }
            }
        }
    }

    @Test
    void independentOracleCoversDuplicatesAndSignedValues() {
        int[][] arrays = {
            {-8, -8, -3, -1, 0, 4, 4, 9},
            {-10, -5, -5, -5, 2, 8, 8},
            {Integer.MIN_VALUE, -100, -100, 0, 100, Integer.MAX_VALUE}
        };
        int[] targets = {Integer.MIN_VALUE, -6, -1, 0, 7, Integer.MAX_VALUE};
        for (int[] arr : arrays) {
            for (int x : targets) {
                for (int k = 1; k <= arr.length; k++) {
                    assertAllMethods(arr, k, x, oracle(arr, k, x));
                }
            }
        }
    }

    @Test
    void maximumOfficialArraySizeAndK() {
        int[] arr = IntStream.rangeClosed(-10_000, -1).toArray();
        assertAllMethods(arr, 5_000, 10_000, oracle(arr, 5_000, 10_000));
        assertAllMethods(arr, 9_999, -10_000, oracle(arr, 9_999, -10_000));
    }

    @Test
    void maximumOfficialArraySizeWithDuplicates() {
        int[] arr = new int[10_000];
        Arrays.fill(arr, 0, 3_333, -10_000);
        Arrays.fill(arr, 3_333, 6_666, 0);
        Arrays.fill(arr, 6_666, 10_000, 10_000);
        assertAllMethods(arr, 5_000, 1, oracle(arr, 5_000, 1));
    }

    @Test
    void callersArrayIsNotMutated() {
        int[] arr = {-10, -2, 0, 3, 11};
        int[] original = arr.clone();
        solution.findClosestElements(arr, 3, 1);
        assertArrayEquals(original, arr);
        solution.findClosestElementsBasicBinarySearch(arr, 3, 1);
        assertArrayEquals(original, arr);
        solution.findClosestElementsTwoPointers(arr, 3, 1);
        assertArrayEquals(original, arr);
    }

    @Test
    void repeatedCallsDoNotShareState() {
        assertAllMethods(new int[]{1, 2, 3, 4, 5}, 2, 1, List.of(1, 2));
        assertAllMethods(new int[]{-5, -1, 0, 6, 10}, 3, 8, List.of(0, 6, 10));
        assertAllMethods(new int[]{100, 200, 300}, 1, 250, List.of(200));
    }

    @Test
    void eachInvocationReturnsAnIndependentList() {
        int[] arr = {1, 2, 3, 4, 5};
        List<Integer> first = solution.findClosestElements(arr, 3, 3);
        List<Integer> second = solution.findClosestElements(arr, 3, 3);
        assertNotSame(first, second);
        first.set(0, 999);
        assertEquals(List.of(2, 3, 4), second);

        List<Integer> basic = solution.findClosestElementsBasicBinarySearch(arr, 3, 3);
        List<Integer> pointers = solution.findClosestElementsTwoPointers(arr, 3, 3);
        assertNotSame(basic, pointers);
        basic.set(0, 999);
        assertEquals(List.of(2, 3, 4), pointers);
    }

    @Test
    void methodsAgreeOnDeterministicNonuniformArrays() {
        int[] arr = {-100, -99, -50, -2, -1, 0, 1, 17, 18, 100};
        for (int x : new int[]{-120, -75, -2, 3, 19, 200}) {
            for (int k : new int[]{1, 2, 4, 7, 10}) {
                assertAllMethods(arr, k, x, oracle(arr, k, x));
            }
        }
    }

    @Test
    void adjacentTieAtDuplicateBoundaryUsesLowerValue() {
        assertAllMethods(new int[]{-5, -1, 1, 5}, 2, 0, List.of(-1, 1));
        assertAllMethods(new int[]{-5, -1, 1, 1, 5}, 3, 0, List.of(-1, 1, 1));
        assertAllMethods(new int[]{-5, -1, 1, 5, 5}, 3, 3, List.of(1, 5, 5));
    }

    private void assertAllMethods(int[] arr, int k, int x, List<Integer> expected) {
        assertEquals(expected, solution.findClosestElements(arr.clone(), k, x), "window binary search");
        assertEquals(expected, solution.findClosestElementsBasicBinarySearch(arr.clone(), k, x),
                "basic binary search");
        assertEquals(expected, solution.findClosestElementsTwoPointers(arr.clone(), k, x), "two pointers");
    }

    /** Independent value-based oracle; long distances avoid reproducing implementation overflow. */
    private List<Integer> oracle(int[] arr, int k, int x) {
        List<Integer> byDistance = Arrays.stream(arr).boxed()
                .collect(java.util.stream.Collectors.toCollection(ArrayList::new));
        byDistance.sort(Comparator.comparingLong((Integer value) -> Math.abs((long) value - x))
                .thenComparingInt(Integer::intValue));
        return byDistance.subList(0, k).stream().sorted().toList();
    }
}
