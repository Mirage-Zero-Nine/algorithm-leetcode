package solutions.greedy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TwoCitySchedCost_1029Test {
    private final TwoCitySchedCost_1029 solver = new TwoCitySchedCost_1029();

    @Test public void testBasicGreedy() {
        int[][] costs = {{10, 20}, {30, 200}, {400, 50}, {30, 20}};
        assertEquals(110, solver.twoCitySchedCost(costs));
    }

    @Test public void testSmallCase() {
        int[][] costs = {{1, 2}, {3, 4}};
        assertEquals(1 + 4, solver.twoCitySchedCost(costs));
    }

    @Test public void testLargerCase() {
        int[][] costs = {{259, 770}, {448, 54}, {926, 667}, {184, 139}, {840, 118}, {577, 469}};
        assertEquals(1859, solver.twoCitySchedCost(costs));
    }

    @Test public void testDynamicProgramming() {
        int[][] costs = {{10, 20}, {30, 200}, {400, 50}, {30, 20}};
        assertEquals(110, solver.dynamicProgramming(costs));
    }

    @Test public void testDynamicProgrammingSmall() {
        int[][] costs = {{1, 2}, {3, 4}};
        assertEquals(5, solver.dynamicProgramming(costs));
    }

    @Test public void testEqualCosts() {
        int[][] costs = {{5, 5}, {5, 5}};
        assertEquals(10, solver.twoCitySchedCost(costs));
    }

    @Test public void testAllPreferA() {
        int[][] costs = {{1, 100}, {2, 100}, {3, 100}, {4, 100}};
        // Sort by diff: 1-100=-99, 2-100=-98, 3-100=-97, 4-100=-96
        // First 2 go to A: 1+2=3, last 2 go to B: 100+100=200 => 203
        assertEquals(203, solver.twoCitySchedCost(costs));
    }

    @Test public void testDPLarger() {
        int[][] costs = {{259, 770}, {448, 54}, {926, 667}, {184, 139}, {840, 118}, {577, 469}};
        assertEquals(1859, solver.dynamicProgramming(costs));
    }

    @Test public void testTwoPeople() {
        int[][] costs = {{100, 1}, {1, 100}};
        assertEquals(2, solver.twoCitySchedCost(costs));
    }

    @Test public void testGiant() {
        int n = 500;
        int[][] costs = new int[n][2];
        for (int i = 0; i < n; i++) {
            costs[i][0] = i + 1;
            costs[i][1] = n - i;
        }
        // The first 250 people are cheaper for A and the remaining 250 for B.
        assertEquals(62750, solver.twoCitySchedCost(costs));
    }
    @Test public void testAdditionalTwo() { assertEquals(4, solver.twoCitySchedCost(new int[][]{{2, 3}, {3, 2}})); }
    @Test public void testAdditionalFour() { assertEquals(60, solver.twoCitySchedCost(new int[][]{{10, 100}, {20, 100}, {100, 20}, {100, 10}})); }
    @Test public void testAdditionalEqual() { assertEquals(40, solver.twoCitySchedCost(new int[][]{{10, 10}, {10, 10}, {10, 10}, {10, 10}})); }
    @Test public void testAdditionalPreferA() { assertEquals(10, solver.twoCitySchedCost(new int[][]{{1, 100}, {2, 100}, {100, 3}, {100, 4}})); }
    @Test public void testAdditionalPreferB() { assertEquals(10, solver.twoCitySchedCost(new int[][]{{100, 1}, {100, 2}, {3, 100}, {4, 100}})); }
    @Test public void testAdditionalMixed() { assertEquals(34, solver.twoCitySchedCost(new int[][]{{5, 6}, {7, 8}, {9, 10}, {11, 12}})); }
    @Test public void testAdditionalSix() { assertEquals(60, solver.twoCitySchedCost(new int[][]{{10, 20}, {10, 20}, {10, 20}, {20, 10}, {20, 10}, {20, 10}})); }
    @Test public void testAdditionalNegativeDiff() { assertEquals(2, solver.twoCitySchedCost(new int[][]{{1, 2}, {2, 1}})); }
    @Test public void testAdditionalLarger() { assertEquals(12, solver.twoCitySchedCost(new int[][]{{1, 10}, {2, 9}, {3, 8}, {8, 3}, {9, 2}, {10, 1}})); }
    @Test public void testAdditionalDPReuse() { assertEquals(110, solver.dynamicProgramming(new int[][]{{10, 20}, {30, 200}, {400, 50}, {30, 20}})); }
}
