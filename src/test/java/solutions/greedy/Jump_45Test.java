package solutions.greedy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Jump_45Test {
    private final Jump_45 solver = new Jump_45();

    @Test public void testBasic() {
        assertEquals(2, solver.jump(new int[]{2, 3, 1, 1, 4}));
    }

    @Test public void testSingle() {
        assertEquals(0, solver.jump(new int[]{0}));
    }

    @Test public void testTwoElements() {
        assertEquals(1, solver.jump(new int[]{1, 1}));
    }

    @Test public void testLongest() {
        assertEquals(1, solver.jump(new int[]{10, 1, 1, 1, 1}));
    }

    @Test public void testDecreasing() {
        assertEquals(2, solver.jump(new int[]{2, 3, 1, 1, 1}));
    }

    @Test public void testAllOnes() {
        assertEquals(3, solver.jump(new int[]{1, 1, 1, 1}));
    }

    @Test public void testLargeFirstJump() {
        assertEquals(2, solver.jump(new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 1, 0}));
    }

    @Test public void testGreedyChoice() {
        assertEquals(2, solver.jump(new int[]{4, 1, 1, 3, 1, 1, 1}));
    }

    @Test public void testThreeElements() {
        assertEquals(2, solver.jump(new int[]{1, 3, 2}));
    }

    @Test public void testGiantArray() {
        int[] arr = new int[10000];
        java.util.Arrays.fill(arr, 1);
        assertEquals(9999, solver.jump(arr));
    }

    @Test public void testGiantArrayLargeJumps() {
        int[] arr = new int[10000];
        java.util.Arrays.fill(arr, 10000);
        assertEquals(1, solver.jump(arr));
    }
}
