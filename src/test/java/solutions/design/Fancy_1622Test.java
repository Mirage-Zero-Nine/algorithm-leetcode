package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;

/**
 * Stateful contract tests for {@link Fancy_1622}.
 *
 * <p>The reference sequence in these tests applies each operation eagerly modulo the problem
 * modulus. It is deliberately independent of the affine/inverse representation used by the
 * implementation, so the tests exercise both operation ordering and modular arithmetic.</p>
 */
public class Fancy_1622Test {

    private static final long MOD = 1_000_000_007L;

    @Test
    public void emptySequenceReturnsMinusOneForEveryOutOfRangeIndex() {
        Fancy_1622 fancy = new Fancy_1622();

        assertEquals(-1, fancy.getIndex(0));
        assertEquals(-1, fancy.getIndex(1));
        assertEquals(-1, fancy.getIndex(100_000));
        fancy.addAll(100);
        fancy.multAll(100);
        assertEquals(-1, fancy.getIndex(0));
    }

    @Test
    public void officialExample() {
        Fancy_1622 fancy = new Fancy_1622();
        fancy.append(2);
        fancy.addAll(3);
        fancy.append(7);
        fancy.multAll(2);
        assertEquals(10, fancy.getIndex(0));
        fancy.addAll(3);
        fancy.append(10);
        fancy.multAll(2);
        assertEquals(26, fancy.getIndex(0));
        assertEquals(34, fancy.getIndex(1));
        assertEquals(20, fancy.getIndex(2));
    }

    @Test
    public void appendingValuesKeepsTheirOrderAndValues() {
        Fancy_1622 fancy = new Fancy_1622();
        int[] values = {1, 2, 50, 99, 100};
        for (int value : values) {
            fancy.append(value);
        }

        for (int i = 0; i < values.length; i++) {
            assertEquals(values[i], fancy.getIndex(i));
        }
    }

    @Test
    public void addAllBeforeAnyAppendDoesNotAffectFutureValues() {
        Fancy_1622 fancy = new Fancy_1622();
        fancy.addAll(37);
        fancy.addAll(63);
        fancy.append(11);

        assertEquals(11, fancy.getIndex(0));
    }

    @Test
    public void multAllBeforeAnyAppendDoesNotAffectFutureValues() {
        Fancy_1622 fancy = new Fancy_1622();
        fancy.multAll(7);
        fancy.multAll(13);
        fancy.append(11);

        assertEquals(11, fancy.getIndex(0));
    }

    @Test
    public void addAllUpdatesEveryExistingElementButNotFutureAppends() {
        Fancy_1622 fancy = new Fancy_1622();
        fancy.append(1);
        fancy.append(100);
        fancy.addAll(23);
        fancy.append(7);

        assertEquals(24, fancy.getIndex(0));
        assertEquals(123, fancy.getIndex(1));
        assertEquals(7, fancy.getIndex(2));
    }

    @Test
    public void multAllUpdatesEveryExistingElementButNotFutureAppends() {
        Fancy_1622 fancy = new Fancy_1622();
        fancy.append(3);
        fancy.append(4);
        fancy.multAll(25);
        fancy.append(6);

        assertEquals(75, fancy.getIndex(0));
        assertEquals(100, fancy.getIndex(1));
        assertEquals(6, fancy.getIndex(2));
    }

    @Test
    public void repeatedAddsAndMultiplicationsFollowOperationOrder() {
        Fancy_1622 fancy = new Fancy_1622();
        fancy.append(5);
        fancy.addAll(3);
        fancy.multAll(4);
        fancy.addAll(7);
        fancy.multAll(2);

        assertEquals(78, fancy.getIndex(0));
    }

    @Test
    public void appendsBetweenTransformsPreserveDifferentHistories() {
        Fancy_1622 fancy = new Fancy_1622();
        fancy.append(2);
        fancy.addAll(5);
        fancy.append(3);
        fancy.multAll(4);
        fancy.append(6);
        fancy.addAll(1);

        assertEquals(29, fancy.getIndex(0));
        assertEquals(13, fancy.getIndex(1));
        assertEquals(7, fancy.getIndex(2));
    }

    @Test
    public void zeroMultiplierZerosExistingValuesAndSubsequentAddition() {
        // m is positive in the LeetCode constraints; this additionally exercises the class's
        // integer-valued API for the natural zero-multiplier edge on already stored values.
        Fancy_1622 fancy = new Fancy_1622();
        fancy.append(5);
        fancy.append(100);
        fancy.multAll(0);
        assertEquals(0, fancy.getIndex(0));
        assertEquals(0, fancy.getIndex(1));
        fancy.addAll(17);
        assertEquals(17, fancy.getIndex(0));
        assertEquals(17, fancy.getIndex(1));
    }

    @Test
    public void moduloWrapsAdditionAtTheModulusBoundary() {
        Fancy_1622 fancy = new Fancy_1622();
        fancy.append(1_000_000_006);
        fancy.addAll(2);

        assertEquals(1, fancy.getIndex(0));
    }

    @Test
    public void moduloWrapsMultiplicationAtTheModulusBoundary() {
        Fancy_1622 fancy = new Fancy_1622();
        fancy.append(100);
        fancy.multAll(100);
        fancy.multAll(100);
        fancy.addAll(100);

        assertEquals((int) ((100L * 100 * 100 + 100) % MOD), fancy.getIndex(0));
    }

    @Test
    public void largeValuesRemainReducedAfterManyTransforms() {
        Fancy_1622 fancy = new Fancy_1622();
        List<Long> expected = new ArrayList<>();
        for (int value : new int[] {100, 99, 98, 1}) {
            fancy.append(value);
            expected.add((long) value);
        }
        for (int i = 0; i < 60; i++) {
            int increment = (i % 100) + 1;
            int multiplier = ((i * 37) % 100) + 1;
            fancy.addAll(increment);
            fancy.multAll(multiplier);
            for (int j = 0; j < expected.size(); j++) {
                expected.set(j, (expected.get(j) + increment) % MOD);
                expected.set(j, expected.get(j) * multiplier % MOD);
            }
        }

        assertMatches(fancy, expected);
    }

    @Test
    public void outOfRangeIndicesStayMinusOneAfterTheSequenceGrows() {
        Fancy_1622 fancy = new Fancy_1622();
        fancy.append(4);
        fancy.append(8);
        fancy.addAll(12);

        assertEquals(-1, fancy.getIndex(2));
        assertEquals(-1, fancy.getIndex(10));
        assertEquals(-1, fancy.getIndex(100_000));
        assertEquals(16, fancy.getIndex(0));
        assertEquals(20, fancy.getIndex(1));
    }

    @Test
    public void duplicateValuesRemainIndependentElements() {
        Fancy_1622 fancy = new Fancy_1622();
        for (int i = 0; i < 8; i++) {
            fancy.append(42);
        }
        fancy.addAll(8);
        fancy.multAll(3);

        for (int i = 0; i < 8; i++) {
            assertEquals(150, fancy.getIndex(i));
        }
    }

    @Test
    public void maximumLegalOperationValuesWorkTogether() {
        Fancy_1622 fancy = new Fancy_1622();
        fancy.append(100);
        fancy.addAll(100);
        fancy.multAll(100);

        assertEquals(20_000, fancy.getIndex(0));
    }

    @Test
    public void repeatedReadsDoNotChangeState() {
        Fancy_1622 fancy = new Fancy_1622();
        fancy.append(9);
        fancy.addAll(14);
        fancy.multAll(6);
        int first = fancy.getIndex(0);

        assertEquals(first, fancy.getIndex(0));
        assertEquals(first, fancy.getIndex(0));
    }

    @Test
    public void queryingIndicesInDifferentOrdersDoesNotChangeState() {
        Fancy_1622 fancy = new Fancy_1622();
        for (int value = 1; value <= 6; value++) {
            fancy.append(value);
        }
        fancy.addAll(11);
        fancy.multAll(7);

        assertEquals(112, fancy.getIndex(4));
        assertEquals(91, fancy.getIndex(1));
        assertEquals(84, fancy.getIndex(0));
        assertEquals(119, fancy.getIndex(5));
        assertEquals(98, fancy.getIndex(2));
        assertEquals(105, fancy.getIndex(3));
    }

    @Test
    public void separateInstancesDoNotShareSequenceOrTransformState() {
        Fancy_1622 first = new Fancy_1622();
        Fancy_1622 second = new Fancy_1622();
        first.append(10);
        first.addAll(5);
        second.append(20);
        second.multAll(3);

        assertEquals(15, first.getIndex(0));
        assertEquals(-1, first.getIndex(1));
        assertEquals(60, second.getIndex(0));
        assertEquals(-1, second.getIndex(1));
    }

    @Test
    public void instanceCanBeReusedAfterAnEmptyRead() {
        Fancy_1622 fancy = new Fancy_1622();
        assertEquals(-1, fancy.getIndex(0));
        fancy.append(31);
        fancy.addAll(9);

        assertEquals(40, fancy.getIndex(0));
    }

    @Test
    public void seededOperationStreamsMatchAnIndependentModularListOracle() {
        long[] seeds = {0L, 1L, 7L, 42L, 1_622L, 2023L, 65_537L, 0x5EEDL};
        for (long seed : seeds) {
            Random random = new Random(seed);
            Fancy_1622 fancy = new Fancy_1622();
            List<Long> expected = new ArrayList<>();

            for (int operation = 0; operation < 400; operation++) {
                switch (random.nextInt(4)) {
                    case 0 -> {
                        int value = 1 + random.nextInt(100);
                        fancy.append(value);
                        expected.add((long) value);
                    }
                    case 1 -> {
                        int increment = 1 + random.nextInt(100);
                        fancy.addAll(increment);
                        for (int i = 0; i < expected.size(); i++) {
                            expected.set(i, (expected.get(i) + increment) % MOD);
                        }
                    }
                    case 2 -> {
                        int multiplier = 1 + random.nextInt(100);
                        fancy.multAll(multiplier);
                        for (int i = 0; i < expected.size(); i++) {
                            expected.set(i, expected.get(i) * multiplier % MOD);
                        }
                    }
                    default -> {
                        int index = random.nextInt(expected.size() + 4);
                        int actual = fancy.getIndex(index);
                        int oracle = index >= expected.size() ? -1 : expected.get(index).intValue();
                        assertEquals(oracle, actual, "seed=" + seed + ", operation=" + operation);
                    }
                }
            }
            assertMatches(fancy, expected);
        }
    }

    @Test
    public void exactMaximumCallCountWithLegalValuesMatchesOracle() {
        Fancy_1622 fancy = new Fancy_1622();
        List<Long> expected = new ArrayList<>();
        int appendCount = 99_996;
        for (int i = 0; i < appendCount; i++) {
            int value = (i % 100) + 1;
            fancy.append(value);
            expected.add((long) value);
        }
        fancy.addAll(100);
        fancy.multAll(100);
        assertEquals((int) ((expected.get(0) + 100) * 100 % MOD), fancy.getIndex(0));
        assertEquals((int) ((expected.get(appendCount - 1) + 100) * 100 % MOD),
                fancy.getIndex(appendCount - 1));
    }

    @Test
    public void longAppendAndTransformHistoryUsesEachElementCreationPoint() {
        Fancy_1622 fancy = new Fancy_1622();
        List<Long> expected = new ArrayList<>();
        for (int round = 0; round < 40; round++) {
            int value = (round % 100) + 1;
            fancy.append(value);
            expected.add((long) value);
            int increment = (round * 13) % 100 + 1;
            int multiplier = (round * 29) % 100 + 1;
            fancy.addAll(increment);
            fancy.multAll(multiplier);
            for (int i = 0; i < expected.size(); i++) {
                expected.set(i, (expected.get(i) + increment) * multiplier % MOD);
            }
        }

        assertMatches(fancy, expected);
    }

    @Test
    public void allLegalMultiplierValuesAreCheckedAgainstEagerResults() {
        Fancy_1622 fancy = new Fancy_1622();
        List<Long> expected = new ArrayList<>();
        fancy.append(1);
        fancy.append(100);
        expected.add(1L);
        expected.add(100L);
        for (int multiplier = 1; multiplier <= 100; multiplier++) {
            fancy.multAll(multiplier);
            for (int i = 0; i < expected.size(); i++) {
                expected.set(i, expected.get(i) * multiplier % MOD);
            }
        }

        assertMatches(fancy, expected);
    }

    @Test
    public void allLegalIncrementValuesAreCheckedAgainstEagerResults() {
        Fancy_1622 fancy = new Fancy_1622();
        List<Long> expected = new ArrayList<>();
        fancy.append(1);
        fancy.append(100);
        expected.add(1L);
        expected.add(100L);
        for (int increment = 1; increment <= 100; increment++) {
            fancy.addAll(increment);
            for (int i = 0; i < expected.size(); i++) {
                expected.set(i, (expected.get(i) + increment) % MOD);
            }
        }

        assertMatches(fancy, expected);
    }

    @Test
    public void modularOracleHandlesValuesThatWrapRepeatedly() {
        Fancy_1622 fancy = new Fancy_1622();
        List<Long> expected = new ArrayList<>();
        fancy.append(100);
        expected.add(100L);
        for (int i = 0; i < 25; i++) {
            fancy.multAll(100);
            fancy.addAll(100);
            expected.set(0, (expected.get(0) * 100 + 100) % MOD);
        }

        assertEquals(expected.get(0).intValue(), fancy.getIndex(0));
    }

    private void assertMatches(Fancy_1622 fancy, List<Long> expected) {
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).intValue(), fancy.getIndex(i), "index=" + i);
        }
        assertEquals(-1, fancy.getIndex(expected.size()));
    }
}
