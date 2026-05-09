package solution.backtracking;

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
}
