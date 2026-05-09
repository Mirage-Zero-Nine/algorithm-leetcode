package solution.array;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MinCost_1578Test {
    private final MinCost_1578 solution = new MinCost_1578();

    @Test
    void testBasic() {
        assertEquals(3, solution.minCost("abaac", new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    void testNoDuplicates() {
        assertEquals(0, solution.minCost("abc", new int[]{1, 2, 3}));
    }

    @Test
    void testAllSame() {
        assertEquals(3, solution.minCost("aaa", new int[]{1, 2, 3}));
    }

    @Test
    void testTwoPairs() {
        assertEquals(9, solution.minCost("aabbcc", new int[]{1, 2, 3, 4, 5, 6}));
    }

    @Test
    void testSingleChar() {
        assertEquals(0, solution.minCost("a", new int[]{5}));
    }
}
