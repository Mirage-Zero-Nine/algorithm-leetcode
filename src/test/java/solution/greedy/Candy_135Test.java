package solution.greedy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Candy_135Test {
    private final Candy_135 solver = new Candy_135();

    @Test public void testBasicTwoPass() {
        assertEquals(5, solver.twoPass(new int[]{1, 0, 2}));
    }

    @Test public void testAllSameTwoPass() {
        assertEquals(4, solver.twoPass(new int[]{1, 2, 2}));
    }

    @Test public void testIncreasingTwoPass() {
        assertEquals(15, solver.twoPass(new int[]{1, 2, 3, 4, 5}));
    }

    @Test public void testSingleChildTwoPass() {
        assertEquals(1, solver.twoPass(new int[]{5}));
    }

    @Test public void testCandyOrig() {
        assertEquals(5, solver.candy(new int[]{1, 0, 2}));
    }

    @Test public void testCandyComplex() {
        assertEquals(35, solver.twoPass(new int[]{1, 1, 2, 3, 7, 6, 5, 4, 3, 2, 1}));
    }
}
