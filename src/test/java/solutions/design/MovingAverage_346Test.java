package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;
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

    @Test
    public void testWarmupUsesOnlyObservedValues() {
        MovingAverage_346 ma = new MovingAverage_346(5);
        assertEquals(8.0, ma.next(8), 0.0);
        assertEquals(4.0, ma.next(0), 0.0);
        assertEquals(6.0, ma.next(10), 0.0);
        assertEquals(4.5, ma.next(0), 0.0);
    }

    @Test
    public void testEvictionBeginsExactlyAtWindowBoundary() {
        MovingAverage_346 ma = new MovingAverage_346(3);
        assertEquals(2.0, ma.next(2), 0.0);
        assertEquals(3.0, ma.next(4), 0.0);
        assertEquals(4.0, ma.next(6), 0.0);
        assertEquals(40.0 / 3.0, ma.next(30), 1e-12);
    }

    @Test
    public void testMultipleCircularBufferWraparounds() {
        MovingAverage_346 ma = new MovingAverage_346(3);
        int[] values = {1, 2, 3, 4, 5, -6, 7, 8, -9, 10, 11, 12};
        double[] expected = {1, 1.5, 2, 3, 4, 1, 2, 3, 2, 3, 4, 11};
        for (int i = 0; i < values.length; i++) {
            assertEquals(expected[i], ma.next(values[i]), 1e-12);
        }
    }

    @Test
    public void testWindowSizeOneAlwaysReturnsLatestValue() {
        MovingAverage_346 ma = new MovingAverage_346(1);
        int[] values = {Integer.MIN_VALUE, -1, 0, 1, Integer.MAX_VALUE};
        for (int value : values) {
            assertEquals((double) value, ma.next(value), 0.0);
        }
    }

    @Test
    public void testMaximumSupportedWindowWarmsAndEvicts() {
        MovingAverage_346 ma = new MovingAverage_346(1000);
        assertEquals(-100_000.0, ma.next(-100_000), 0.0);
        double average = 0.0;
        for (int i = 1; i < 1000; i++) {
            average = ma.next(100_000);
        }
        assertEquals(99_800.0, average, 0.0);
        assertEquals(100_000.0, ma.next(100_000), 0.0);
    }

    @Test
    public void testAlternatingSigns() {
        MovingAverage_346 ma = new MovingAverage_346(4);
        assertEquals(100.0, ma.next(100), 0.0);
        assertEquals(0.0, ma.next(-100), 0.0);
        assertEquals(100.0 / 3.0, ma.next(100), 1e-12);
        assertEquals(0.0, ma.next(-100), 0.0);
        assertEquals(0.0, ma.next(100), 0.0);
        assertEquals(0.0, ma.next(-100), 0.0);
    }

    @Test
    public void testNonTerminatingAveragePrecision() {
        MovingAverage_346 ma = new MovingAverage_346(7);
        assertEquals(1.0, ma.next(1), 1e-12);
        assertEquals(0.5, ma.next(0), 1e-12);
        assertEquals(1.0 / 3.0, ma.next(0), 1e-12);
        assertEquals(0.25, ma.next(0), 1e-12);
        assertEquals(0.2, ma.next(0), 1e-12);
    }

    @Test
    public void testExtremeSignedValuesCanCancel() {
        MovingAverage_346 ma = new MovingAverage_346(2);
        assertEquals((double) Integer.MAX_VALUE, ma.next(Integer.MAX_VALUE), 0.0);
        assertEquals(-0.5, ma.next(Integer.MIN_VALUE), 0.0);
        assertEquals(-0.5, ma.next(Integer.MAX_VALUE), 0.0);
        assertEquals(-0.5, ma.next(Integer.MIN_VALUE), 0.0);
    }

    @Test
    public void testIntegerSumDoesNotOverflow() {
        MovingAverage_346 ma = new MovingAverage_346(3);
        assertEquals((double) Integer.MAX_VALUE, ma.next(Integer.MAX_VALUE), 0.0);
        assertEquals((double) Integer.MAX_VALUE, ma.next(Integer.MAX_VALUE), 0.0);
        assertEquals((double) Integer.MAX_VALUE, ma.next(Integer.MAX_VALUE), 0.0);
        assertEquals((2.0 * Integer.MAX_VALUE + Integer.MIN_VALUE) / 3.0,
                ma.next(Integer.MIN_VALUE), 1e-12);
        assertEquals(((double) Integer.MAX_VALUE + 2.0 * Integer.MIN_VALUE) / 3.0,
                ma.next(Integer.MIN_VALUE), 1e-12);
    }

    @Test
    public void testMaximumLeetCodeValuesKeepRunningSumSafe() {
        MovingAverage_346 ma = new MovingAverage_346(1000);
        for (int i = 0; i < 1000; i++) {
            assertEquals(100_000.0, ma.next(100_000), 0.0);
        }
        for (int i = 0; i < 1000; i++) {
            assertEquals(99_800.0 - 200.0 * i, ma.next(-100_000), 0.0);
        }
    }

    @Test
    public void testLongStreamAgainstIndependentDequeOracle() {
        int windowSize = 17;
        MovingAverage_346 ma = new MovingAverage_346(windowSize);
        Deque<Integer> window = new ArrayDeque<>();
        long sum = 0;
        Random random = new Random(346_2026L);
        for (int i = 0; i < 10_000; i++) {
            int value = random.nextInt(200_001) - 100_000;
            window.addLast(value);
            sum += value;
            if (window.size() > windowSize) {
                sum -= window.removeFirst();
            }
            assertEquals((double) sum / window.size(), ma.next(value), 1e-10,
                    "wrong average at stream position " + i);
        }
    }

    @Test
    public void testIndependentInstancesDoNotShareState() {
        MovingAverage_346 first = new MovingAverage_346(2);
        MovingAverage_346 second = new MovingAverage_346(2);
        assertEquals(10.0, first.next(10), 0.0);
        assertEquals(-10.0, second.next(-10), 0.0);
        assertEquals(15.0, first.next(20), 0.0);
        assertEquals(-15.0, second.next(-20), 0.0);
        assertEquals(20.0, first.next(20), 0.0);
        assertEquals(-20.0, second.next(-20), 0.0);
    }

    @Test
    public void testSameInstanceRemainsCorrectAfterManyCalls() {
        MovingAverage_346 ma = new MovingAverage_346(4);
        for (int i = 1; i <= 100; i++) {
            ma.next(i);
        }
        assertEquals(99.5, ma.next(101), 0.0);
        assertEquals(100.5, ma.next(102), 0.0);
        assertEquals(101.5, ma.next(103), 0.0);
        assertEquals(102.5, ma.next(104), 0.0);
    }

    @Test
    public void testZerosAndDuplicatesAfterEviction() {
        MovingAverage_346 ma = new MovingAverage_346(4);
        assertEquals(5.0, ma.next(5), 0.0);
        assertEquals(5.0, ma.next(5), 0.0);
        assertEquals(10.0 / 3.0, ma.next(0), 1e-12);
        assertEquals(2.5, ma.next(0), 0.0);
        assertEquals(1.25, ma.next(0), 0.0);
        assertEquals(0.0, ma.next(0), 0.0);
    }

    @Test
    public void testWindowSizeTwoFractionalEvictions() {
        MovingAverage_346 ma = new MovingAverage_346(2);
        assertEquals(1.0, ma.next(1), 0.0);
        assertEquals(1.5, ma.next(2), 0.0);
        assertEquals(2.5, ma.next(3), 0.0);
        assertEquals(3.5, ma.next(4), 0.0);
        assertEquals(4.5, ma.next(5), 0.0);
    }

    @Test
    public void testNegativeMaximumValues() {
        MovingAverage_346 ma = new MovingAverage_346(3);
        assertEquals(-100_000.0, ma.next(-100_000), 0.0);
        assertEquals(-100_000.0, ma.next(-100_000), 0.0);
        assertEquals(-100_000.0, ma.next(-100_000), 0.0);
        assertEquals(-100_000.0, ma.next(-100_000), 0.0);
    }

    @Test
    public void testMixedLegalValuesWithIndependentOracle() {
        int[] values = {-100_000, 99_999, 12_345, -54_321, 0, 100_000, -1, 1};
        MovingAverage_346 ma = new MovingAverage_346(5);
        long sum = 0;
        for (int i = 0; i < values.length; i++) {
            sum += values[i];
            if (i >= 5) {
                sum -= values[i - 5];
            }
            assertEquals((double) sum / Math.min(i + 1, 5), ma.next(values[i]), 1e-12);
        }
    }
}
