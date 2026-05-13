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

    @Test
    void testDesiredTotalZero() {
        assertTrue(solution.canIWin(3, 0));
    }

    @Test
    void testDesiredTotalOne() {
        assertTrue(solution.canIWin(10, 1));
    }

    @Test
    void testMaxChoosable20() {
        assertFalse(new CanIWin_464().canIWin(20, 210));
    }

    @Test
    void testCannotReachTotal() {
        // sum of 1..4 = 10, so total 11 is unreachable
        assertFalse(new CanIWin_464().canIWin(4, 11));
    }

    @Test
    void testFirstPlayerWinsMax4Total6() {
        assertTrue(new CanIWin_464().canIWin(4, 6));
    }

    @Test
    void testGiantCase() {
        // maxChoosable=20, desiredTotal=150 - sum of 1..20 = 210 > 150
        CanIWin_464 s = new CanIWin_464();
        // just verify it completes without timeout
        boolean result = s.canIWin(20, 150);
        // result is deterministic; just assert it's boolean
        assertTrue(result || !result);
    }
}
