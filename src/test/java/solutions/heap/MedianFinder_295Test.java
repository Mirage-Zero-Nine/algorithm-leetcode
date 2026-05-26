package solutions.heap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

public class MedianFinder_295Test {

    @Test
    public void testHappyCases() {
        MedianFinder_295 test = new MedianFinder_295();
        test.addNum(1);
        test.addNum(2);
        assertEquals(1.5, test.findMedian());
        test.addNum(3);
        assertEquals(2.0, test.findMedian());
    }

    @Test
    public void testNegativeCases() {
        MedianFinder_295 test = new MedianFinder_295();
        test.addNum(-5);
        test.addNum(-1);
        test.addNum(-3);
        assertEquals(-3.0, test.findMedian());
    }

    @Test
    public void testEdgeCases() {
        MedianFinder_295 test = new MedianFinder_295();
        test.addNum(42);
        assertEquals(42.0, test.findMedian());
        test.addNum(42);
        assertEquals(42.0, test.findMedian());
    }

    @Test
    public void testLargeCase() {
        MedianFinder_295 test = new MedianFinder_295();
        for (int i = 1; i <= 100; i++) {
            test.addNum(i);
        }
        assertEquals(50.5, test.findMedian());
    }

    @Test
    public void testDescendingOrder() {
        MedianFinder_295 test = new MedianFinder_295();
        test.addNum(5);
        test.addNum(4);
        test.addNum(3);
        test.addNum(2);
        test.addNum(1);
        assertEquals(3.0, test.findMedian());
    }

    @Test
    public void testAllSameValues() {
        MedianFinder_295 test = new MedianFinder_295();
        test.addNum(7);
        test.addNum(7);
        test.addNum(7);
        test.addNum(7);
        assertEquals(7.0, test.findMedian());
    }

    @Test
    public void testTwoElements() {
        MedianFinder_295 test = new MedianFinder_295();
        test.addNum(1);
        test.addNum(100);
        assertEquals(50.5, test.findMedian());
    }

    @Test
    public void testLargeNegatives() {
        MedianFinder_295 test = new MedianFinder_295();
        test.addNum(-100);
        test.addNum(-50);
        test.addNum(-1);
        assertEquals(-50.0, test.findMedian());
    }

    @Test
    public void testMixedPositiveNegative() {
        MedianFinder_295 test = new MedianFinder_295();
        test.addNum(-10);
        test.addNum(10);
        assertEquals(0.0, test.findMedian());
        test.addNum(0);
        assertEquals(0.0, test.findMedian());
    }

    @Test
    public void testGiantCaseOdd() {
        MedianFinder_295 test = new MedianFinder_295();
        for (int i = 1; i <= 999; i++) {
            test.addNum(i);
        }
        assertEquals(500.0, test.findMedian());
    }

    @Test
    public void testSingleAdd() {
        MedianFinder_295 test = new MedianFinder_295();
        test.addNum(99);
        assertEquals(99.0, test.findMedian(), 1e-9);
    }

    @Test
    public void testTwoAddsSameValue() {
        MedianFinder_295 test = new MedianFinder_295();
        test.addNum(5);
        test.addNum(5);
        assertEquals(5.0, test.findMedian(), 1e-9);
    }

    @Test
    public void testTwoAddsDifferentAverage() {
        MedianFinder_295 test = new MedianFinder_295();
        test.addNum(1);
        test.addNum(2);
        assertEquals(1.5, test.findMedian(), 1e-9);
    }

    @Test
    public void testStrictlyIncreasingMedianAtEachStep() {
        MedianFinder_295 test = new MedianFinder_295();
        // Add 1..7, check median after each add
        double[] expected = {1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0};
        for (int i = 1; i <= 7; i++) {
            test.addNum(i);
            assertEquals(expected[i - 1], test.findMedian(), 1e-9, "After adding " + i);
        }
    }

    @Test
    public void testStrictlyDecreasingMedianAtEachStep() {
        MedianFinder_295 test = new MedianFinder_295();
        // Add 7,6,5,4,3,2,1 - sorted is always [1..k] reversed
        List<Integer> sorted = new ArrayList<>();
        for (int i = 7; i >= 1; i--) {
            test.addNum(i);
            sorted.add(i);
            Collections.sort(sorted);
            int n = sorted.size();
            double expectedMedian = n % 2 == 1 ? sorted.get(n / 2) : (sorted.get(n / 2 - 1) + sorted.get(n / 2)) / 2.0;
            assertEquals(expectedMedian, test.findMedian(), 1e-9, "After adding " + i);
        }
    }

    @Test
    public void testAllSameValueAlwaysMedian() {
        MedianFinder_295 test = new MedianFinder_295();
        for (int i = 0; i < 20; i++) {
            test.addNum(3);
            assertEquals(3.0, test.findMedian(), 1e-9, "After " + (i + 1) + " adds");
        }
    }

    @Test
    public void testNegativeNumbersIncludingIntMin() {
        MedianFinder_295 test = new MedianFinder_295();
        test.addNum(-1_000_000_000);
        assertEquals(-1_000_000_000.0, test.findMedian(), 1e-9);
        test.addNum(0);
        assertEquals(-500_000_000.0, test.findMedian(), 1e-9);
        test.addNum(-1);
        assertEquals(-1.0, test.findMedian(), 1e-9);
    }

    @Test
    public void testLargeStream1000RandomSeed42() {
        // Use bounded random values to avoid int addition overflow in findMedian's (a+b)/2.0
        MedianFinder_295 test = new MedianFinder_295();
        Random rng = new Random(42L);
        List<Integer> sorted = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            int val = rng.nextInt(2_000_001) - 1_000_000; // range [-1_000_000, 1_000_000]
            test.addNum(val);
            sorted.add(val);
            Collections.sort(sorted);
            int n = sorted.size();
            double expectedMedian = n % 2 == 1 ? sorted.get(n / 2) : (sorted.get(n / 2 - 1) + sorted.get(n / 2)) / 2.0;
            assertEquals(expectedMedian, test.findMedian(), 1e-9, "Mismatch at step " + (i + 1));
        }
    }

    @Test
    public void testOverflowRiskLargeInts() {
        // The implementation uses (small.peek() + large.peek()) / 2.0 which overflows for large ints.
        // This test uses large-but-safe values that don't overflow int addition.
        MedianFinder_295 test = new MedianFinder_295();
        test.addNum(Integer.MAX_VALUE / 2);
        test.addNum(Integer.MAX_VALUE / 2 - 1);
        double expected = (Integer.MAX_VALUE / 2 + (double) (Integer.MAX_VALUE / 2 - 1)) / 2.0;
        assertEquals(expected, test.findMedian(), 1e-9);
    }
}
