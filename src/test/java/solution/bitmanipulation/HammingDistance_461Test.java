package solution.bitmanipulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HammingDistance_461Test {
    private final HammingDistance_461 solver = new HammingDistance_461();

    @Test public void testBasic() {
        assertEquals(2, solver.hammingDistance(1, 4));
    }

    @Test public void testSameNumber() {
        assertEquals(0, solver.hammingDistance(5, 5));
    }

    @Test public void testZero() {
        assertEquals(0, solver.hammingDistance(0, 0));
    }

    @Test public void testOneZero() {
        assertEquals(3, solver.hammingDistance(0, 7));
    }

    @Test public void testLarger() {
        assertEquals(4, solver.hammingDistance(0b1010, 0b0101));
    }

    @Test public void testMaxInt() {
        assertEquals(31, solver.hammingDistance(0, Integer.MAX_VALUE));
    }

    @Test public void testConsecutive() {
        // 2 = 10, 3 = 11 -> differ in 1 bit
        assertEquals(1, solver.hammingDistance(2, 3));
    }

    @Test public void testLargeNumbers() {
        // 255 = 11111111, 0 = 00000000
        assertEquals(8, solver.hammingDistance(255, 0));
    }

    @Test public void testSymmetric() {
        assertEquals(solver.hammingDistance(3, 7), solver.hammingDistance(7, 3));
    }

    @Test public void testGiantCase() {
        // large values: 0x55555555 = 1431655765, 0x2AAAAAAA = 715827882
        // XOR = 0x7FFFFFFF = all 31 bits set -> 31
        assertEquals(31, solver.hammingDistance(0x55555555, 0x2AAAAAAA));
    }
}
