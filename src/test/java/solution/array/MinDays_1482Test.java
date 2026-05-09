package solution.array;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MinDays_1482Test {
    private final MinDays_1482 solution = new MinDays_1482();

    @Test
    void testBasic() {
        assertEquals(3, solution.minDays(new int[]{1, 10, 3, 10, 2}, 3, 1));
    }

    @Test
    void testImpossible() {
        assertEquals(-1, solution.minDays(new int[]{1, 10, 3, 10, 2}, 3, 2));
    }

    @Test
    void testLargeK() {
        assertEquals(12, solution.minDays(new int[]{7, 7, 7, 7, 12, 7, 7}, 2, 3));
    }

    @Test
    void testSingleBouquet() {
        assertEquals(9, solution.minDays(new int[]{1, 10, 2, 9, 3, 8, 4, 7, 5, 6}, 4, 2));
    }

    @Test
    void testMinDays() {
        assertEquals(1000000000, solution.minDays(new int[]{1000000000, 1000000000}, 1, 1));
    }
}
