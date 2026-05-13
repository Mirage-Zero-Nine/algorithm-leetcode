package solution.divideandconquer;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
}