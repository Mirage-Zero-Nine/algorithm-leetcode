package solution.array;

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
}
