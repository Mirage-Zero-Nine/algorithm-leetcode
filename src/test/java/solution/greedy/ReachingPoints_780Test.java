package solution.greedy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReachingPoints_780Test {
    private final ReachingPoints_780 solver = new ReachingPoints_780();

    @Test public void testBasicTrue() {
        assertTrue(solver.reachingPoints(1, 1, 3, 5));
    }

    @Test public void testBasicFalse() {
        assertFalse(solver.reachingPoints(1, 1, 2, 2));
    }

    @Test public void testSamePoint() {
        assertTrue(solver.reachingPoints(1, 1, 1, 1));
    }

    @Test public void testLarge() {
        assertTrue(solver.reachingPoints(1, 1, 5, 8));
    }

    @Test public void testCanNotReach() {
        assertFalse(solver.reachingPoints(3, 7, 3, 6));
    }
}
