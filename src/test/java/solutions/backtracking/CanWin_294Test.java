package solutions.backtracking;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CanWin_294Test {
    private final CanWin_294 solution = new CanWin_294();

    @Test
    void testCanWin() {
        assertTrue(solution.canWin("++++"));
    }

    @Test
    void testCannotWin() {
        assertFalse(solution.canWin("+"));
    }

    @Test
    void testTwoPlus() {
        assertTrue(solution.canWin("++"));
    }

    @Test
    void testMixed() {
        assertTrue(solution.canWin("++++-"));
    }

    @Test
    void testEmpty() {
        assertFalse(solution.canWin(""));
    }

    @Test
    void testAllMinus() {
        assertFalse(solution.canWin("----"));
    }

    @Test
    void testThreePlus() {
        assertTrue(solution.canWin("+++"));
    }

    @Test
    void testFivePlus() {
        assertFalse(solution.canWin("+++++"));
    }

    @Test
    void testSixPlus() {
        assertTrue(solution.canWin("++++++"));
    }

    @Test
    void testSingleMinus() {
        assertFalse(solution.canWin("-"));
    }

    @Test
    void testAlternating() {
        assertFalse(solution.canWin("+-+-+-"));
    }

    @Test
    void testGiantString() {
        // 11 consecutive pluses
        assertTrue(solution.canWin("+++++++++++"));
    }
}
