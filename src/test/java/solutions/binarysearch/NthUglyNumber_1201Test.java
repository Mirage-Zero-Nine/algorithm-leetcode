package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Contract tests for the inclusion-exclusion/binary-search solution to LeetCode 1201.
 *
 * <p>The oracle in this class deliberately uses direct enumeration for small cases and a
 * separate inclusion-exclusion calculation for large cases. It therefore does not simply
 * repeat the implementation's binary-search decision at every assertion.</p>
 */
public class NthUglyNumber_1201Test {

    private final NthUglyNumber_1201 test = new NthUglyNumber_1201();

    @Test
    public void testOfficialExamples() {
        assertEquals(4, test.nthUglyNumber(3, 2, 3, 5));
        assertEquals(6, test.nthUglyNumber(4, 2, 3, 4));
        assertEquals(10, test.nthUglyNumber(5, 2, 11, 13));
    }

    @Test
    public void testFirstUglyNumberIsTheSmallestDivisor() {
        assertEquals(2, test.nthUglyNumber(1, 2, 3, 5));
        assertEquals(7, test.nthUglyNumber(1, 7, 11, 13));
        assertEquals(1, test.nthUglyNumber(1, 1, 999_999_999, 1_000_000_000));
    }

    @Test
    public void testSecondUglyNumberAndEarlyOverlaps() {
        assertEquals(bruteNth(2, 2, 3, 5), test.nthUglyNumber(2, 2, 3, 5));
        assertEquals(bruteNth(2, 4, 6, 9), test.nthUglyNumber(2, 4, 6, 9));
        assertEquals(bruteNth(3, 5, 10, 15), test.nthUglyNumber(3, 5, 10, 15));
    }

    @Test
    public void testEveryPositiveIntegerWhenOneDivisorIsOne() {
        assertEquals(37, test.nthUglyNumber(37, 1, 17, 23));
        assertEquals(1_000_000_000, test.nthUglyNumber(1_000_000_000, 1, 999_999_999, 1_000_000_000));
    }

    @Test
    public void testAllDivisorsEqual() {
        assertEquals(21, test.nthUglyNumber(7, 3, 3, 3));
        assertEquals(2_000_000_000, test.nthUglyNumber(1_000_000_000, 2, 2, 2));
    }

    @Test
    public void testTwoDivisorsEqual() {
        assertEquals(bruteNth(25, 4, 4, 7), test.nthUglyNumber(25, 4, 4, 7));
        assertEquals(bruteNth(40, 3, 11, 3), test.nthUglyNumber(40, 3, 11, 3));
    }

    @Test
    public void testDivisibilityChainDoesNotDoubleCount() {
        assertEquals(20, test.nthUglyNumber(10, 2, 4, 8));
        assertEquals(bruteNth(35, 3, 6, 12), test.nthUglyNumber(35, 3, 6, 12));
        assertEquals(bruteNth(45, 5, 10, 20), test.nthUglyNumber(45, 5, 10, 20));
    }

    @Test
    public void testPairwiseGcdOverlaps() {
        assertEquals(bruteNth(30, 6, 10, 15), test.nthUglyNumber(30, 6, 10, 15));
        assertEquals(bruteNth(50, 12, 18, 30), test.nthUglyNumber(50, 12, 18, 30));
        assertEquals(bruteNth(60, 14, 21, 35), test.nthUglyNumber(60, 14, 21, 35));
    }

    @Test
    public void testPairwiseCoprimeDivisors() {
        assertEquals(66, test.nthUglyNumber(20, 7, 11, 13));
        assertEquals(bruteNth(80, 5, 7, 11), test.nthUglyNumber(80, 5, 7, 11));
    }

    @Test
    public void testMixedPrimeAndCompositeDivisors() {
        assertEquals(14, test.nthUglyNumber(10, 2, 3, 7));
        assertEquals(bruteNth(73, 4, 9, 25), test.nthUglyNumber(73, 4, 9, 25));
        assertEquals(bruteNth(90, 8, 15, 22), test.nthUglyNumber(90, 8, 15, 22));
    }

    @Test
    public void testSmallCasesAgainstIndependentEnumeration() {
        for (int a = 1; a <= 6; a++) {
            for (int b = 1; b <= 6; b++) {
                for (int c = 1; c <= 6; c++) {
                    for (int n = 1; n <= 15; n++) {
                        assertEquals(bruteNth(n, a, b, c), test.nthUglyNumber(n, a, b, c),
                                "n=" + n + ", a=" + a + ", b=" + b + ", c=" + c);
                    }
                }
            }
        }
    }

    @Test
    public void testSmallCasesWithLargeCommonFactors() {
        assertEquals(bruteNth(17, 30, 42, 70), test.nthUglyNumber(17, 30, 42, 70));
        assertEquals(bruteNth(24, 36, 48, 60), test.nthUglyNumber(24, 36, 48, 60));
        assertEquals(bruteNth(32, 49, 63, 77), test.nthUglyNumber(32, 49, 63, 77));
    }

    @Test
    public void testKnownMediumCase() {
        assertEquals(108, test.nthUglyNumber(40, 4, 6, 17));
        assertEquals(bruteNth(250, 13, 17, 19), test.nthUglyNumber(250, 13, 17, 19));
    }

    @Test
    public void testCountingBoundaryAtAnswer() {
        assertMinimalByIndependentCount(100, 2, 3, 5);
        assertMinimalByIndependentCount(250, 6, 10, 15);
        assertMinimalByIndependentCount(1_000, 17, 19, 23);
    }

    @Test
    public void testLargeNOfficialSample() {
        assertEquals(1_999_999_984,
                test.nthUglyNumber(1_000_000_000, 2, 217_983_653, 336_916_467));
    }

    @Test
    public void testMaximumNWithSeveralDensities() {
        assertEquals(1_000_000_000, test.nthUglyNumber(1_000_000_000, 1, 2, 3));
        assertEquals(bruteNthByCount(1_000_000_000, 2, 3, 5),
                test.nthUglyNumber(1_000_000_000, 2, 3, 5));
        assertEquals(bruteNthByCount(500_000_000, 500_000_000, 500_000_001, 3),
                test.nthUglyNumber(500_000_000, 500_000_000, 500_000_001, 3));
    }

    @Test
    public void testMaximumIndividualDivisor() {
        assertEquals(999_999, test.nthUglyNumber(1, 1_000_000_000, 999_999, 999_999));
        assertEquals(bruteNthByCount(3, 1_000_000_000, 999_999_999, 1),
                test.nthUglyNumber(3, 1_000_000_000, 999_999_999, 1));
    }

    @Test
    public void testLargeLcmWithinProductConstraint() {
        // 500,000,000 * 999,999,999 * 2 is below 10^18; pairwise LCMs approach 10^18.
        assertEquals(bruteNthByCount(500_000_000, 500_000_000, 999_999_999, 2),
                test.nthUglyNumber(500_000_000, 500_000_000, 999_999_999, 2));
        assertEquals(bruteNthByCount(2, 500_000_000, 999_999_999, 2),
                test.nthUglyNumber(2, 500_000_000, 999_999_999, 2));
    }

    @Test
    public void testResultCanReachTwoBillion() {
        assertEquals(2_000_000_000, test.nthUglyNumber(1_000_000_000, 2, 4, 8));
        assertMinimalByIndependentCount(1_000_000_000, 2, 4, 8);
    }

    @Test
    public void testFactorOrderingDoesNotChangeResult() {
        int expected = test.nthUglyNumber(1_234, 6, 10, 15);
        assertEquals(expected, test.nthUglyNumber(1_234, 15, 6, 10));
        assertEquals(expected, test.nthUglyNumber(1_234, 10, 15, 6));
        assertEquals(expected, test.nthUglyNumber(1_234, 6, 15, 10));
    }

    @Test
    public void testRepeatedCallsDoNotShareState() {
        assertEquals(4, test.nthUglyNumber(3, 2, 3, 5));
        assertEquals(10, test.nthUglyNumber(5, 2, 11, 13));
        assertEquals(4, test.nthUglyNumber(3, 2, 3, 5));
    }

    @Test
    public void testAnswerHasExactlyNValuesThroughIt() {
        int answer = test.nthUglyNumber(137, 8, 12, 18);
        assertEquals(137, countByEnumeration(answer, 8, 12, 18));
        assertTrue(answer >= 1);
    }

    @Test
    public void testNoEarlierCandidateCanBeTheAnswer() {
        int answer = test.nthUglyNumber(73, 14, 21, 35);
        assertEquals(72, countByEnumeration(answer - 1, 14, 21, 35));
        assertEquals(73, countByEnumeration(answer, 14, 21, 35));
    }

    @Test
    public void testAdjacentUglyNumbersAreCountedOnce() {
        assertEquals(bruteNth(40, 2, 3, 4), test.nthUglyNumber(40, 2, 3, 4));
        assertEquals(bruteNth(41, 2, 3, 4), test.nthUglyNumber(41, 2, 3, 4));
    }

    @Test
    public void testSparseUglySequence() {
        assertEquals(bruteNth(1, 997, 998, 999), test.nthUglyNumber(1, 997, 998, 999));
        assertEquals(bruteNth(17, 997, 998, 999), test.nthUglyNumber(17, 997, 998, 999));
        assertEquals(bruteNth(100, 997, 998, 999), test.nthUglyNumber(100, 997, 998, 999));
    }

    @Test
    public void testDeterministicVariedCasesAgainstEnumeration() {
        int[][] cases = {
                {1, 4, 6, 9}, {2, 2, 7, 11}, {3, 8, 13, 21}, {4, 4, 5, 6},
                {6, 11, 16, 23}, {8, 15, 22, 29}, {12, 1, 12, 1}, {17, 19, 23, 29}
        };
        for (int[] values : cases) {
            int n = values[0];
            assertEquals(bruteNth(n, values[1], values[2], values[3]),
                    test.nthUglyNumber(n, values[1], values[2], values[3]));
        }
    }

    @Test
    public void testIndependentCountOracleAcrossSmallRange() {
        for (int a = 2; a <= 9; a++) {
            for (int b = 2; b <= 9; b++) {
                for (int c = 2; c <= 9; c++) {
                    int n = 1 + (a * 7 + b * 11 + c * 13) % 40;
                    assertEquals(bruteNthByCount(n, a, b, c), test.nthUglyNumber(n, a, b, c),
                            "n=" + n + ", a=" + a + ", b=" + b + ", c=" + c);
                }
            }
        }
    }

    @Test
    public void testLargeLcmCasesAgainstCountOracle() {
        assertEquals(bruteNthByCount(999, 99_999, 100_000, 100_001),
                test.nthUglyNumber(999, 99_999, 100_000, 100_001));
        assertEquals(bruteNthByCount(50_000, 12_345, 23_456, 34_567),
                test.nthUglyNumber(50_000, 12_345, 23_456, 34_567));
    }

    private void assertMinimalByIndependentCount(int n, int a, int b, int c) {
        int answer = test.nthUglyNumber(n, a, b, c);
        assertEquals(n, countByInclusionExclusion(answer, a, b, c));
        assertEquals(n - 1L, countByInclusionExclusion(answer - 1L, a, b, c));
    }

    private static int bruteNth(int n, int a, int b, int c) {
        int count = 0;
        for (int value = 1; ; value++) {
            if (value % a == 0 || value % b == 0 || value % c == 0) {
                if (++count == n) {
                    return value;
                }
            }
        }
    }

    private static int bruteNthByCount(int n, int a, int b, int c) {
        long low = 1;
        long high = 2_000_000_000L;
        while (low < high) {
            long mid = low + (high - low) / 2;
            if (countByInclusionExclusion(mid, a, b, c) < n) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return (int) low;
    }

    private static long countByEnumeration(int upperBound, int a, int b, int c) {
        long count = 0;
        for (int value = 1; value <= upperBound; value++) {
            if (value % a == 0 || value % b == 0 || value % c == 0) {
                count++;
            }
        }
        return count;
    }

    private static long countByInclusionExclusion(long value, long a, long b, long c) {
        long ab = lcm(a, b);
        long ac = lcm(a, c);
        long bc = lcm(b, c);
        long abc = lcm(a, bc);
        return value / a + value / b + value / c
                - value / ab - value / ac - value / bc + value / abc;
    }

    private static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    private static long gcd(long a, long b) {
        while (b != 0) {
            long remainder = a % b;
            a = b;
            b = remainder;
        }
        return a;
    }
}
