package solution.greedy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
