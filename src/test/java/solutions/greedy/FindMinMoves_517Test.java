package solutions.greedy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindMinMoves_517Test {
    private final FindMinMoves_517 solver = new FindMinMoves_517();

    @Test public void testBasic() {
        assertEquals(3, solver.findMinMoves(new int[]{1, 0, 5}));
    }

    @Test public void testEqualDresses() {
        // [2,2,2] total=6, avg=2
        assertEquals(0, solver.findMinMoves(new int[]{2, 2, 2}));
    }

    @Test public void testImpossible() {
        // Total 2, len 3, 2%3 != 0 -> -1
        assertEquals(-1, solver.findMinMoves(new int[]{0, 2, 0}));
    }

    @Test public void testNull() {
        assertEquals(-1, solver.findMinMoves(null));
    }

    @Test public void testComplex() {
        assertEquals(8, solver.findMinMoves(new int[]{0, 0, 11, 5}));
    }

    @Test public void testEmpty() {
        assertEquals(-1, solver.findMinMoves(new int[]{}));
    }

    @Test public void testSingleMachine() {
        assertEquals(0, solver.findMinMoves(new int[]{7}));
    }

    @Test public void testTwoMachines() {
        assertEquals(1, solver.findMinMoves(new int[]{0, 2}));
    }

    @Test public void testLargeExcess() {
        // [0, 0, 0, 12] avg=3, max excess at index 3 is 9
        assertEquals(9, solver.findMinMoves(new int[]{0, 0, 0, 12}));
    }

    @Test public void testGiantCase() {
        int n = 10000;
        int[] machines = new int[n];
        machines[0] = n * 5;
        // avg = 5, machines[0] excess = n*5 - 5 = 49995
        assertEquals(49995, solver.findMinMoves(machines));
    }
}
