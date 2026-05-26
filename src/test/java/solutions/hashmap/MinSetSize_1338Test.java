package solutions.hashmap;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MinSetSize_1338Test {
    private final MinSetSize_1338 solution = new MinSetSize_1338();

    @Test
    void testBasic() {
        assertEquals(2, solution.minSetSize(new int[]{3, 3, 3, 3, 5, 5, 5, 2, 2, 7}));
    }

    @Test
    void testAllSame() {
        assertEquals(1, solution.minSetSize(new int[]{7, 7, 7, 7, 7, 7}));
    }

    @Test
    void testAllDifferent() {
        assertEquals(1, solution.minSetSize(new int[]{1, 9}));
    }

    @Test
    void testLargeArray() {
        assertEquals(5, solution.minSetSize(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
    }

    @Test
    void testSortingMap() {
        assertEquals(2, solution.minSetSizeBySortingMap(new int[]{3, 3, 3, 3, 5, 5, 5, 2, 2, 7}));
    }

    @Test
    void testTwoElements() {
        assertEquals(1, solution.minSetSize(new int[]{1, 1}));
    }

    @Test
    void testSingleElement() {
        assertEquals(1, solution.minSetSize(new int[]{5, 5}));
    }

    @Test
    void testEvenDistribution() {
        assertEquals(2, solution.minSetSize(new int[]{1, 1, 2, 2, 3, 3, 4, 4}));
    }

    @Test
    void testSortingMapAllSame() {
        assertEquals(1, solution.minSetSizeBySortingMap(new int[]{7, 7, 7, 7, 7, 7}));
    }

    @Test
    void testSortingMapAllDifferent() {
        assertEquals(5, solution.minSetSizeBySortingMap(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
    }

    @Test
    void testGiantArray() {
        int[] arr = new int[10000];
        for (int i = 0; i < 10000; i++) {
            arr[i] = i % 100;
        }
        // 100 distinct values each appearing 100 times, need to remove 5000 => 50 values
        assertEquals(50, solution.minSetSize(arr));
    }
}
