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

    @Test public void testEverySingleValueBitPosition() {
        assertEquals(0, solver.rangeBitwiseAnd(0, 0));
        for (int bit = 0; bit < 31; bit++) {
            int value = 1 << bit;
            assertEquals(value, solver.rangeBitwiseAnd(value, value), "single value at bit " + bit);
        }
    }

    @Test public void testAdjacentRangesAtLowBits() {
        assertEquals(0, solver.rangeBitwiseAnd(1, 2));
        assertEquals(2, solver.rangeBitwiseAnd(2, 3));
        assertEquals(0, solver.rangeBitwiseAnd(3, 4));
        assertEquals(4, solver.rangeBitwiseAnd(4, 5));
        assertEquals(4, solver.rangeBitwiseAnd(4, 6));
        assertEquals(0, solver.rangeBitwiseAnd(7, 8));
        assertEquals(8, solver.rangeBitwiseAnd(8, 9));
    }

    @Test public void testRangesWithinOnePowerOfTwoBlock() {
        assertEquals(16, solver.rangeBitwiseAnd(16, 23));
        assertEquals(24, solver.rangeBitwiseAnd(24, 27));
        assertEquals(40, solver.rangeBitwiseAnd(40, 43));
        assertEquals(96, solver.rangeBitwiseAnd(96, 111));
        assertEquals(256, solver.rangeBitwiseAnd(256, 511));
    }

    @Test public void testCrossingSeveralPowerBoundariesProducesZero() {
        assertEquals(0, solver.rangeBitwiseAnd(3, 8));
        assertEquals(0, solver.rangeBitwiseAnd(7, 16));
        assertEquals(0, solver.rangeBitwiseAnd(15, 32));
        assertEquals(0, solver.rangeBitwiseAnd(31, 64));
        assertEquals(0, solver.rangeBitwiseAnd(1023, 4096));
    }

    @Test public void testRangesStartingAtZero() {
        assertEquals(0, solver.rangeBitwiseAnd(0, 2));
        assertEquals(0, solver.rangeBitwiseAnd(0, 7));
        assertEquals(0, solver.rangeBitwiseAnd(0, 1 << 15));
        assertEquals(0, solver.rangeBitwiseAnd(0, 1 << 30));
        assertEquals(0, solver.rangeBitwiseAnd(0, Integer.MAX_VALUE));
    }

    @Test public void testMaximumValuePrefixRanges() {
        assertEquals(Integer.MAX_VALUE, solver.rangeBitwiseAnd(Integer.MAX_VALUE, Integer.MAX_VALUE));
        assertEquals(Integer.MAX_VALUE - 1, solver.rangeBitwiseAnd(Integer.MAX_VALUE - 1, Integer.MAX_VALUE));
        assertEquals(Integer.MAX_VALUE - 3, solver.rangeBitwiseAnd(Integer.MAX_VALUE - 3, Integer.MAX_VALUE - 1));
        assertEquals(0x7fffffc0, solver.rangeBitwiseAnd(0x7fffffc0, 0x7fffffff));
        assertEquals(0x40000000, solver.rangeBitwiseAnd(0x40000000, Integer.MAX_VALUE));
    }

    @Test public void testWideValidRangesHaveKnownPrefixResults() {
        assertEquals(0, solver.rangeBitwiseAnd(1, Integer.MAX_VALUE));
        assertEquals(0, solver.rangeBitwiseAnd(123456789, Integer.MAX_VALUE));
        assertEquals(0x20000000, solver.rangeBitwiseAnd(0x20000000, 0x3fffffff));
        assertEquals(0x40000000, solver.rangeBitwiseAnd(0x40000000, 0x7fffffff));
        assertEquals(0x10000000, solver.rangeBitwiseAnd(0x10000000, 0x1fffffff));
    }

    @Test public void testSeededRangesAgainstIndependentPrefixOracle() {
        java.util.Random random = new java.util.Random(201201L);
        for (int sample = 0; sample < 2000; sample++) {
            int first = random.nextInt(Integer.MAX_VALUE);
            int second = random.nextInt(Integer.MAX_VALUE);
            int left = Math.min(first, second);
            int right = Math.max(first, second);
            assertEquals(rangeAndByCommonPrefix(left, right), solver.rangeBitwiseAnd(left, right),
                    "Unexpected result for seeded range [" + left + ", " + right + "]");
        }
    }

    @Test public void testSeededRangesWithinCommonPrefixes() {
        java.util.Random random = new java.util.Random(201202L);
        for (int sample = 0; sample < 1000; sample++) {
            int prefix = random.nextInt(1 << 20);
            int left = prefix << 10;
            int right = left + random.nextInt(1 << 10);
            assertEquals(rangeAndByCommonPrefix(left, right), solver.rangeBitwiseAnd(left, right),
                    "Unexpected result for common-prefix range [" + left + ", " + right + "]");
        }
    }

    @Test public void testPrefixOracleAtSignedIntBoundary() {
        int[] starts = {0, 1, 0x3fffffff, 0x40000000, 0x5aaaaaaa, 0x7ffff000};
        int[] ends = {0, 1, 0x40000000, 0x7fffffff, 0x7fffffff, 0x7fffffff};
        for (int index = 0; index < starts.length; index++) {
            assertEquals(rangeAndByCommonPrefix(starts[index], ends[index]),
                    solver.rangeBitwiseAnd(starts[index], ends[index]));
        }
    }

    @Test public void testRepeatedCallsDoNotRetainRangeState() {
        assertEquals(4, solver.rangeBitwiseAnd(5, 7));
        assertEquals(0, solver.rangeBitwiseAnd(0, Integer.MAX_VALUE));
        assertEquals(12, solver.rangeBitwiseAnd(12, 13));
        assertEquals(Integer.MAX_VALUE, solver.rangeBitwiseAnd(Integer.MAX_VALUE, Integer.MAX_VALUE));
        assertEquals(0, solver.rangeBitwiseAnd(7, 8));
    }

    @Test public void testInterleavedNarrowAndWideCalls() {
        int[] left = {0, 4, 0x40000000, 26, 1024, Integer.MAX_VALUE - 1};
        int[] right = {Integer.MAX_VALUE, 7, Integer.MAX_VALUE, 28, 2047, Integer.MAX_VALUE};
        for (int index = 0; index < left.length; index++) {
            assertEquals(rangeAndByCommonPrefix(left[index], right[index]),
                    solver.rangeBitwiseAnd(left[index], right[index]));
        }
    }

    private int rangeAndByCommonPrefix(int left, int right) {
        int differingBits = left ^ right;
        if (differingBits == 0) {
            return left;
        }
        int differingBitCount = 32 - Integer.numberOfLeadingZeros(differingBits);
        int commonPrefixMask = -1 << differingBitCount;
        return left & commonPrefixMask;
    }
}
