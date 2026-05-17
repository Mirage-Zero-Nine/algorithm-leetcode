package solution.greedy;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test public void testTwoChildrenEqualRatings() {
        // Both get 1 candy each
        assertEquals(2, solver.twoPass(new int[]{3, 3}));
        assertEquals(2, solver.candy(new int[]{3, 3}));
    }

    @Test public void testTwoChildrenAscending() {
        // [1,2] -> candies [1,2] = 3
        assertEquals(3, solver.twoPass(new int[]{1, 2}));
        assertEquals(3, solver.candy(new int[]{1, 2}));
    }

    @Test public void testTwoChildrenDescending() {
        // [2,1] -> candies [2,1] = 3
        assertEquals(3, solver.twoPass(new int[]{2, 1}));
        assertEquals(3, solver.candy(new int[]{2, 1}));
    }

    @Test public void testSinglePeakInMiddle() {
        // [1,3,2,1,5] -> candies [1,2,1,1,2] via twoPass? Let's verify:
        // Left pass:  [1,2,1,1,2]
        // Right pass: [1,2,2,1,2] -> max: [1,2,2,1,2] = 8? No.
        // Left pass:  [1,2,1,1,2]
        // Right pass (r->l): i=3: r[2]>r[3]? 2>1 yes -> tmp[2]=max(tmp[3]+1,1)=2; i=2: r[1]>r[2]? 3>2 yes -> tmp[1]=max(tmp[2]+1,2)=3
        // Final: [1,3,2,1,2] = 9
        assertEquals(9, solver.twoPass(new int[]{1, 3, 2, 1, 5}));
        assertEquals(9, solver.candy(new int[]{1, 3, 2, 1, 5}));
    }

    @Test public void testSingleValleyInMiddle() {
        // [5,3,1,3,5] already tested in testCandyVShape -> 11
        // Different valley: [3,1,3] -> candies [2,1,2] = 5
        assertEquals(5, solver.twoPass(new int[]{3, 1, 3}));
        assertEquals(5, solver.candy(new int[]{3, 1, 3}));
    }

    @Test public void testPlateauInMiddle() {
        // [1,2,3,3,3,2,1] -> Left pass: [1,2,3,1,1,1,1], Right pass: [1,2,3,1,3,2,1] = 13
        assertEquals(13, solver.twoPass(new int[]{1, 2, 3, 3, 3, 2, 1}));
        assertEquals(13, solver.candy(new int[]{1, 2, 3, 3, 3, 2, 1}));
    }

    @Test public void testStrictlyDescendingSix() {
        // [6,5,4,3,2,1] -> 1+2+3+4+5+6 = 21
        assertEquals(21, solver.twoPass(new int[]{6, 5, 4, 3, 2, 1}));
        assertEquals(21, solver.candy(new int[]{6, 5, 4, 3, 2, 1}));
    }

    @Test public void testLargeRandom1000() {
        Random rng = new Random(42L);
        int n = 1000;
        int[] ratings = new int[n];
        for (int i = 0; i < n; i++) {
            ratings[i] = rng.nextInt(100);
        }
        int resultTwoPass = solver.twoPass(ratings);
        int resultCandy = solver.candy(ratings);
        // Both methods should agree
        assertEquals(resultTwoPass, resultCandy);
        // Property: result >= n (each child gets at least 1)
        assertTrue(resultTwoPass >= n);
    }

    @Test public void testBothMethodsAgreeOnWave() {
        // Wave pattern: up-down-up-down
        int[] ratings = {1, 5, 2, 6, 3, 7, 4, 8};
        assertEquals(solver.twoPass(ratings), solver.candy(ratings));
        // Property: result >= n
        assertTrue(solver.twoPass(ratings) >= ratings.length);
    }

    @Test public void testAllSameLarger() {
        // n children all same rating -> each gets 1 -> total = n
        int n = 100;
        int[] ratings = new int[n];
        java.util.Arrays.fill(ratings, 7);
        assertEquals(n, solver.twoPass(ratings));
        assertEquals(n, solver.candy(ratings));
    }
}
