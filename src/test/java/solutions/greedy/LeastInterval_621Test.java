package solutions.greedy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeastInterval_621Test {
    private final LeastInterval_621 solver = new LeastInterval_621();

    @Test public void testBasic() {
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

    @Test public void testAllSameTask() {
        // AAA with n=2: A__A__A = 7
        assertEquals(7, solver.leastInterval(new char[]{'A', 'A', 'A'}, 2));
    }

    @Test public void testNoIdleNeeded() {
        // ABCABC with n=2: no idle needed since 3 different tasks fill the gap
        assertEquals(6, solver.leastInterval(new char[]{'A', 'A', 'B', 'B', 'C', 'C'}, 2));
    }

    @Test public void testTwoTasks() {
        assertEquals(2, solver.leastInterval(new char[]{'A', 'B'}, 1));
    }

    @Test public void testNEqualsOne() {
        // AAABB with n=1: A_A_A_BB -> ABABABAB? Actually AB_AB_A = ABA_BA = 5? Let's check
        assertEquals(5, solver.leastInterval(new char[]{'A', 'A', 'A', 'B', 'B'}, 1));
    }

    @Test public void testGiantCase() {
        // 26 different tasks, each appearing 100 times, n=25
        // max=100, maxCount=26, slots=(100-1)*(25-25)=0, no idle needed
        char[] tasks = new char[2600];
        int idx = 0;
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 100; j++) {
                tasks[idx++] = (char) ('A' + i);
            }
        }
        assertEquals(2600, solver.leastInterval(tasks, 25));
    }

    @Test public void testSingleTaskLargeN() {
        // A with n=100: just 1
        assertEquals(1, solver.leastInterval(new char[]{'A'}, 100));
    }
}
