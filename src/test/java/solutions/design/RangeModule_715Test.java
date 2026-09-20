package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Test;

/**
 * Stateful contract tests for {@link RangeModule_715}.
 *
 * <p>The small-range tests use a boolean cell model rather than another interval tree. Since all
 * endpoints are integers, tracking the cells {@code [x, x + 1)} independently is equivalent to
 * tracking every real number in an integer-endpoint range. This keeps the expected result
 * independent from the production {@code TreeMap} representation.</p>
 */
public class RangeModule_715Test {

    @Test
    public void officialExamplePreservesHalfOpenBoundaries() {
        RangeModule_715 module = new RangeModule_715();

        module.addRange(10, 20);
        module.removeRange(14, 16);

        assertTrue(module.queryRange(10, 14));
        assertFalse(module.queryRange(13, 15));
        assertTrue(module.queryRange(16, 17));
    }

    @Test
    public void emptyModuleHasNoTrackedPositiveRange() {
        RangeModule_715 module = new RangeModule_715();

        assertFalse(module.queryRange(1, 2));
        assertFalse(module.queryRange(1, 1_000_000_000));
    }

    @Test
    public void exactAndContainedQueriesAreCovered() {
        RangeModule_715 module = new RangeModule_715();
        module.addRange(10, 100);

        assertTrue(module.queryRange(10, 100));
        assertTrue(module.queryRange(10, 11));
        assertTrue(module.queryRange(50, 51));
        assertTrue(module.queryRange(99, 100));
    }

    @Test
    public void queryMustRejectEitherSideOfAnInterval() {
        RangeModule_715 module = new RangeModule_715();
        module.addRange(10, 20);

        assertFalse(module.queryRange(9, 20));
        assertFalse(module.queryRange(10, 21));
        assertFalse(module.queryRange(9, 21));
        assertFalse(module.queryRange(5, 10));
    }

    @Test
    public void queryRejectsAnUntrackedGapBetweenIntervals() {
        RangeModule_715 module = new RangeModule_715();
        module.addRange(1, 5);
        module.addRange(8, 12);

        assertTrue(module.queryRange(1, 5));
        assertTrue(module.queryRange(8, 12));
        assertFalse(module.queryRange(1, 12));
        assertFalse(module.queryRange(4, 9));
        assertFalse(module.queryRange(5, 8));
    }

    @Test
    public void overlappingAddsMergeOnBothSides() {
        RangeModule_715 module = new RangeModule_715();
        module.addRange(10, 20);
        module.addRange(5, 15);
        module.addRange(15, 25);

        assertTrue(module.queryRange(5, 25));
        assertTrue(module.queryRange(10, 20));
    }

    @Test
    public void anEnclosingOrAlreadyCoveredAddDoesNotLoseCoverage() {
        RangeModule_715 module = new RangeModule_715();
        module.addRange(10, 20);
        module.addRange(12, 18);
        module.addRange(5, 25);
        module.addRange(6, 24);

        assertTrue(module.queryRange(5, 25));
        assertFalse(module.queryRange(4, 5));
        assertFalse(module.queryRange(25, 26));
    }

    @Test
    public void adjacentAddsFormOneContinuousTrackedRange() {
        RangeModule_715 module = new RangeModule_715();
        module.addRange(1, 5);
        module.addRange(5, 10);
        module.addRange(10, 15);

        assertTrue(module.queryRange(1, 15));
        assertTrue(module.queryRange(4, 11));
    }

    @Test
    public void disjointAddsRemainIndependent() {
        RangeModule_715 module = new RangeModule_715();
        module.addRange(1, 3);
        module.addRange(10, 12);
        module.addRange(20, 22);

        assertTrue(module.queryRange(1, 3));
        assertTrue(module.queryRange(10, 12));
        assertTrue(module.queryRange(20, 22));
        assertFalse(module.queryRange(3, 10));
        assertFalse(module.queryRange(1, 22));
    }

    @Test
    public void removingTheMiddleSplitsAnExistingRange() {
        RangeModule_715 module = new RangeModule_715();
        module.addRange(1, 100);
        module.removeRange(40, 60);

        assertTrue(module.queryRange(1, 40));
        assertTrue(module.queryRange(60, 100));
        assertFalse(module.queryRange(40, 41));
        assertFalse(module.queryRange(39, 61));
    }

    @Test
    public void removingAFrontOrBackTrimPreservesTheRemainder() {
        RangeModule_715 module = new RangeModule_715();
        module.addRange(10, 30);
        module.removeRange(10, 15);
        module.removeRange(25, 30);

        assertTrue(module.queryRange(15, 25));
        assertFalse(module.queryRange(10, 15));
        assertFalse(module.queryRange(25, 30));
    }

    @Test
    public void removingSeveralRangesDeletesOnlyTheOverlappingParts() {
        RangeModule_715 module = new RangeModule_715();
        module.addRange(0, 5);
        module.addRange(10, 15);
        module.addRange(20, 25);
        module.removeRange(3, 22);

        assertTrue(module.queryRange(0, 3));
        assertTrue(module.queryRange(22, 25));
        assertFalse(module.queryRange(3, 22));
        assertFalse(module.queryRange(10, 15));
    }

    @Test
    public void removingAnUntrackedRangeIsANoop() {
        RangeModule_715 module = new RangeModule_715();
        module.addRange(20, 30);
        module.removeRange(1, 10);
        module.removeRange(40, 50);

        assertTrue(module.queryRange(20, 30));
        assertFalse(module.queryRange(1, 10));
    }

    @Test
    public void removalExactlyAtBoundariesRespectsHalfOpenSemantics() {
        RangeModule_715 module = new RangeModule_715();
        module.addRange(0, 10);
        module.addRange(20, 30);
        module.removeRange(10, 20);

        assertTrue(module.queryRange(0, 10));
        assertTrue(module.queryRange(20, 30));
        assertFalse(module.queryRange(10, 20));
    }

    @Test
    public void addingAfterRemovalCanRestoreOnlyTheRequestedGap() {
        RangeModule_715 module = new RangeModule_715();
        module.addRange(1, 20);
        module.removeRange(5, 15);
        module.addRange(8, 12);

        assertTrue(module.queryRange(1, 5));
        assertTrue(module.queryRange(8, 12));
        assertTrue(module.queryRange(15, 20));
        assertFalse(module.queryRange(5, 8));
        assertFalse(module.queryRange(12, 15));
    }

    @Test
    public void repeatedAddsAndRemovesAreIdempotent() {
        RangeModule_715 module = new RangeModule_715();
        module.addRange(2, 8);
        module.addRange(2, 8);
        module.removeRange(4, 6);
        module.removeRange(4, 6);

        assertTrue(module.queryRange(2, 4));
        assertTrue(module.queryRange(6, 8));
        assertFalse(module.queryRange(4, 6));
    }

    @Test
    public void separateInstancesDoNotShareState() {
        RangeModule_715 first = new RangeModule_715();
        RangeModule_715 second = new RangeModule_715();
        first.addRange(1, 10);

        assertTrue(first.queryRange(1, 10));
        assertFalse(second.queryRange(1, 10));
        second.addRange(20, 30);
        first.removeRange(1, 10);
        assertFalse(first.queryRange(1, 10));
        assertTrue(second.queryRange(20, 30));
    }

    @Test
    public void documentedReversedAddAndRemoveCallsAreNoops() {
        RangeModule_715 module = new RangeModule_715();
        module.addRange(10, 5);
        module.removeRange(10, 5);
        assertFalse(module.queryRange(5, 10));

        module.addRange(1, 20);
        module.removeRange(15, 5);
        assertTrue(module.queryRange(1, 20));
    }

    @Test
    public void negativeCoordinatesAreSupportedByTheJavaImplementation() {
        RangeModule_715 module = new RangeModule_715();
        module.addRange(-100, -10);
        module.addRange(-10, 0);

        assertTrue(module.queryRange(-100, 0));
        assertTrue(module.queryRange(-1, 0));
        assertFalse(module.queryRange(-101, -100));
    }

    @Test
    public void fullIntegerEndpointRangeDoesNotOverflow() {
        RangeModule_715 module = new RangeModule_715();
        module.addRange(Integer.MIN_VALUE, Integer.MAX_VALUE);
        module.removeRange(-1, 1);

        assertTrue(module.queryRange(Integer.MIN_VALUE, -1));
        assertFalse(module.queryRange(-1, 1));
        assertTrue(module.queryRange(1, Integer.MAX_VALUE));
    }

    @Test
    public void maximumLeetCodeEndpointAndTinyRangesAreHandled() {
        RangeModule_715 module = new RangeModule_715();
        module.addRange(1, 1_000_000_000);
        module.removeRange(500_000_000, 500_000_001);

        assertTrue(module.queryRange(1, 2));
        assertTrue(module.queryRange(999_999_999, 1_000_000_000));
        assertFalse(module.queryRange(500_000_000, 500_000_001));
        assertFalse(module.queryRange(1, 1_000_000_000));
    }

    @Test
    public void exactlyTenThousandLegalCallsRemainCorrect() {
        RangeModule_715 module = new RangeModule_715();
        for (int i = 0; i < 5_000; i++) {
            int left = 2 * i + 1;
            module.addRange(left, left + 1);
        }
        for (int i = 0; i < 5_000; i++) {
            int left = 2 * i + 1;
            assertTrue(module.queryRange(left, left + 1));
        }
    }

    @Test
    public void exhaustiveSmallRangesMatchAnIndependentCellModel() {
        RangeModule_715 module = new RangeModule_715();
        BooleanRangeOracle oracle = new BooleanRangeOracle(9);

        for (int left = 0; left < 9; left++) {
            for (int right = left + 1; right <= 9; right++) {
                module.addRange(left, right);
                oracle.add(left, right);
            }
        }
        for (int left = 0; left < 9; left++) {
            for (int right = left + 1; right <= 9; right++) {
                assertEquals(oracle.query(left, right), module.queryRange(left, right),
                        "after adding every small range: [" + left + "," + right + ")");
            }
        }
        for (int left = 0; left < 9; left += 2) {
            int right = Math.min(9, left + 2);
            module.removeRange(left, right);
            oracle.remove(left, right);
        }
        assertOracleMatches(module, oracle, 9);
    }

    @Test
    public void seededStatefulOperationsMatchAnIndependentCellModel() {
        RangeModule_715 module = new RangeModule_715();
        BooleanRangeOracle oracle = new BooleanRangeOracle(80);
        Random random = new Random(0x715C0DEL);

        for (int operation = 0; operation < 10_000; operation++) {
            int left = random.nextInt(79);
            int right = left + 1 + random.nextInt(80 - left);
            switch (random.nextInt(3)) {
                case 0 -> {
                    module.addRange(left, right);
                    oracle.add(left, right);
                }
                case 1 -> {
                    module.removeRange(left, right);
                    oracle.remove(left, right);
                }
                default -> assertEquals(oracle.query(left, right), module.queryRange(left, right),
                        "operation " + operation + " query [" + left + "," + right + ")");
            }
        }
        assertOracleMatches(module, oracle, 80);
    }

    private void assertOracleMatches(RangeModule_715 module, BooleanRangeOracle oracle, int limit) {
        for (int left = 0; left < limit; left++) {
            for (int right = left + 1; right <= limit; right++) {
                assertEquals(oracle.query(left, right), module.queryRange(left, right),
                        "final query [" + left + "," + right + ")");
            }
        }
    }

    /** A deliberately simple reference model: one flag per unit interval. */
    private static final class BooleanRangeOracle {
        private final boolean[] tracked;

        private BooleanRangeOracle(int endpointLimit) {
            tracked = new boolean[endpointLimit];
        }

        private void add(int left, int right) {
            for (int coordinate = left; coordinate < right; coordinate++) {
                tracked[coordinate] = true;
            }
        }

        private void remove(int left, int right) {
            for (int coordinate = left; coordinate < right; coordinate++) {
                tracked[coordinate] = false;
            }
        }

        private boolean query(int left, int right) {
            for (int coordinate = left; coordinate < right; coordinate++) {
                if (!tracked[coordinate]) {
                    return false;
                }
            }
            return true;
        }
    }
}
