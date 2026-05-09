package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MovingAverage346Test {

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
}
