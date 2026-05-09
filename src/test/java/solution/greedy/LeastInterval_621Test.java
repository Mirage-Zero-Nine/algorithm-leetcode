package solution.greedy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeastInterval_621Test {
    private final LeastInterval_621 solver = new LeastInterval_621();

    @Test public void testBasic() {
        // AABBB, n=2: A__A__A__B_B_B - but actually 8 for AAABBB with n=2
        assertEquals(8, solver.leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2));
    }

    @Test public void testZeroN() {
        assertEquals(6, solver.leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 0));
    }

    @Test public void testSingle() {
        assertEquals(1, solver.leastInterval(new char[]{'A'}, 2));
    }

    @Test public void testManyTypes() {
        assertEquals(16, solver.leastInterval(new char[]{'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'}, 2));
    }

    @Test public void testLargeN() {
        assertEquals(104, solver.leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 50));
    }
}
