package solution.bitmanipulation;

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
}
