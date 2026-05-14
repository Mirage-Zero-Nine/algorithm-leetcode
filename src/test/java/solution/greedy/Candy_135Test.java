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

    @Test public void testCandySingleChild() {
        assertEquals(1, solver.candy(new int[]{5}));
    }

    @Test public void testCandyIncreasing() {
        assertEquals(15, solver.candy(new int[]{1, 2, 3, 4, 5}));
    }

    @Test public void testCandyDecreasing() {
        assertEquals(15, solver.candy(new int[]{5, 4, 3, 2, 1}));
        assertEquals(15, solver.twoPass(new int[]{5, 4, 3, 2, 1}));
    }

    @Test public void testCandyAllEqual() {
        assertEquals(5, solver.candy(new int[]{3, 3, 3, 3, 3}));
        assertEquals(5, solver.twoPass(new int[]{3, 3, 3, 3, 3}));
    }

    @Test public void testCandyVShape() {
        // [5, 3, 1, 3, 5] -> candies: [3, 2, 1, 2, 3] = 11
        assertEquals(11, solver.twoPass(new int[]{5, 3, 1, 3, 5}));
        assertEquals(11, solver.candy(new int[]{5, 3, 1, 3, 5}));
    }

    @Test public void testGiantCase() {
        int[] ratings = new int[10000];
        for (int i = 0; i < 10000; i++) {
            ratings[i] = i;
        }
        // Strictly increasing: 1+2+3+...+10000 = 50005000
        assertEquals(50005000, solver.twoPass(ratings));
        assertEquals(50005000, solver.candy(ratings));
    }
}
