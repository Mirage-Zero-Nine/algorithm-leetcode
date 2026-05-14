package solution.divideandconquer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FindMedianSortedArrays_4Test {
    private final FindMedianSortedArrays_4 solver = new FindMedianSortedArrays_4();
    private static final double DELTA = 1e-9;

    @Test public void bothOddTotal() {
        // merged: [1,2,3,4,5,6] -> median 3.5 (even total=6)
        assertEquals(3.5, solver.findMedianSortedArrays(new int[]{1, 2, 3}, new int[]{4, 5, 6}), DELTA);
    }

    @Test public void oddTotalLength() {
        // merged: [1,2,3] -> median 2
        assertEquals(2.0, solver.findMedianSortedArrays(new int[]{1, 3}, new int[]{2}), DELTA);
    }

    @Test public void evenTotalLength() {
        // merged: [1,2,3,4] -> median 2.5
        assertEquals(2.5, solver.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}), DELTA);
    }

    @Test public void firstArrayEmpty() {
        // merged: [1,2,3] -> median 2
        assertEquals(2.0, solver.findMedianSortedArrays(new int[]{}, new int[]{1, 2, 3}), DELTA);
    }

    @Test public void secondArrayEmpty() {
        // merged: [1,2,3,4] -> median 2.5
        assertEquals(2.5, solver.findMedianSortedArrays(new int[]{1, 2, 3, 4}, new int[]{}), DELTA);
    }

    @Test public void singleElementEach() {
        // merged: [1,2] -> median 1.5
        assertEquals(1.5, solver.findMedianSortedArrays(new int[]{1}, new int[]{2}), DELTA);
    }

    @Test public void disjointRanges() {
        // merged: [1,2,3,7,8,9] -> median 5.0
        assertEquals(5.0, solver.findMedianSortedArrays(new int[]{1, 2, 3}, new int[]{7, 8, 9}), DELTA);
    }

    @Test public void allSameValue() {
        assertEquals(2.0, solver.findMedianSortedArrays(new int[]{2, 2}, new int[]{2, 2}), DELTA);
    }

    @Test public void negativeNumbers() {
        // merged: [-5,-3,-1,0,2,4] -> median (-1+0)/2 = -0.5
        assertEquals(-0.5, solver.findMedianSortedArrays(new int[]{-5, -3, 0}, new int[]{-1, 2, 4}), DELTA);
    }

    @Test public void unevenLengths() {
        // merged: [1,2,3,4,5,6,7] -> median 4
        assertEquals(4.0, solver.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4, 5, 6, 7}), DELTA);
    }

    @Test public void singleAndMultiple() {
        // merged: [1,2,3] -> median 2
        assertEquals(2.0, solver.findMedianSortedArrays(new int[]{2}, new int[]{1, 3}), DELTA);
    }
}
