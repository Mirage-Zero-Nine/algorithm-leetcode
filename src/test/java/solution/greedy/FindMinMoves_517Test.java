package solution.greedy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindMinMoves_517Test {
    private final FindMinMoves_517 solver = new FindMinMoves_517();

    @Test public void testBasic() {
        assertEquals(3, solver.findMinMoves(new int[]{1, 0, 5}));
    }

    @Test public void testEqualDresses() {
        // [2,2,2] total=6, avg=2
        assertEquals(0, solver.findMinMoves(new int[]{2, 2, 2}));
    }

    @Test public void testImpossible() {
        // Total 2, len 3, 2%3 != 0 -> -1
        assertEquals(-1, solver.findMinMoves(new int[]{0, 2, 0}));
    }

    @Test public void testNull() {
        assertEquals(-1, solver.findMinMoves(null));
    }

    @Test public void testComplex() {
        assertEquals(8, solver.findMinMoves(new int[]{0, 0, 11, 5}));
    }
}
