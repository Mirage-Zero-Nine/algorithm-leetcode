package solutions.bitmanipulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReverseBits_190Test {
    private final ReverseBits_190 solver = new ReverseBits_190();

    @Test public void testBasic() {
        // 00000010100101000001111010011100 -> 00111001011110000010100101000000
        assertEquals(964176192, solver.reverseBits(43261596));
    }

    @Test public void testOfficialSecondExample() {
        // 01111111111111111111111111111100 -> 00111111111111111111111111111110
        assertEquals(1073741822, solver.reverseBits(2147483644));
    }

    @Test public void testZero() {
        assertEquals(0, solver.reverseBits(0));
    }

    @Test public void testAllOnes() {
        // 0xFFFFFFFF reversed is 0xFFFFFFFF => -1
        assertEquals(-1, solver.reverseBits(-1));
    }

    @Test public void testOne() {
        // 0x00000001 -> 0x80000000
        assertEquals(0x80000000, solver.reverseBits(1));
    }

    @Test public void testPalindromeBits() {
        // 0x80000001 -> 0x80000001
        assertEquals(0x80000001, solver.reverseBits(0x80000001));
    }

    @Test public void testTwo() {
        // 0x00000002 (bit 1 set) -> bit 30 set = 0x40000000
        assertEquals(0x40000000, solver.reverseBits(2));
    }

    @Test public void testHighBitOnly() {
        // 0x80000000 -> 0x00000001
        assertEquals(1, solver.reverseBits(0x80000000));
    }

    @Test public void testSignBitAndAdjacentHighBits() {
        assertEquals(Integer.reverse(0xC0000000), solver.reverseBits(0xC0000000));
        assertEquals(Integer.reverse(0xE0000000), solver.reverseBits(0xE0000000));
        assertEquals(Integer.reverse(0xFFFFFFFC), solver.reverseBits(0xFFFFFFFC));
    }

    @Test public void testAlternatingBits() {
        // 0xAAAAAAAA -> reversed = 0x55555555
        assertEquals(0x55555555, solver.reverseBits(0xAAAAAAAA));
    }

    @Test public void testAlternatingBitsInverse() {
        // 0x55555555 -> reversed = 0xAAAAAAAA
        assertEquals(0xAAAAAAAA, solver.reverseBits(0x55555555));
    }

    @Test public void testNegativeNumber() {
        // -2 = 0xFFFFFFFE -> reversed = 0x7FFFFFFF
        assertEquals(0x7FFFFFFF, solver.reverseBits(-2));
    }

    @Test public void testLargePositive() {
        // 0x0000FFFF -> reversed = 0xFFFF0000
        assertEquals(0xFFFF0000, solver.reverseBits(0x0000FFFF));
    }

    @Test public void testOfficialEvenInputBoundaries() {
        assertEquals(Integer.reverse(2), solver.reverseBits(2));
        assertEquals(Integer.reverse(0x7FFFFFFE), solver.reverseBits(0x7FFFFFFE));
        assertEquals(Integer.reverse(0x40000000), solver.reverseBits(0x40000000));
    }

    @Test public void testByteAndNibbleBoundaries() {
        assertEquals(0xFF000000, solver.reverseBits(0x000000FF));
        assertEquals(0x00FF0000, solver.reverseBits(0x0000FF00));
        assertEquals(0xF0F0F0F0, solver.reverseBits(0x0F0F0F0F));
        assertEquals(0x0F0F0F0F, solver.reverseBits(0xF0F0F0F0));
    }

    @Test public void testSparseMultiBitPatterns() {
        int[] values = {0x00010001, 0x01000001, 0x10000008, 0x40000004, 0x80000081};
        for (int value : values) {
            assertEquals(Integer.reverse(value), solver.reverseBits(value));
        }
    }

    @Test public void testDenseMultiBitPatterns() {
        int[] values = {0x0000FFF0, 0x0FFFFFFF, 0x33333333, 0x66666666, 0x7FFFFFFF};
        for (int value : values) {
            assertEquals(Integer.reverse(value), solver.reverseBits(value));
        }
    }

    @Test public void testNegativeUnsignedPatterns() {
        int[] values = {Integer.MIN_VALUE, -3, -0x100, 0x80000001, 0xF000000F};
        for (int value : values) {
            assertEquals(Integer.reverse(value), solver.reverseBits(value));
        }
    }

    @Test public void testEverySingleSetBitAndClearedBit() {
        for (int bit = 0; bit < 32; bit++) {
            assertEquals(1 << (31 - bit), solver.reverseBits(1 << bit));
            assertEquals(~(1 << (31 - bit)), solver.reverseBits(~(1 << bit)));
        }
    }

    @Test public void testSeededValuesAgainstJdkReverse() {
        java.util.Random random = new java.util.Random(1900906L);
        for (int sample = 0; sample < 10000; sample++) {
            int value = random.nextInt();
            assertEquals(Integer.reverse(value), solver.reverseBits(value));
        }
    }

    @Test public void testAllSixteenBitPatternsInBothHalves() {
        for (int value = 0; value <= 65535; value++) {
            assertEquals(Integer.reverse(value), solver.reverseBits(value));
            assertEquals(Integer.reverse(value << 16), solver.reverseBits(value << 16));
        }
    }

    @Test public void testInvolutionForStructuredValues() {
        int[] values = {
                0, 1, 2, 3, 0x0000FFFF, 0x00FF00FF, 0x3333CCCC,
                0x55555555, 0xAAAAAAAA, 0x7FFFFFFF, Integer.MIN_VALUE, -1
        };
        for (int value : values) {
            assertEquals(value, solver.reverseBits(solver.reverseBits(value)));
        }
    }

    @Test public void testInvolutionForSeededValues() {
        java.util.Random random = new java.util.Random(190190L);
        for (int sample = 0; sample < 1000; sample++) {
            int value = random.nextInt();
            assertEquals(value, solver.reverseBits(solver.reverseBits(value)));
        }
    }

    @Test public void testRepeatedCallsRemainIndependent() {
        int first = solver.reverseBits(0x01234567);
        assertEquals(Integer.reverse(0x89ABCDEF), solver.reverseBits(0x89ABCDEF));
        assertEquals(Integer.reverse(0x01234567), first);
        assertEquals(Integer.reverse(0), solver.reverseBits(0));
        assertEquals(Integer.reverse(Integer.MIN_VALUE), solver.reverseBits(Integer.MIN_VALUE));
    }
}
