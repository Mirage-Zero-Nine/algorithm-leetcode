package solutions.design;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;

/** Tests the stateful stream contract for {@link KthLargest_703}. */
public class KthLargest_703Test {

    @Test
    public void officialExampleOne() {
        assertSequence(3, new int[]{4, 5, 8, 2}, new int[]{3, 5, 10, 9, 4},
                new int[]{4, 5, 5, 8, 8});
    }

    @Test
    public void officialExampleTwoKeepsDuplicateRanks() {
        assertSequence(4, new int[]{7, 7, 7, 7, 8, 3}, new int[]{2, 10, 9, 9},
                new int[]{7, 7, 7, 8});
    }

    @Test
    public void emptyInitialStreamWithKOne() {
        assertSequence(1, new int[]{}, new int[]{-4, -9, 0, 6},
                new int[]{-4, -4, 0, 6});
    }

    @Test
    public void initialStreamMayContainOneFewerValueThanK() {
        assertSequence(3, new int[]{10, 20}, new int[]{5, 30, 1},
                new int[]{5, 10, 10});
    }

    @Test
    public void initialStreamExactlyKValues() {
        assertSequence(3, new int[]{1, 2, 3}, new int[]{0, 4, 2},
                new int[]{1, 2, 2});
    }

    @Test
    public void initialStreamContainsMoreThanKValues() {
        assertSequence(2, new int[]{-5, 100, 4, 8, 9}, new int[]{7, 101, -100},
                new int[]{9, 100, 100});
    }

    @Test
    public void kOneIsTheRunningMaximum() {
        assertSequence(1, new int[]{5}, new int[]{3, 6, 6, Integer.MIN_VALUE, Integer.MAX_VALUE},
                new int[]{5, 6, 6, 6, Integer.MAX_VALUE});
    }

    @Test
    public void allDuplicateValuesRemainTheSameRank() {
        assertSequence(2, new int[]{5, 5, 5}, new int[]{5, 4, 6, 5, 5},
                new int[]{5, 5, 5, 5, 5});
    }

    @Test
    public void negativeValuesAreOrderedByValueNotMagnitude() {
        assertSequence(2, new int[]{-3, -2, -1}, new int[]{-4, 0, -2, -10},
                new int[]{-2, -1, -1, -1});
    }

    @Test
    public void fullJavaIntegerRangeDoesNotOverflow() {
        assertSequence(3, new int[]{Integer.MIN_VALUE, 0, Integer.MAX_VALUE},
                new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, -1, 1},
                new int[]{Integer.MIN_VALUE, 0, 0, 1});
    }

    @Test
    public void ascendingAddsReplaceTheKthThreshold() {
        assertSequence(4, new int[]{1, 2, 3, 4}, new int[]{5, 6, 7, 8},
                new int[]{2, 3, 4, 5});
    }

    @Test
    public void descendingAddsBelowTheThresholdDoNotChangeIt() {
        assertSequence(3, new int[]{10, 20, 30, 40}, new int[]{9, 8, 7, 6},
                new int[]{20, 20, 20, 20});
    }

    @Test
    public void valuesEqualToTheThresholdAreRetained() {
        assertSequence(3, new int[]{1, 2, 3}, new int[]{2, 3, 2, 4},
                new int[]{2, 2, 2, 3});
    }

    @Test
    public void repeatedAddsUseAllEarlierValues() {
        KthLargest_703 stream = new KthLargest_703(3, new int[]{4, 1, 7});
        assertEquals(2, stream.add(2));
        assertEquals(4, stream.add(6));
        assertEquals(6, stream.add(8));
        assertEquals(6, stream.add(0));
        assertEquals(6, stream.add(5));
    }

    @Test
    public void eachNewInstanceStartsWithIndependentState() {
        KthLargest_703 first = new KthLargest_703(2, new int[]{1, 10});
        KthLargest_703 second = new KthLargest_703(2, new int[]{1, 10});

        assertEquals(10, first.add(100));
        assertEquals(2, second.add(2));
        assertEquals(10, first.add(-100));
        assertEquals(3, second.add(3));
    }

    @Test
    public void constructorDoesNotMutateTheInitialArray() {
        int[] nums = {9, -2, 9, 4, 0};
        int[] original = nums.clone();
        KthLargest_703 stream = new KthLargest_703(3, nums);

        assertEquals(5, stream.add(5));
        assertArrayEquals(original, nums);
    }

    @Test
    public void orderingOfInitialInputDoesNotMatter() {
        int[] ascending = {-8, -1, 0, 3, 3, 11};
        int[] descending = {11, 3, 3, 0, -1, -8};
        int[] additions = {-9, 12, 3, 2};

        assertAgainstOracle(3, ascending, additions);
        assertAgainstOracle(3, descending, additions);
    }

    @Test
    public void seededRandomStreamsMatchAnIndependentMultisetOracle() {
        Random random = new Random(703_2026L);
        for (int scenario = 0; scenario < 40; scenario++) {
            int k = 1 + random.nextInt(20);
            int initialLength = k - 1 + random.nextInt(31);
            int[] initial = randomValues(random, initialLength);
            int[] additions = randomValues(random, 80);
            assertAgainstOracle(k, initial, additions);
        }
    }

    @Test
    public void seededSmallValuesExerciseManyDuplicateTransitions() {
        Random random = new Random(17);
        for (int scenario = 0; scenario < 100; scenario++) {
            int k = 1 + random.nextInt(8);
            int[] initial = new int[k - 1 + random.nextInt(8)];
            int[] additions = new int[25];
            for (int i = 0; i < initial.length; i++) {
                initial[i] = random.nextInt(7) - 3;
            }
            for (int i = 0; i < additions.length; i++) {
                additions[i] = random.nextInt(7) - 3;
            }
            assertAgainstOracle(k, initial, additions);
        }
    }

    @Test
    public void maximumInitialLengthIsHandled() {
        int[] nums = new int[10_000];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i - 5_000;
        }
        assertSequence(5_000, nums, new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, 0},
                new int[]{0, 1, 1});
    }

    @Test
    public void maximumKIsHandledWhenTheFirstAddCompletesTheRank() {
        int[] nums = new int[9_999];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i;
        }
        assertSequence(10_000, nums, new int[]{-1, 10_000}, new int[]{-1, 0});
    }

    @Test
    public void maximumNumberOfAddCallsMaintainsTheMaximum() {
        int[] additions = new int[10_000];
        int expected = Integer.MIN_VALUE;
        int[] expectedResults = new int[additions.length];
        for (int i = 0; i < additions.length; i++) {
            additions[i] = (i % 2 == 0) ? i - 5_000 : 5_000 - i;
            expected = Math.max(expected, additions[i]);
            expectedResults[i] = expected;
        }
        assertSequence(1, new int[]{}, additions, expectedResults);
    }

    @Test
    public void largeStateWithSmallKUsesAnIndependentOracle() {
        Random random = new Random(703);
        int[] initial = randomValues(random, 10_000);
        int[] additions = randomValues(random, 1_000);
        assertAgainstOracle(7, initial, additions);
    }

    @Test
    public void aFreshCallSequenceCanBeReplayedExactly() {
        int[] initial = {12, -4, 12, 8, 0};
        int[] additions = {7, 15, -20, 12, 9};

        KthLargest_703 first = new KthLargest_703(3, initial);
        KthLargest_703 replay = new KthLargest_703(3, initial);
        for (int value : additions) {
            assertEquals(first.add(value), replay.add(value));
        }
    }

    @Test
    public void largeAndSmallValuesCanAlternateAcrossThreshold() {
        assertSequence(4, new int[]{-100, -50, 0, 50, 100},
                new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 49, 51, -49, 75},
                new int[]{0, 0, 49, 50, 50, 51});
    }

    @Test
    public void initialFewerThanKValuesWorksForTheMaximumValidGap() {
        assertSequence(5, new int[]{-3, -2, -1, 0}, new int[]{-4, 10, 9},
                new int[]{-4, -3, -2});
    }

    @Test
    public void oneElementStreamTracksEverySubsequentValue() {
        assertSequence(1, new int[]{Integer.MIN_VALUE},
                new int[]{Integer.MIN_VALUE, -1, 0, Integer.MAX_VALUE, 42},
                new int[]{Integer.MIN_VALUE, -1, 0, Integer.MAX_VALUE, Integer.MAX_VALUE});
    }

    private static void assertSequence(int k, int[] initial, int[] additions, int[] expected) {
        KthLargest_703 stream = new KthLargest_703(k, initial.clone());
        assertEquals(expected.length, additions.length);
        for (int i = 0; i < additions.length; i++) {
            assertEquals(expected[i], stream.add(additions[i]), "after add index " + i);
        }
    }

    private static void assertAgainstOracle(int k, int[] initial, int[] additions) {
        KthLargest_703 stream = new KthLargest_703(k, initial.clone());
        TreeMap<Integer, Integer> counts = new TreeMap<>();
        for (int value : initial) {
            counts.merge(value, 1, Integer::sum);
        }
        for (int i = 0; i < additions.length; i++) {
            int value = additions[i];
            counts.merge(value, 1, Integer::sum);
            int expected = kthLargest(counts, k);
            assertEquals(expected, stream.add(value), "after add index " + i);
        }
    }

    private static int kthLargest(TreeMap<Integer, Integer> counts, int k) {
        int remaining = k;
        for (Map.Entry<Integer, Integer> entry : counts.descendingMap().entrySet()) {
            remaining -= entry.getValue();
            if (remaining <= 0) {
                return entry.getKey();
            }
        }
        throw new AssertionError("oracle requested a rank that does not exist");
    }

    private static int[] randomValues(Random random, int length) {
        int[] values = new int[length];
        for (int i = 0; i < length; i++) {
            values[i] = random.nextInt(20_001) - 10_000;
        }
        return values;
    }
}
