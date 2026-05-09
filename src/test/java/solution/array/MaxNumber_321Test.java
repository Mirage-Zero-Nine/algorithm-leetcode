package solution.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MaxNumber_321Test {
    private final MaxNumber_321 solution = new MaxNumber_321();

    @Test
    void testBasic() {
        assertArrayEquals(new int[]{9, 8, 6, 5, 3}, solution.maxNumber(new int[]{3, 4, 6, 5}, new int[]{9, 1, 2, 5, 8, 3}, 5));
    }

    @Test
    void testAllFromBoth() {
        assertArrayEquals(new int[]{6, 7, 6, 0, 4}, solution.maxNumber(new int[]{6, 7}, new int[]{6, 0, 4}, 5));
    }

    @Test
    void testSingleDigit() {
        assertArrayEquals(new int[]{9}, solution.maxNumber(new int[]{3, 9}, new int[]{8, 9}, 1));
    }

    @Test
    void testAllFromOne() {
        assertArrayEquals(new int[]{9, 8, 7}, solution.maxNumber(new int[]{9, 8, 7}, new int[]{1, 2}, 3));
    }

    @Test
    void testEmpty() {
        assertArrayEquals(new int[]{2, 1}, solution.maxNumber(new int[]{2, 1}, new int[]{}, 2));
    }
}
