package solutions.math;

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

    @Test public void testTargetEqualsStart() {
        assertTrue(solver.reachingPoints(5, 5, 5, 5));
    }

    @Test public void testOnlyXGrows() {
        // (1,1) -> (2,1) -> (3,1) -> ... -> (n,1)
        assertTrue(solver.reachingPoints(1, 1, 10, 1));
    }

    @Test public void testOnlyYGrows() {
        // (1,1) -> (1,2) -> (1,3) -> ... -> (1,n)
        assertTrue(solver.reachingPoints(1, 1, 1, 10));
    }

    @Test public void testLargeTarget() {
        assertTrue(solver.reachingPoints(1, 1, 1000000000, 1));
    }

    @Test public void testLargeUnreachable() {
        assertFalse(solver.reachingPoints(2, 3, 100, 100));
    }

    @Test public void testStartLargerThanTarget() {
        assertFalse(solver.reachingPoints(10, 10, 5, 5));
    }
}
