package solutions.greedy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinDominoRotations_1007Test {
    private final MinDominoRotations_1007 solver = new MinDominoRotations_1007();

    @Test public void testBasic() {
        int[] A = {2, 1, 2, 4, 2, 2};
        int[] B = {5, 2, 6, 2, 3, 2};
        assertEquals(2, solver.minDominoRotations(A, B));
    }

    @Test public void testImpossible() {
        int[] A = {3, 5, 1, 2, 3};
        int[] B = {3, 6, 3, 3, 4};
        assertEquals(-1, solver.minDominoRotations(A, B));
    }

    @Test public void testAllSame() {
        int[] A = {1, 1, 1};
        int[] B = {1, 1, 1};
        assertEquals(0, solver.minDominoRotations(A, B));
    }

    @Test public void testOneElement() {
        int[] A = {2};
        int[] B = {3};
        assertEquals(0, solver.minDominoRotations(A, B));
    }

    @Test public void testRotateAllA() {
        // Swap entire B to A -> all 2's in top
        int[] A = {3, 3, 3};
        int[] B = {2, 2, 2};
        assertEquals(0, solver.minDominoRotations(A, B));
    }

    @Test public void testRotateToB() {
        int[] A = {1, 2, 1, 1, 1};
        int[] B = {2, 1, 2, 2, 2};
        assertEquals(1, solver.minDominoRotations(A, B));
    }

    @Test public void testTwoElements() {
        int[] A = {1, 2};
        int[] B = {2, 1};
        assertEquals(1, solver.minDominoRotations(A, B));
    }

    @Test public void testAlreadyUniformA() {
        int[] A = {5, 5, 5, 5};
        int[] B = {1, 2, 3, 4};
        assertEquals(0, solver.minDominoRotations(A, B));
    }

    @Test public void testNoSolution() {
        int[] A = {1, 2, 3, 4, 5, 6};
        int[] B = {6, 5, 4, 3, 2, 1};
        assertEquals(-1, solver.minDominoRotations(A, B));
    }

    @Test public void testGiant() {
        int[] A = new int[1000];
        int[] B = new int[1000];
        for (int i = 0; i < 1000; i++) {
            A[i] = (i % 2 == 0) ? 3 : 1;
            B[i] = 3;
        }
        // B already all 3s, so 0 rotations needed to make B uniform
        assertEquals(0, solver.minDominoRotations(A, B));
    }
    @Test public void testAdditionalTop() { assertEquals(0, solver.minDominoRotations(new int[]{1, 2, 3}, new int[]{2, 2, 2})); }
    @Test public void testAdditionalBottom() { assertEquals(0, solver.minDominoRotations(new int[]{2, 2, 2}, new int[]{1, 2, 3})); }
    @Test public void testAdditionalAlreadyBottom() { assertEquals(0, solver.minDominoRotations(new int[]{2, 2, 2}, new int[]{1, 1, 1})); }
    @Test public void testAdditionalTwo() { assertEquals(1, solver.minDominoRotations(new int[]{1, 2}, new int[]{2, 3})); }
    @Test public void testAdditionalNoMatch() { assertEquals(-1, solver.minDominoRotations(new int[]{1, 2, 3}, new int[]{4, 5, 6})); }
    @Test public void testAdditionalFour() { assertEquals(-1, solver.minDominoRotations(new int[]{1, 2, 3, 4}, new int[]{2, 1, 4, 2})); }
    @Test public void testAdditionalValueSix() { assertEquals(1, solver.minDominoRotations(new int[]{6, 1, 6}, new int[]{2, 6, 3})); }
    @Test public void testAdditionalMixed() { assertEquals(1, solver.minDominoRotations(new int[]{3, 5, 3, 3}, new int[]{5, 3, 4, 3})); }
    @Test public void testAdditionalUniformB() { assertEquals(0, solver.minDominoRotations(new int[]{2, 3, 4}, new int[]{5, 5, 5})); }
    @Test public void testAdditionalSingleMatch() { assertEquals(0, solver.minDominoRotations(new int[]{6}, new int[]{6})); }
}
