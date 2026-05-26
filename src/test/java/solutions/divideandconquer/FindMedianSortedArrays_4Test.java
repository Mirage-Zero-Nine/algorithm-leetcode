package solutions.divideandconquer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Random;

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

    @Test public void oneEmptyOtherSizeOne() {
        assertEquals(7.0, solver.findMedianSortedArrays(new int[]{}, new int[]{7}), DELTA);
        assertEquals(42.0, solver.findMedianSortedArrays(new int[]{42}, new int[]{}), DELTA);
    }

    @Test public void bothSingleSameValue() {
        assertEquals(5.0, solver.findMedianSortedArrays(new int[]{5}, new int[]{5}), DELTA);
    }

    @Test public void equalLengthsEvenTotal() {
        // [1,3] and [2,4] -> merged [1,2,3,4] -> (2+3)/2 = 2.5
        assertEquals(2.5, solver.findMedianSortedArrays(new int[]{1, 3}, new int[]{2, 4}), DELTA);
        // [1,2,3,4] and [5,6,7,8] -> merged 8 elements -> (4+5)/2 = 4.5
        assertEquals(4.5, solver.findMedianSortedArrays(new int[]{1, 2, 3, 4}, new int[]{5, 6, 7, 8}), DELTA);
    }

    @Test public void vastlyDifferentSizes() {
        // 1 element vs 1000 elements: merged has 1001 elements, median at index 500
        int[] large = new int[1000];
        for (int i = 0; i < 1000; i++) large[i] = i + 1; // 1..1000
        // insert 0 -> merged [0,1,2,...,1000], median at index 500 = 500
        assertEquals(500.0, solver.findMedianSortedArrays(new int[]{0}, large), DELTA);
    }

    @Test public void disjointRangesLarge() {
        // [1..100] and [200..300] -> 201 elements total, median at index 100 = value 100 (0-indexed from merged)
        int[] a = new int[100];
        int[] b = new int[101];
        for (int i = 0; i < 100; i++) a[i] = i + 1;    // 1..100
        for (int i = 0; i < 101; i++) b[i] = i + 200;  // 200..300
        // merged: [1..100, 200..300], 201 elements, median at index 100 = 200
        assertEquals(200.0, solver.findMedianSortedArrays(a, b), DELTA);
    }

    @Test public void overlappingRanges() {
        // [1,3,5,7,9] and [2,4,6,8,10] -> merged [1..10], 10 elements -> (5+6)/2 = 5.5
        assertEquals(5.5, solver.findMedianSortedArrays(new int[]{1, 3, 5, 7, 9}, new int[]{2, 4, 6, 8, 10}), DELTA);
    }

    @Test public void allNegativeNumbers() {
        // [-10,-8,-6] and [-5,-3,-1] -> merged [-10,-8,-6,-5,-3,-1] -> (-6+-5)/2 = -5.5
        assertEquals(-5.5, solver.findMedianSortedArrays(new int[]{-10, -8, -6}, new int[]{-5, -3, -1}), DELTA);
    }

    @Test public void largeRandomCrossChecked() {
        Random rng = new Random(42L);
        int[] a = rng.ints(500, -10000, 10000).sorted().toArray();
        int[] b = rng.ints(501, -10000, 10000).sorted().toArray();
        // Reference: merge and find middle
        int[] merged = new int[a.length + b.length];
        System.arraycopy(a, 0, merged, 0, a.length);
        System.arraycopy(b, 0, merged, a.length, b.length);
        Arrays.sort(merged);
        int n = merged.length;
        double expected = (n % 2 == 1) ? merged[n / 2] : (merged[n / 2 - 1] + merged[n / 2]) / 2.0;
        assertEquals(expected, solver.findMedianSortedArrays(a, b), DELTA);
    }

    @Test public void propertyMedianBetweenMinAndMax() {
        Random rng = new Random(42L);
        int[] a = rng.ints(300, -5000, 5000).sorted().toArray();
        int[] b = rng.ints(400, -5000, 5000).sorted().toArray();
        double median = solver.findMedianSortedArrays(a, b);
        int min = Math.min(a[0], b[0]);
        int max = Math.max(a[a.length - 1], b[b.length - 1]);
        assertTrue(median >= min && median <= max,
                "Median " + median + " should be between " + min + " and " + max);
    }
}
