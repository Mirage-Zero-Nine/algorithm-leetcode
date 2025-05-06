package solution.divideandconquer;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2025/05/06 15:16
 * Created with IntelliJ IDEA
 */

public class SortArray912Test {
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
}