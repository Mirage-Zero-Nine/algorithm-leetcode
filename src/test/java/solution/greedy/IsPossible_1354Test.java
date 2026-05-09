package solution.greedy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IsPossible_1354Test {
    private final IsPossible_1354 solver = new IsPossible_1354();

    @Test public void testBasic() {
        assertTrue(solver.isPossible(new int[]{9, 3, 5}));
    }

    @Test public void testImpossible() {
        assertFalse(solver.isPossible(new int[]{1, 1, 1, 2}));
    }

    @Test public void testAllOnes() {
        assertTrue(solver.isPossible(new int[]{1, 1, 1}));
    }

    @Test public void testSingle() {
        assertTrue(solver.isPossible(new int[]{1}));
    }

    @Test public void testTwoElements() {
        assertTrue(solver.isPossible(new int[]{1, 1000000000}));
    }
}
