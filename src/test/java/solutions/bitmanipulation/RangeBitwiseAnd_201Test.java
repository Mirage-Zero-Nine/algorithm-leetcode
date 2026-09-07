package solutions.bitmanipulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RangeBitwiseAnd_201Test {
    private final RangeBitwiseAnd_201 solver = new RangeBitwiseAnd_201();

    @Test public void testBasic() {
        assertEquals(4, solver.rangeBitwiseAnd(5, 7));
    }

    @Test public void testZero() {
        assertEquals(0, solver.rangeBitwiseAnd(0, 1));
    }

    @Test public void testEqual() {
        assertEquals(15, solver.rangeBitwiseAnd(15, 15));
    }

    @Test public void testLargeRange() {
        assertEquals(0, solver.rangeBitwiseAnd(1, 2147483647));
    }

    @Test public void testSmallRange() {
        assertEquals(12, solver.rangeBitwiseAnd(12, 13));
    }

    @Test public void testBothZero() {
        assertEquals(0, solver.rangeBitwiseAnd(0, 0));
    }

    @Test public void testPowerOfTwo() {
        assertEquals(4, solver.rangeBitwiseAnd(4, 7));
    }

    @Test public void testConsecutiveLarge() {
        assertEquals(2147483646, solver.rangeBitwiseAnd(2147483646, 2147483647));
    }

    @Test public void testWideRangeZero() {
        assertEquals(0, solver.rangeBitwiseAnd(5, 12));
    }

    @Test public void testSameHighBits() {
        // 26 = 11010, 27 = 11011, 28 = 11100 -> AND = 11000 = 24
        assertEquals(24, solver.rangeBitwiseAnd(26, 28));
    }

    @Test public void testRangeOfThree() {
        // 6=110, 7=111 -> AND = 110 = 6
        assertEquals(6, solver.rangeBitwiseAnd(6, 7));
    }

    @Test public void testExhaustiveSmallDomain() {
        for (int left = 0; left <= 255; left++) {
            for (int right = left; right <= 255; right++) {
                assertEquals(rangeAndByEnumeration(left, right), solver.rangeBitwiseAnd(left, right),
                        "Unexpected result for range [" + left + ", " + right + "]");
            }
        }
    }

    private int rangeAndByEnumeration(int left, int right) {
        int result = left;
        for (int value = left + 1; value <= right; value++) {
            result &= value;
        }
        return result;
    }

    @Test public void testNarrowRangesNearIntegerMaximum() {
        for (int width = 0; width <= 1024; width++) {
            int left = Integer.MAX_VALUE - width;
            int expected = Integer.MAX_VALUE;
            for (long value = left; value <= Integer.MAX_VALUE; value++) expected &= (int) value;
            assertEquals(expected, solver.rangeBitwiseAnd(left, Integer.MAX_VALUE));
        }
    }

    @Test public void testEveryPowerOfTwoBoundary() {
        for (int bit = 1; bit < 31; bit++) {
            int power = 1 << bit;
            assertEquals(0, solver.rangeBitwiseAnd(power - 1, power));
            assertEquals(power, solver.rangeBitwiseAnd(power, power + power - 1));
        }
    }

    @Test public void testSeededNarrowHighRanges() {
        java.util.Random random = new java.util.Random(2010906L);
        for (int sample = 0; sample < 1000; sample++) {
            int left = random.nextInt(Integer.MAX_VALUE - 128);
            int right = left + random.nextInt(128);
            int expected = left;
            for (int value = left + 1; value <= right; value++) expected &= value;
            assertEquals(expected, solver.rangeBitwiseAnd(left, right));
        }
    }
}
