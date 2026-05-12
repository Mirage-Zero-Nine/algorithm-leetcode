package solution.backtracking;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CanIWin_464Test {
    private final CanIWin_464 solution = new CanIWin_464();

    @Test
    void testCannotWin() {
        assertFalse(solution.canIWin(10, 11));
    }

    @Test
    void testCanWin() {
        assertTrue(solution.canIWin(10, 0));
    }

    @Test
    void testSmallNumbers() {
        assertFalse(solution.canIWin(10, 40));
    }

    @Test
    void testImmediateWin() {
        assertTrue(solution.canIWin(5, 5));
    }

    @Test
    void testLargeTotal() {
        assertFalse(solution.canIWin(5, 50));
    }
}
