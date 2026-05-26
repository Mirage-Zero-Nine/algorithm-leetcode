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
        int expected = 0;
        for (int i = 0; i < n; i++) {
            costs[i][0] = i + 1;
            costs[i][1] = n - i;
        }
        int result = solver.twoCitySchedCost(costs);
        assertTrue(result > 0);
    }
}
