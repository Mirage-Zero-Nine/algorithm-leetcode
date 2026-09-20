package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.junit.jupiter.api.Test;

/**
 * Stateful contract tests for {@link FreqStack_895}.
 *
 * <p>The model deliberately scans an independent list from the top on every pop. This makes the
 * expected value independent from the production frequency-bucket representation.
 */
public class FreqStack_895Test {

    @Test
    public void followsOfficialExample() {
        FreqStack_895 stack = new FreqStack_895();
        stack.push(5);
        stack.push(7);
        stack.push(5);
        stack.push(7);
        stack.push(4);
        stack.push(5);

        assertEquals(5, stack.pop());
        assertEquals(7, stack.pop());
        assertEquals(5, stack.pop());
        assertEquals(4, stack.pop());
    }

    @Test
    public void popsTheOnlyElementAndSupportsReuseAfterDrain() {
        FreqStack_895 stack = new FreqStack_895();
        stack.push(1);
        assertEquals(1, stack.pop());

        stack.push(2);
        stack.push(2);
        assertEquals(2, stack.pop());
        assertEquals(2, stack.pop());
    }

    @Test
    public void breaksFrequencyOneTiesByMostRecentPush() {
        FreqStack_895 stack = new FreqStack_895();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }

    @Test
    public void returnsRepeatedValueUntilItsFrequencyIsExhausted() {
        FreqStack_895 stack = new FreqStack_895();
        stack.push(3);
        stack.push(3);
        stack.push(3);

        assertEquals(3, stack.pop());
        assertEquals(3, stack.pop());
        assertEquals(3, stack.pop());
    }

    @Test
    public void choosesHigherFrequencyOverMoreRecentLowerFrequency() {
        FreqStack_895 stack = new FreqStack_895();
        stack.push(1);
        stack.push(1);
        stack.push(2);

        assertEquals(1, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }

    @Test
    public void recencyTieIsBasedOnThePushThatCreatedTheTiedFrequency() {
        FreqStack_895 stack = new FreqStack_895();
        stack.push(1);
        stack.push(2);
        stack.push(1);
        stack.push(2);
        stack.push(1);

        assertEquals(1, stack.pop()); // frequency three wins
        assertEquals(2, stack.pop()); // both are now frequency two; 2 reached it later
        assertEquals(1, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }

    @Test
    public void aPopCanLowerTheWinnerAndChangeTheNextWinner() {
        FreqStack_895 stack = new FreqStack_895();
        stack.push(10);
        stack.push(20);
        stack.push(10);
        stack.push(20);
        stack.push(10);

        assertEquals(10, stack.pop()); // 10:2, 20:2
        assertEquals(20, stack.pop()); // 20's frequency-two entry is newer
        stack.push(20);                // 10:2, 20:2; this push is newest
        assertEquals(20, stack.pop());
        assertEquals(10, stack.pop());
        assertEquals(20, stack.pop());
        assertEquals(10, stack.pop());
    }

    @Test
    public void interleavedPushesAndPopsPreserveCurrentFrequencies() {
        FreqStack_895 stack = new FreqStack_895();
        stack.push(10);
        stack.push(20);
        stack.push(10);
        assertEquals(10, stack.pop());
        stack.push(20);
        assertEquals(20, stack.pop());
        assertEquals(20, stack.pop());
        assertEquals(10, stack.pop());
    }

    @Test
    public void zeroIsAnOrdinaryValue() {
        FreqStack_895 stack = new FreqStack_895();
        stack.push(0);
        stack.push(-1);
        stack.push(0);
        stack.push(-1);

        assertEquals(-1, stack.pop());
        assertEquals(0, stack.pop());
        assertEquals(-1, stack.pop());
        assertEquals(0, stack.pop());
    }

    @Test
    public void supportsNegativeValuesAndIntegerBoundaries() {
        int[] values = {Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, -7,
            Integer.MAX_VALUE, Integer.MIN_VALUE};
        assertMatchesModel(values);
    }

    @Test
    public void supportsLeetCodeValueBounds() {
        int[] values = {0, 1_000_000_000, 0, 1_000_000_000, 1_000_000_000, 0};
        assertMatchesModel(values);
    }

    @Test
    public void manyDistinctValuesPopInReverseOrder() {
        FreqStack_895 stack = new FreqStack_895();
        for (int value = -250; value <= 250; value++) {
            stack.push(value);
        }
        for (int value = 250; value >= -250; value--) {
            assertEquals(value, stack.pop());
        }
    }

    @Test
    public void duplicateRunsDoNotLoseOlderFrequencyLevels() {
        int[] values = {4, 4, 4, 9, 9, 2, 4, 9, 2, 2, 2, 9, 4};
        assertMatchesModel(values);
    }

    @Test
    public void seededOracleSequenceUsesDuplicatesAndNegativeValues() {
        assertRandomSequenceMatchesModel(895_001, 1_500);
    }

    @Test
    public void secondSeededOracleSequenceUsesBoundaryValues() {
        assertRandomSequenceMatchesModel(895_002, 2_000);
    }

    @Test
    public void thirdSeededOracleSequenceExercisesFrequentStateChanges() {
        assertRandomSequenceMatchesModel(895_003, 3_000);
    }

    @Test
    public void twoInstancesDoNotShareFrequencyOrRecencyState() {
        FreqStack_895 first = new FreqStack_895();
        FreqStack_895 second = new FreqStack_895();
        first.push(8);
        first.push(8);
        second.push(9);
        second.push(10);
        second.push(9);

        assertEquals(8, first.pop());
        assertEquals(9, second.pop());
        assertEquals(8, first.pop());
        assertEquals(10, second.pop());
        assertEquals(9, second.pop());
    }

    @Test
    public void repeatedCallsOnOneInstanceStartFromTheCurrentState() {
        FreqStack_895 stack = new FreqStack_895();
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.pop());
        stack.push(3);
        stack.push(1);
        stack.push(3);

        assertEquals(3, stack.pop());
        assertEquals(1, stack.pop());
        assertEquals(3, stack.pop());
        assertEquals(1, stack.pop());
    }

    @Test
    public void oracleCoversEveryPopForACompactExhaustiveAlphabet() {
        for (int seed = 0; seed < 40; seed++) {
            int[] values = new int[30];
            Random random = new Random(seed);
            for (int i = 0; i < values.length; i++) {
                values[i] = random.nextInt(7) - 3;
            }
            assertMatchesModel(values);
        }
    }

    @Test
    public void frequencyLevelsRemainCorrectWhenTheWinnerAlternates() {
        int[] values = {1, 2, 3, 1, 2, 3, 1, 2, 4, 4, 3, 4, 2, 1, 4, 3};
        assertMatchesModel(values);
    }

    @Test
    public void largeFrequencyGapDoesNotCausePrematureTie() {
        FreqStack_895 stack = new FreqStack_895();
        for (int i = 0; i < 12; i++) {
            stack.push(-5);
        }
        for (int i = 0; i < 11; i++) {
            stack.push(6);
        }
        assertEquals(-5, stack.pop());
        assertEquals(6, stack.pop());
        assertEquals(-5, stack.pop());
    }

    @Test
    public void exactMaximumDocumentedOperationCount() {
        FreqStack_895 stack = new FreqStack_895();
        // 10,000 pushes followed by 10,000 valid pops is the 20,000-call contract boundary.
        for (int round = 0; round < 100; round++) {
            for (int value = 0; value < 100; value++) {
                stack.push(value);
            }
        }
        for (int frequency = 100; frequency >= 1; frequency--) {
            for (int value = 99; value >= 0; value--) {
                assertEquals(value, stack.pop());
            }
        }
    }

    @Test
    public void longMixedWorkloadMatchesAnIndependentModel() {
        FreqStack_895 actual = new FreqStack_895();
        Model expected = new Model();
        Random random = new Random(895_004);
        for (int operation = 0; operation < 10_000; operation++) {
            int value = switch (random.nextInt(8)) {
                case 0 -> Integer.MIN_VALUE;
                case 1 -> Integer.MAX_VALUE;
                case 2 -> -1;
                case 3 -> 0;
                case 4 -> 1_000_000_000;
                default -> random.nextInt(13) - 6;
            };
            actual.push(value);
            expected.push(value);
            if ((operation & 1) == 1) {
                assertEquals(expected.pop(), actual.pop());
            }
        }
        while (!expected.isEmpty()) {
            assertEquals(expected.pop(), actual.pop());
        }
    }

    private static void assertMatchesModel(int[] values) {
        FreqStack_895 actual = new FreqStack_895();
        Model expected = new Model();
        for (int value : values) {
            actual.push(value);
            expected.push(value);
        }
        while (!expected.isEmpty()) {
            assertEquals(expected.pop(), actual.pop());
        }
    }

    private static void assertRandomSequenceMatchesModel(int seed, int operations) {
        FreqStack_895 actual = new FreqStack_895();
        Model expected = new Model();
        Random random = new Random(seed);
        for (int operation = 0; operation < operations; operation++) {
            boolean push = expected.isEmpty() || random.nextBoolean();
            if (push) {
                int value = switch (random.nextInt(8)) {
                    case 0 -> Integer.MIN_VALUE;
                    case 1 -> Integer.MAX_VALUE;
                    case 2 -> -1_000_000_000;
                    case 3 -> 1_000_000_000;
                    default -> random.nextInt(11) - 5;
                };
                actual.push(value);
                expected.push(value);
            } else {
                assertEquals(expected.pop(), actual.pop());
            }
        }
        while (!expected.isEmpty()) {
            assertEquals(expected.pop(), actual.pop());
        }
    }

    private static final class Model {
        private final List<Integer> values = new ArrayList<>();
        private final Map<Integer, Integer> frequencies = new HashMap<>();

        private void push(int value) {
            values.add(value);
            frequencies.merge(value, 1, Integer::sum);
        }

        private int pop() {
            int maximumFrequency = 0;
            for (int frequency : frequencies.values()) {
                maximumFrequency = Math.max(maximumFrequency, frequency);
            }
            for (int index = values.size() - 1; index >= 0; index--) {
                int value = values.get(index);
                if (frequencies.get(value) == maximumFrequency) {
                    values.remove(index);
                    int remaining = frequencies.get(value) - 1;
                    if (remaining == 0) {
                        frequencies.remove(value);
                    } else {
                        frequencies.put(value, remaining);
                    }
                    return value;
                }
            }
            throw new IllegalStateException("pop() requires a nonempty stack");
        }

        private boolean isEmpty() {
            return values.isEmpty();
        }
    }
}
