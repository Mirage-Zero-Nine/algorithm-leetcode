package solutions.bitmanipulation;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for {@link XorOperation_1486}.
 *
 * <p>The valid LeetCode input domain is {@code 1 <= n <= 1000} and
 * {@code 0 <= start <= 1000}. Expected values generated below come from a
 * separate sequential-value oracle rather than from the implementation's
 * {@code start + 2 * i} expression.</p>
 */
class XorOperation_1486Test {

    private final XorOperation_1486 solver = new XorOperation_1486();

    @Test
    void leetCodeExampleOne() {
        assertEquals(8, solver.xorOperation(5, 0));
    }

    @Test
    void leetCodeExampleTwo() {
        assertEquals(8, solver.xorOperation(4, 3));
    }

    @Test
    void oneElementAtZero() {
        assertEquals(0, solver.xorOperation(1, 0));
    }

    @Test
    void oneElementAtOddStart() {
        assertEquals(5, solver.xorOperation(1, 5));
    }

    @Test
    void oneElementAtMaximumStart() {
        assertEquals(1000, solver.xorOperation(1, 1000));
    }

    @Test
    void twoElementsStartingAtZero() {
        assertEquals(2, solver.xorOperation(2, 0));
    }

    @Test
    void twoElementsStartingAtOddValue() {
        assertEquals(2, solver.xorOperation(2, 1));
    }

    @Test
    void twoElementsStartingAtEvenValue() {
        assertEquals(2, solver.xorOperation(2, 100));
    }

    @Test
    void twoElementsAtMaximumStart() {
        assertEquals(referenceXor(2, 1000), solver.xorOperation(2, 1000));
    }

    @Test
    void threeElementSequence() {
        // 3 ^ 5 ^ 7 = 1.
        assertEquals(1, solver.xorOperation(3, 3));
    }

    @Test
    void fourElementSequenceCanCancelToZero() {
        // 1 ^ 3 ^ 5 ^ 7 = 0; this exercises an all-zero XOR result.
        assertEquals(0, solver.xorOperation(4, 1));
    }

    @Test
    void eightElementSequenceCanCancelToZero() {
        assertEquals(0, solver.xorOperation(8, 0));
    }

    @Test
    void sequenceCrossingPowerOfTwoBoundary() {
        assertEquals(254, solver.xorOperation(2, 126));
        assertEquals(510, solver.xorOperation(2, 254));
        assertEquals(1022, solver.xorOperation(2, 510));
    }

    @Test
    void longerSequenceWithLargeStart() {
        assertEquals(106, solver.xorOperation(3, 100));
    }

    @Test
    void maximumNAtZeroStart() {
        assertEquals(referenceXor(1000, 0), solver.xorOperation(1000, 0));
    }

    @Test
    void maximumNAtOddStart() {
        assertEquals(referenceXor(1000, 999), solver.xorOperation(1000, 999));
    }

    @Test
    void maximumNAtMaximumStart() {
        assertEquals(referenceXor(1000, 1000), solver.xorOperation(1000, 1000));
    }

    @Test
    void everySmallInputAgainstReferenceOracle() {
        for (int n = 1; n <= 40; n++) {
            for (int start = 0; start <= 80; start++) {
                assertEquals(referenceXor(n, start), solver.xorOperation(n, start),
                        "n=" + n + ", start=" + start);
            }
        }
    }

    @Test
    void boundaryStartsAcrossLengthsAgainstReferenceOracle() {
        int[] starts = {0, 1, 2, 3, 7, 8, 15, 16, 31, 32, 63, 64,
                127, 128, 255, 256, 511, 512, 999, 1000};
        for (int n = 1; n <= 25; n++) {
            for (int start : starts) {
                assertEquals(referenceXor(n, start), solver.xorOperation(n, start),
                        "n=" + n + ", start=" + start);
            }
        }
    }

    @Test
    void seededValidInputsAgainstReferenceOracle() {
        Random random = new Random(1486L);
        for (int caseNumber = 0; caseNumber < 300; caseNumber++) {
            int n = 1 + random.nextInt(1000);
            int start = random.nextInt(1001);
            assertEquals(referenceXor(n, start), solver.xorOperation(n, start),
                    "case=" + caseNumber + ", n=" + n + ", start=" + start);
        }
    }

    @Test
    void allLengthsAtMinimumStart() {
        for (int n = 1; n <= 1000; n++) {
            assertEquals(referenceXor(n, 0), solver.xorOperation(n, 0), "n=" + n);
        }
    }

    @Test
    void allLengthsAtMaximumStart() {
        for (int n = 1; n <= 1000; n++) {
            assertEquals(referenceXor(n, 1000), solver.xorOperation(n, 1000), "n=" + n);
        }
    }

    @Test
    void oddAndEvenStartsRemainCoveredAtMaximumLength() {
        for (int start = 0; start <= 1000; start += 2) {
            assertEquals(referenceXor(1000, start), solver.xorOperation(1000, start),
                    "even start=" + start);
            if (start < 1000) {
                assertEquals(referenceXor(1000, start + 1), solver.xorOperation(1000, start + 1),
                        "odd start=" + (start + 1));
            }
        }
    }

    @Test
    void repeatedCallsDoNotShareState() {
        assertEquals(8, solver.xorOperation(5, 0));
        assertEquals(8, solver.xorOperation(4, 3));
        assertEquals(0, solver.xorOperation(8, 0));
        assertEquals(1000, solver.xorOperation(1, 1000));
        assertEquals(referenceXor(37, 712), solver.xorOperation(37, 712));
    }

    @Test
    void resultsAreIndependentAcrossFreshInstances() {
        XorOperation_1486 first = new XorOperation_1486();
        XorOperation_1486 second = new XorOperation_1486();
        assertEquals(first.xorOperation(73, 411), second.xorOperation(73, 411));
        assertEquals(referenceXor(73, 411), first.xorOperation(73, 411));
    }

    @Test
    void maximumContractInputsDoNotTruncateTheSequence() {
        // The final term is start + 2 * (n - 1) = 2998, so high bits beyond
        // start must participate in the XOR result.
        assertEquals(referenceXor(1000, 1000), solver.xorOperation(1000, 1000));
        assertEquals(referenceXor(999, 1000), solver.xorOperation(999, 1000));
    }

    /**
     * Independent contract oracle: build each arithmetic-sequence value by
     * advancing the previous value, then XOR it into the accumulator.
     */
    private static int referenceXor(int n, int start) {
        int result = 0;
        int value = start;
        for (int i = 0; i < n; i++) {
            result ^= value;
            value += 2;
        }
        return result;
    }
}
