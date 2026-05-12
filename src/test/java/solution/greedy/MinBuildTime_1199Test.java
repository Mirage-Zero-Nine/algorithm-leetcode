package solution.greedy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinBuildTime_1199Test {
    private final MinBuildTime_1199 solver = new MinBuildTime_1199();

    @Test public void testBasic() {
        // blocks=[1], split=1 => one worker builds 1 block = 1 time
        assertEquals(1, solver.minBuildTime(new int[]{1}, 1));
    }

    @Test public void testTwoBlocks() {
        // blocks=[1,2], split=5: split takes 5, then 2 workers each build one
        // total = 5 + max(1, 2) = 7
        assertEquals(7, solver.minBuildTime(new int[]{1, 2}, 5));
    }

    @Test public void testMultipleBlocks() {
        // blocks=[1,2,3], split=1
        assertEquals(4, solver.minBuildTime(new int[]{1, 2, 3}, 1));
    }

    @Test public void testSameValues() {
        assertEquals(17, solver.minBuildTime(new int[]{1, 2, 4, 7, 8, 11, 15}, 2));
    }

    @Test public void testSingleBlockLargeSplit() {
        assertEquals(100, solver.minBuildTime(new int[]{100}, 1000));
    }
}
