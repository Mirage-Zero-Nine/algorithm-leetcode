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

    @Test public void testKEqualsOne() {
        // Only adjacent pairs: (1,3)&(2,0)=3+0+1=4, (2,0)&(5,10) too far, (5,10)&(6,-10)=10+(-10)+1=1
        assertEquals(4, solver.findMaxValueOfEquation(new int[][]{{1, 3}, {2, 0}, {5, 10}, {6, -10}}, 1));
    }

    @Test public void testAllPositiveY() {
        // (0,10)&(1,10): 10+10+1=21
        assertEquals(21, solver.findMaxValueOfEquation(new int[][]{{0, 10}, {1, 10}}, 5));
    }

    @Test public void testThreePointsAllInRange() {
        // (0,5)&(1,5)=5+5+1=11, (0,5)&(2,5)=5+5+2=12, (1,5)&(2,5)=5+5+1=11
        assertEquals(12, solver.findMaxValueOfEquation(new int[][]{{0, 5}, {1, 5}, {2, 5}}, 10));
    }

    @Test public void testGiant() {
        int n = 1000;
        int[][] points = new int[n][2];
        for (int i = 0; i < n; i++) {
            points[i] = new int[]{i, i};
        }
        // max is points[0]&points[999]: 0+999+999=1998
        assertEquals(1998, solver.findMaxValueOfEquation(points, 1000));
    }

    @Test public void testKTooSmallForBestPair() {
        // Best pair would be (0,100)&(10,100)=100+100+10=210 but k=5 excludes it
        // Within k=5: (0,100)&(5,1)=100+1+5=106, (5,1)&(10,100)=1+100+5=106
        assertEquals(106, solver.findMaxValueOfEquation(new int[][]{{0, 100}, {5, 1}, {10, 100}}, 5));
    }
}
