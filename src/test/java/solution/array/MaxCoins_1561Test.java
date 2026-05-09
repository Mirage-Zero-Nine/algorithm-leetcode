package solution.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MaxCoins_1561Test {
    private final MaxCoins_1561 solution = new MaxCoins_1561();

    @Test
    void testBasic() {
        assertEquals(9, solution.maxCoins(new int[]{2, 4, 1, 2, 7, 8}));
    }

    @Test
    void testLargerArray() {
        assertEquals(4, solution.maxCoins(new int[]{2, 4, 5}));
    }

    @Test
    void testAllSame() {
        assertEquals(18, solution.maxCoins(new int[]{9, 8, 7, 6, 5, 1, 2, 3, 4}));
    }

    @Test
    void testSmallArray() {
        assertEquals(1, solution.maxCoins(new int[]{1, 1, 1}));
    }

    @Test
    void testDescending() {
        assertEquals(16, solution.maxCoins(new int[]{10, 9, 8, 7, 6, 5}));
    }
}
