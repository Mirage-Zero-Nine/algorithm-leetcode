package solutions.greedy;

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

    @Test public void testNull() {
        assertEquals(0, solver.minimumMoves(null));
    }

    @Test public void testSingleX() {
        assertEquals(0, solver.minimumMoves("X"));
    }

    @Test public void testSingleO() {
        assertEquals(0, solver.minimumMoves("O"));
    }

    @Test public void testAllX() {
        assertEquals(2, solver.minimumMoves("XXXXXX"));
    }

    @Test public void testXAtEnd() {
        assertEquals(1, solver.minimumMoves("OOOOX"));
    }

    @Test public void testGiantCase() {
        String s = "X".repeat(3000);
        assertEquals(1000, solver.minimumMoves(s));
    }
}
