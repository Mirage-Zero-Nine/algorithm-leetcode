package solution.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FindMaxValueOfEquation_1499Test {
    private final FindMaxValueOfEquation_1499 solver = new FindMaxValueOfEquation_1499();

    @Test public void testExample1() {
        // yi + yj + |xi - xj|, max is points[0] and points[2]: 4+3+|1-3|=9 or points[2] and points[3]: 3+(-2)+|3-5|=3
        assertEquals(4, solver.findMaxValueOfEquation(new int[][]{{1, 3}, {2, 0}, {5, 10}, {6, -10}}, 1));
    }

    @Test public void testExample2() {
        assertEquals(3, solver.findMaxValueOfEquation(new int[][]{{0, 0}, {3, 0}, {9, 2}}, 3));
    }

    @Test public void testTwoPoints() {
        // yi + yj + |xi - xj| = 1 + 2 + |0-1| = 4
        assertEquals(4, solver.findMaxValueOfEquation(new int[][]{{0, 1}, {1, 2}}, 1));
    }

    @Test public void testLargeK() {
        // k large enough to include all pairs
        assertEquals(17, solver.findMaxValueOfEquation(new int[][]{{1, 3}, {2, 0}, {5, 10}, {6, -10}}, 10));
    }

    @Test public void testNegativeY() {
        // (-1) + (-2) + |0-1| = -2
        assertEquals(-2, solver.findMaxValueOfEquation(new int[][]{{0, -1}, {1, -2}}, 1));
    }
}
