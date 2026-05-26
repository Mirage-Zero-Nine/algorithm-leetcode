package solutions.greedy;

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

    @Test public void testTwoOnes() {
        assertFalse(solver.isPossible(new int[]{2, 2}));
    }

    @Test public void testThreeElements() {
        assertTrue(solver.isPossible(new int[]{8, 5}));
    }

    @Test public void testLargeImpossible() {
        assertFalse(solver.isPossible(new int[]{2, 900000002}));
    }

    @Test public void testPossibleSequence() {
        // [1,1] -> [1,2] -> [3,2] -> [3,5]
        assertTrue(solver.isPossible(new int[]{3, 5}));
    }

    @Test public void testGiant() {
        // [1, 1000000000] is possible since sum-max=1
        assertTrue(solver.isPossible(new int[]{1, 1000000000}));
    }
}
