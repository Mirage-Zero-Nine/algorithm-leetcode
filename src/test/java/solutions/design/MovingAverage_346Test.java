package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MovingAverage_346Test {

    @Test
    public void testHappyCases() {
        MovingAverage_346 ma = new MovingAverage_346(3);
        assertEquals(1.0, ma.next(1), 0.0001);
        assertEquals(5.5, ma.next(10), 0.0001);
        assertEquals(4.666, ma.next(3), 0.001);
        assertEquals(6.0, ma.next(5), 0.0001);
    }

    @Test
    public void testEdgeCases() {
        MovingAverage_346 ma = new MovingAverage_346(1);
        assertEquals(1.0, ma.next(1), 0.0001);
        assertEquals(2.0, ma.next(2), 0.0001);
    }

    @Test
    public void testLargeCase() {
        MovingAverage_346 ma = new MovingAverage_346(5);
        for (int i = 1; i <= 5; i++) ma.next(i);
        assertEquals(4.0, ma.next(6), 0.0001);
    }

    @Test
    public void testWindowSize2() {
        MovingAverage_346 ma = new MovingAverage_346(2);
        assertEquals(3.0, ma.next(3), 0.0001);
        assertEquals(4.0, ma.next(5), 0.0001);
        assertEquals(6.0, ma.next(7), 0.0001);
    }

    @Test
    public void testAllZeros() {
        MovingAverage_346 ma = new MovingAverage_346(3);
        assertEquals(0.0, ma.next(0), 0.0001);
        assertEquals(0.0, ma.next(0), 0.0001);
        assertEquals(0.0, ma.next(0), 0.0001);
    }

    @Test
    public void testNegativeValues() {
        MovingAverage_346 ma = new MovingAverage_346(2);
        assertEquals(-5.0, ma.next(-5), 0.0001);
        assertEquals(-2.5, ma.next(0), 0.0001);
        assertEquals(5.0, ma.next(10), 0.0001);
    }

    @Test
    public void testSameValues() {
        MovingAverage_346 ma = new MovingAverage_346(3);
        assertEquals(4.0, ma.next(4), 0.0001);
        assertEquals(4.0, ma.next(4), 0.0001);
        assertEquals(4.0, ma.next(4), 0.0001);
        assertEquals(4.0, ma.next(4), 0.0001);
    }

    @Test
    public void testWindowNotFullYet() {
        MovingAverage_346 ma = new MovingAverage_346(4);
        assertEquals(10.0, ma.next(10), 0.0001);
        assertEquals(15.0, ma.next(20), 0.0001);
        assertEquals(20.0, ma.next(30), 0.0001);
    }

    @Test
    public void testSlidingOutOldValues() {
        MovingAverage_346 ma = new MovingAverage_346(3);
        ma.next(1); ma.next(2); ma.next(3);
        // window: [2,3,100]
        assertEquals(35.0, ma.next(100), 0.0001);
        // window: [3,100,0]
        assertEquals(34.333, ma.next(0), 0.001);
    }

    @Test
    public void testLargeValues() {
        MovingAverage_346 ma = new MovingAverage_346(2);
        assertEquals(1000000.0, ma.next(1000000), 0.0001);
        assertEquals(1000000.0, ma.next(1000000), 0.0001);
        assertEquals(500000.5, ma.next(1), 0.0001);
    }

    @Test
    public void testGiantCase() {
        MovingAverage_346 ma = new MovingAverage_346(100);
        double last = 0;
        for (int i = 1; i <= 200; i++) {
            last = ma.next(i);
        }
        // window contains 101..200, avg = (101+200)/2 = 150.5
        assertEquals(150.5, last, 0.0001);
    }
}
