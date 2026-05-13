package solution.heap;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
