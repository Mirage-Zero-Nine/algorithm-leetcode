package solutions.bitmanipulation;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GreyCode_89Test {
    private final GreyCode_89 solver = new GreyCode_89();

    @Test public void testN2OfficialSequence() {
        List<Integer> result = solver.grayCode(2);
        assertEquals(4, result.size());
        assertEquals(0, result.get(0).intValue());
        assertIterableEquals(List.of(0, 1, 3, 2), result);
        assertGrayProperty(result);
    }

    @Test public void testN0ClassSupportedBoundary() {
        assertIterableEquals(List.of(0), solver.grayCode(0));
    }

    @Test public void testN1OfficialSequence() {
        assertIterableEquals(List.of(0, 1), solver.grayCode(1));
    }

    @Test public void testN3KnownSequence() {
        assertIterableEquals(List.of(0, 1, 3, 2, 6, 7, 5, 4), solver.grayCode(3));
    }

    @Test public void testN4ExactSequence() {
        assertIterableEquals(List.of(0, 1, 3, 2, 6, 7, 5, 4,
                12, 13, 15, 14, 10, 11, 9, 8), solver.grayCode(4));
    }

    @Test public void testIndependentReflectedPrefixOracleForSmallWidths() {
        for (int bits = 0; bits <= 8; bits++) {
            assertIterableEquals(reflectedPrefixOracle(bits), solver.grayCode(bits),
                    "unexpected sequence for n=" + bits);
        }
    }

    @Test public void testFormulaFromDocumentationAtEverySixBitIndex() {
        List<Integer> actual = solver.grayCode(6);
        for (int i = 0; i < actual.size(); i++) {
            assertEquals(i ^ (i >>> 1), actual.get(i), "index=" + i);
        }
    }

    @Test public void testN5HasExpectedSizeAndUniqueCodes() {
        List<Integer> result = solver.grayCode(5);
        assertEquals(32, result.size());
        assertEquals(32, new HashSet<>(result).size());
        assertGrayProperty(result);
    }

    @Test public void testMinimumLeetCodeWidthSatisfiesFullContract() {
        assertValidGrayCode(solver.grayCode(1), 1);
    }

    @Test public void testEverySupportedWidthHasExpectedCardinality() {
        for (int bits = 1; bits <= 16; bits++) {
            assertEquals(1 << bits, solver.grayCode(bits).size(), "n=" + bits);
        }
    }

    @Test public void testEverySupportedWidthContainsOnlyInRangeValues() {
        for (int bits = 1; bits <= 16; bits++) {
            int limit = 1 << bits;
            for (int code : solver.grayCode(bits)) {
                assertTrue(code >= 0 && code < limit,
                        "n=" + bits + ", code=" + code);
            }
        }
    }

    @Test public void testUniquenessAcrossSmallAndLargeWidths() {
        for (int bits : new int[]{1, 2, 3, 4, 5, 8, 10, 12, 16}) {
            List<Integer> codes = solver.grayCode(bits);
            assertEquals(codes.size(), new HashSet<>(codes).size(), "n=" + bits);
        }
    }

    @Test public void testAllValuesAreRepresentedForWidthsThroughTen() {
        for (int bits = 1; bits <= 10; bits++) {
            Set<Integer> seen = new HashSet<>(solver.grayCode(bits));
            for (int value = 0; value < (1 << bits); value++) {
                assertTrue(seen.contains(value), "n=" + bits + ", missing=" + value);
            }
        }
    }

    @Test public void testAdjacentCodesDifferByExactlyOneBitForEveryWidth() {
        for (int bits = 1; bits <= 16; bits++) {
            assertGrayProperty(solver.grayCode(bits));
        }
    }

    @Test public void testFirstAndLastCodesDifferByOneBitForEveryWidth() {
        for (int bits = 1; bits <= 16; bits++) {
            List<Integer> codes = solver.grayCode(bits);
            assertEquals(1, Integer.bitCount(codes.get(0) ^ codes.get(codes.size() - 1)),
                    "n=" + bits);
        }
    }

    @Test public void testWidthSixHasExpectedTransitionBitPattern() {
        List<Integer> codes = solver.grayCode(6);
        int[] transitionsByBit = new int[6];
        for (int i = 1; i < codes.size(); i++) {
            int changedBit = Integer.numberOfTrailingZeros(codes.get(i) ^ codes.get(i - 1));
            transitionsByBit[changedBit]++;
        }
        assertEquals(List.of(32, 16, 8, 4, 2, 1),
                java.util.Arrays.stream(transitionsByBit).boxed().toList());
    }

    @Test public void testWidthTenSatisfiesIndependentContractOracle() {
        assertValidGrayCode(solver.grayCode(10), 10);
    }

    @Test public void testWidthFifteenSatisfiesIndependentContractOracle() {
        assertValidGrayCode(solver.grayCode(15), 15);
    }

    @Test public void testMaximumLeetCodeWidthSatisfiesIndependentContractOracle() {
        assertValidGrayCode(solver.grayCode(16), 16);
    }

    @Test public void testMaximumWidthContainsItsHighestCode() {
        Set<Integer> seen = new HashSet<>(solver.grayCode(16));
        assertTrue(seen.contains(0));
        assertTrue(seen.contains((1 << 16) - 1));
    }

    @Test public void testCodesAtPowerOfTwoBoundariesMatchFormula() {
        for (int bits = 1; bits <= 16; bits++) {
            List<Integer> codes = solver.grayCode(bits);
            int[] indices = {0, 1, (1 << (bits - 1)) - 1, 1 << (bits - 1),
                    (1 << bits) - 2, (1 << bits) - 1};
            for (int index : indices) {
                assertEquals(index ^ (index >>> 1), codes.get(index),
                        "n=" + bits + ", index=" + index);
            }
        }
    }

    @Test public void testSixBitCodeBeginsWithZero() {
        assertEquals(0, solver.grayCode(6).get(0).intValue());
    }

    @Test public void testZeroBitResultIsAValidSingletonSequence() {
        assertValidGrayCode(solver.grayCode(0), 0);
    }

    @Test public void testRepeatedCallsProduceEqualSequences() {
        assertIterableEquals(solver.grayCode(9), solver.grayCode(9));
    }

    @Test public void testReturnedListsDoNotShareStorage() {
        List<Integer> first = solver.grayCode(4);
        List<Integer> second = solver.grayCode(4);
        assertNotSame(first, second);
        first.set(0, 99);
        assertEquals(0, second.get(0).intValue());
        assertEquals(0, solver.grayCode(4).get(0).intValue());
    }

    @Test public void testCallsDoNotShareReturnedList() {
        List<Integer> codes = solver.grayCode(3);
        codes.clear();
        assertEquals(8, solver.grayCode(3).size());
    }

    @Test public void testInterleavedWidthsRemainIndependent() {
        List<Integer> widthThree = solver.grayCode(3);
        List<Integer> widthEight = solver.grayCode(8);
        List<Integer> widthThreeAgain = solver.grayCode(3);
        assertIterableEquals(widthThree, widthThreeAgain);
        assertEquals(256, widthEight.size());
        assertEquals(8, widthThree.size());
    }

    @Test public void testFreshInstanceMatchesExistingInstance() {
        GreyCode_89 freshSolver = new GreyCode_89();
        for (int bits : new int[]{0, 1, 2, 7, 16}) {
            assertIterableEquals(solver.grayCode(bits), freshSolver.grayCode(bits), "n=" + bits);
        }
    }

    @Test public void testSmallWidthsExhaustivelyMatchSetAndSequenceOracles() {
        for (int bits = 0; bits <= 6; bits++) {
            List<Integer> actual = solver.grayCode(bits);
            assertIterableEquals(reflectedPrefixOracle(bits), actual, "n=" + bits);
            assertEquals(expectedCodes(bits), new HashSet<>(actual), "n=" + bits);
        }
    }

    @Test public void testAllCodesInRangeAndCyclicAtLargerWidths() {
        for (int bits : new int[]{6, 7, 8, 9, 11, 12, 14, 16}) {
            assertValidGrayCode(solver.grayCode(bits), bits);
        }
    }

    private void assertGrayProperty(List<Integer> codes) {
        for (int i = 1; i < codes.size(); i++) {
            int diff = codes.get(i) ^ codes.get(i - 1);
            assertEquals(1, Integer.bitCount(diff), "adjacent index=" + i);
        }
    }

    private void assertValidGrayCode(List<Integer> codes, int bits) {
        int expectedSize = 1 << bits;
        assertEquals(expectedSize, codes.size(), "size for n=" + bits);
        assertEquals(0, codes.get(0).intValue(), "start for n=" + bits);

        Set<Integer> seen = new HashSet<>(codes);
        assertEquals(expectedSize, seen.size(), "uniqueness for n=" + bits);
        for (int value = 0; value < expectedSize; value++) {
            assertTrue(seen.contains(value), "n=" + bits + ", missing=" + value);
        }
        assertGrayProperty(codes);
        if (bits > 0) {
            assertEquals(1, Integer.bitCount(codes.get(0) ^ codes.get(codes.size() - 1)),
                    "cycle for n=" + bits);
        }
    }

    /**
     * Builds Gray code by reflecting the existing prefix and setting the newly added bit.
     * This oracle does not use the production formula {@code i ^ (i >>> 1)}.
     */
    private List<Integer> reflectedPrefixOracle(int bits) {
        List<Integer> expected = new ArrayList<>();
        expected.add(0);
        for (int bit = 0; bit < bits; bit++) {
            int mask = 1 << bit;
            for (int i = expected.size() - 1; i >= 0; i--) {
                expected.add(expected.get(i) | mask);
            }
        }
        return expected;
    }

    private Set<Integer> expectedCodes(int bits) {
        Set<Integer> expected = new HashSet<>();
        for (int value = 0; value < (1 << bits); value++) {
            expected.add(value);
        }
        return expected;
    }
}
