package solutions.bitmanipulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HammingDistance_461Test {
    private final HammingDistance_461 solver = new HammingDistance_461();

    @Test public void testBasic() {
        assertEquals(2, solver.hammingDistance(1, 4));
    }

    @Test public void testSecondOfficialExample() {
        assertEquals(1, solver.hammingDistance(3, 1));
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

    @Test public void testMaxIntAgainstItself() {
        assertEquals(0, solver.hammingDistance(Integer.MAX_VALUE, Integer.MAX_VALUE));
    }

    @Test public void testMaxIntAgainstImmediatePredecessor() {
        assertEquals(1, solver.hammingDistance(Integer.MAX_VALUE, Integer.MAX_VALUE - 1));
    }

    @Test public void testHighestAllowedBit() {
        int highestAllowedBit = 1 << 30;
        assertEquals(1, solver.hammingDistance(0, highestAllowedBit));
        assertEquals(30, solver.hammingDistance(Integer.MAX_VALUE, highestAllowedBit));
    }

    @Test public void testConsecutive() {
        // 2 = 10, 3 = 11 -> differ in 1 bit
        assertEquals(1, solver.hammingDistance(2, 3));
    }

    @Test public void testSingleBitCarryChangesTwoPositions() {
        assertEquals(2, solver.hammingDistance(1, 2));
    }

    @Test public void testLargeNumbers() {
        // 255 = 11111111, 0 = 00000000
        assertEquals(8, solver.hammingDistance(255, 0));
    }

    @Test public void testAlternatingBitsAgainstZero() {
        assertEquals(16, solver.hammingDistance(0, 0x55555555));
        assertEquals(15, solver.hammingDistance(0, 0x2AAAAAAA));
    }

    @Test public void testUpperAndLowerHalfMasks() {
        assertEquals(31, solver.hammingDistance(0x40000000, 0x3FFFFFFF));
    }

    @Test public void testSparseDisjointBits() {
        assertEquals(4, solver.hammingDistance(0x40000001, 0x20000002));
    }

    @Test public void testDenseMaskDifference() {
        assertEquals(15, solver.hammingDistance(0x7FFFFFFF, 0x0000FFFF));
    }

    @Test public void testSymmetric() {
        assertEquals(solver.hammingDistance(3, 7), solver.hammingDistance(7, 3));
    }

    @Test public void testGiantCase() {
        // large values: 0x55555555 = 1431655765, 0x2AAAAAAA = 715827882
        // XOR = 0x7FFFFFFF = all 31 bits set -> 31
        assertEquals(31, solver.hammingDistance(0x55555555, 0x2AAAAAAA));
    }

    @Test public void testAllPairsInSmallNonnegativeDomain() {
        for (int x = 0; x <= 255; x++) {
            for (int y = 0; y <= 255; y++) {
                assertEquals(expectedHammingDistance(x, y), solver.hammingDistance(x, y),
                        "x=" + x + ", y=" + y);
            }
        }
    }

    private int expectedHammingDistance(int x, int y) {
        int distance = 0;
        for (int bit = 0; bit < 31; bit++) {
            if (((x >>> bit) & 1) != ((y >>> bit) & 1)) {
                distance++;
            }
        }
        return distance;
    }

    @Test public void testEachHighBitDiffersFromZeroAndItsComplement() {
        for (int bit = 0; bit < 31; bit++) {
            int value = 1 << bit;
            assertEquals(1, solver.hammingDistance(0, value));
            assertEquals(31, solver.hammingDistance(value, Integer.MAX_VALUE ^ value));
        }
    }

    @Test public void testSeededFullNonnegativeDomain() {
        java.util.Random random = new java.util.Random(4610906L);
        for (int sample = 0; sample < 10000; sample++) {
            int x = random.nextInt() & Integer.MAX_VALUE;
            int y = random.nextInt() & Integer.MAX_VALUE;
            assertEquals(Integer.bitCount(x ^ y), solver.hammingDistance(x, y));
        }
    }

    @Test public void testConsecutiveNumbersAcrossEveryCarryBoundary() {
        for (int bit = 1; bit < 31; bit++)
            assertEquals(bit + 1, solver.hammingDistance((1 << bit) - 1, 1 << bit));
    }

    @Test public void testAdjacentSingleBitMasks() {
        for (int bit = 0; bit < 30; bit++) {
            assertEquals(2, solver.hammingDistance(1 << bit, 1 << (bit + 1)),
                    "bit=" + bit);
        }
    }

    @Test public void testClearingEveryBitFromMaximum() {
        for (int bit = 0; bit < 31; bit++) {
            assertEquals(1, solver.hammingDistance(Integer.MAX_VALUE, Integer.MAX_VALUE ^ (1 << bit)),
                    "bit=" + bit);
        }
    }

    @Test public void testSymmetryAcrossBoundaryValues() {
        int[] values = {0, 1, 2, 31, 1 << 15, 1 << 30, Integer.MAX_VALUE};
        for (int x : values) {
            for (int y : values) {
                assertEquals(solver.hammingDistance(x, y), solver.hammingDistance(y, x),
                        "x=" + x + ", y=" + y);
            }
        }
    }

    @Test public void testRepeatedCallsDoNotRetainState() {
        assertEquals(31, solver.hammingDistance(0, Integer.MAX_VALUE));
        assertEquals(0, solver.hammingDistance(123456789, 123456789));
        assertEquals(Integer.bitCount(0x12345678 ^ 0x0F0F0F0F),
                solver.hammingDistance(0x12345678, 0x0F0F0F0F));
        assertEquals(1, solver.hammingDistance(0, 1));
    }
}
