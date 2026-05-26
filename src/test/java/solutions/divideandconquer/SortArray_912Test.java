package solutions.divideandconquer;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2025/05/06 15:16
 * Created with IntelliJ IDEA
 */

public class SortArray_912Test {
    private final SortArray_912 test = new SortArray_912();

    @Test
    public void test() {
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, test.sortArray(new int[]{1, 2, 3, 4, 5}));
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, test.sortArray(new int[]{5, 4, 3, 2, 1}));
        assertArrayEquals(new int[]{0, 1, 1, 2, 3, 5, 54}, test.sortArray(new int[]{0, 1, 5, 1, 2, 3, 54}));
        assertArrayEquals(new int[]{0, 1, 1, 2, 3, 3, 3, 3, 3, 5, 54}, test.sortArray(new int[]{0, 1, 5, 1, 2, 3, 3, 3, 3, 3, 54}));
        assertArrayEquals(new int[]{0, 100, 110}, test.sortArray(new int[]{110, 100, 0}));
    }

    @Test
    public void testEmpty() {
        assertArrayEquals(new int[]{}, test.sortArray(new int[]{}));
        assertArrayEquals(new int[]{1}, test.sortArray(new int[]{1}));
        assertArrayEquals(new int[]{1}, test.mergeSort(new int[]{1}));
        assertArrayEquals(new int[]{}, test.mergeSort(new int[]{}));
    }

    @Test
    public void testMerge() {
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, test.mergeSort(new int[]{1, 2, 3, 4, 5}));
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, test.mergeSort(new int[]{5, 4, 3, 2, 1}));
        assertArrayEquals(new int[]{0, 1, 1, 2, 3, 5, 54}, test.mergeSort(new int[]{0, 1, 5, 1, 2, 3, 54}));
        assertArrayEquals(new int[]{0, 1, 1, 2, 3, 3, 3, 3, 3, 5, 54}, test.mergeSort(new int[]{0, 1, 5, 1, 2, 3, 3, 3, 3, 3, 54}));
        assertArrayEquals(new int[]{0, 100, 110}, test.mergeSort(new int[]{110, 100, 0}));
    }

    @Test
    public void testNullInput() {
        assertNull(test.sortArray(null));
        assertNull(test.mergeSort(null));
    }

    @Test
    public void testNegativeNumbers() {
        assertArrayEquals(new int[]{-5, -3, -1, 0, 2, 4}, test.sortArray(new int[]{4, -1, 0, -5, 2, -3}));
        assertArrayEquals(new int[]{-5, -3, -1, 0, 2, 4}, test.mergeSort(new int[]{4, -1, 0, -5, 2, -3}));
    }

    @Test
    public void testAllSameElements() {
        assertArrayEquals(new int[]{7, 7, 7, 7}, test.sortArray(new int[]{7, 7, 7, 7}));
        assertArrayEquals(new int[]{7, 7, 7, 7}, test.mergeSort(new int[]{7, 7, 7, 7}));
    }

    @Test
    public void testTwoElements() {
        assertArrayEquals(new int[]{1, 2}, test.sortArray(new int[]{2, 1}));
        assertArrayEquals(new int[]{1, 2}, test.mergeSort(new int[]{2, 1}));
    }

    @Test
    public void testAlreadySorted() {
        assertArrayEquals(new int[]{-10, -5, 0, 5, 10}, test.sortArray(new int[]{-10, -5, 0, 5, 10}));
    }

    @Test
    public void testLargeValues() {
        assertArrayEquals(new int[]{Integer.MIN_VALUE, -1, 0, 1, Integer.MAX_VALUE},
                test.sortArray(new int[]{0, Integer.MAX_VALUE, -1, Integer.MIN_VALUE, 1}));
        assertArrayEquals(new int[]{Integer.MIN_VALUE, -1, 0, 1, Integer.MAX_VALUE},
                test.mergeSort(new int[]{0, Integer.MAX_VALUE, -1, Integer.MIN_VALUE, 1}));
    }

    @Test
    public void testGiantArray() {
        int[] arr = new int[10000];
        int[] expected = new int[10000];
        for (int i = 0; i < 10000; i++) {
            arr[i] = 10000 - i;
            expected[i] = i + 1;
        }
        assertArrayEquals(expected, test.sortArray(arr));
    }

    @Test
    public void testGiantArrayMergeSort() {
        int[] arr = new int[10000];
        int[] expected = new int[10000];
        for (int i = 0; i < 10000; i++) {
            arr[i] = 10000 - i;
            expected[i] = i + 1;
        }
        assertArrayEquals(expected, test.mergeSort(arr));
    }

    @Test
    public void testTwoElementsSorted() {
        assertArrayEquals(new int[]{1, 2}, test.sortArray(new int[]{1, 2}));
        assertArrayEquals(new int[]{1, 2}, test.mergeSort(new int[]{1, 2}));
    }

    @Test
    public void testAllNegative() {
        assertArrayEquals(new int[]{-10, -7, -5, -3, -1}, test.sortArray(new int[]{-3, -10, -1, -7, -5}));
        assertArrayEquals(new int[]{-10, -7, -5, -3, -1}, test.mergeSort(new int[]{-3, -10, -1, -7, -5}));
    }

    @Test
    public void testAlreadySortedDescending() {
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, test.sortArray(new int[]{8, 7, 6, 5, 4, 3, 2, 1}));
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, test.mergeSort(new int[]{8, 7, 6, 5, 4, 3, 2, 1}));
    }

    @Test
    public void testManyDuplicates() {
        int[] input = {3, 1, 3, 3, 2, 1, 1, 3, 2, 2};
        assertArrayEquals(new int[]{1, 1, 1, 2, 2, 2, 3, 3, 3, 3}, test.sortArray(input.clone()));
        assertArrayEquals(new int[]{1, 1, 1, 2, 2, 2, 3, 3, 3, 3}, test.mergeSort(input.clone()));
    }

    @Test
    public void testSortedExceptOneOutOfPlace() {
        // 1,2,3,4,5,6,7,8 but 8 is placed at index 2
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, test.sortArray(new int[]{1, 2, 8, 3, 4, 5, 6, 7}));
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, test.mergeSort(new int[]{1, 2, 8, 3, 4, 5, 6, 7}));
    }

    @Test
    public void testLargeRandomCrossCheckedWithArraysSort() {
        Random rng = new Random(42L);
        int[] input = new int[2000];
        for (int i = 0; i < input.length; i++) {
            input[i] = rng.nextInt(200001) - 100000;
        }

        int[] expectedQs = input.clone();
        Arrays.sort(expectedQs);

        int[] quickSortInput = input.clone();
        int[] mergeSortInput = input.clone();

        assertArrayEquals(expectedQs, test.sortArray(quickSortInput));
        assertArrayEquals(expectedQs, test.mergeSort(mergeSortInput));
    }

    @Test
    public void testPropertyNonDecreasingAndPermutation() {
        Random rng = new Random(42L);
        int[] input = new int[1500];
        for (int i = 0; i < input.length; i++) {
            input[i] = rng.nextInt(1001) - 500;
        }

        int[] inputCopyForQs = input.clone();
        int[] inputCopyForMs = input.clone();

        int[] sortedQs = test.sortArray(inputCopyForQs);
        int[] sortedMs = test.mergeSort(inputCopyForMs);

        // Property: non-decreasing
        for (int i = 1; i < sortedQs.length; i++) {
            assertTrue(sortedQs[i - 1] <= sortedQs[i], "quicksort result not non-decreasing at index " + i);
        }
        for (int i = 1; i < sortedMs.length; i++) {
            assertTrue(sortedMs[i - 1] <= sortedMs[i], "mergesort result not non-decreasing at index " + i);
        }

        // Property: permutation (multiset equality)
        int[] originalSorted = input.clone();
        Arrays.sort(originalSorted);
        assertArrayEquals(originalSorted, sortedQs, "quicksort result is not a permutation of input");
        assertArrayEquals(originalSorted, sortedMs, "mergesort result is not a permutation of input");
    }

    @Test
    public void testMixNegativeAndPositive() {
        assertArrayEquals(new int[]{-100, -50, -1, 0, 1, 50, 100},
                test.sortArray(new int[]{100, -1, 50, 0, -50, 1, -100}));
        assertArrayEquals(new int[]{-100, -50, -1, 0, 1, 50, 100},
                test.mergeSort(new int[]{100, -1, 50, 0, -50, 1, -100}));
    }
}