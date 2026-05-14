package solution.bitmanipulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HammingWeight_191Test {
    private final HammingWeight_191 solver = new HammingWeight_191();

    @Test public void testBasic() {
        assertEquals(3, solver.hammingWeight(11));
    }

    @Test public void testZero() {
        assertEquals(0, solver.hammingWeight(0));
    }

    @Test public void testOne() {
        assertEquals(1, solver.hammingWeight(1));
    }

    @Test public void testPowerOfTwo() {
        assertEquals(1, solver.hammingWeight(128));
    }

    @Test public void testAllOnes() {
        assertEquals(5, solver.hammingWeight(0b11111));
    }

    @Test public void testMaxInt() {
        assertEquals(31, solver.hammingWeight(Integer.MAX_VALUE));
    }

    @Test public void testNegativeOne() {
        // -1 in two's complement is all 32 bits set
        assertEquals(32, solver.hammingWeight(-1));
    }

    @Test public void testMinValue() {
        // Integer.MIN_VALUE = 0x80000000, only sign bit set
        assertEquals(1, solver.hammingWeight(Integer.MIN_VALUE));
    }

    @Test public void testAlternatingBits() {
        // 0b10101010101010101010101010101010 = 16 ones
        assertEquals(16, solver.hammingWeight(0xAAAAAAAA));
    }

    @Test public void testLargePositive() {
        // 1023 = 0b1111111111, 10 ones
        assertEquals(10, solver.hammingWeight(1023));
    }
}
