package solution.greedy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinimumMoves_2027Test {
    private final MinimumMoves_2027 solver = new MinimumMoves_2027();

    @Test public void testBasic() {
        assertEquals(1, solver.minimumMoves("XXX"));
    }

    @Test public void testAllO() {
        assertEquals(0, solver.minimumMoves("OOOO"));
    }

    @Test public void testMixed() {
        assertEquals(2, solver.minimumMoves("XXOX"));
    }

    @Test public void testShort() {
        assertEquals(0, solver.minimumMoves("OO"));
    }

    @Test public void testLongString() {
        assertEquals(3, solver.minimumMoves("XOXOXOOXXX"));
    }
}
