package solutions.design;

import org.junit.jupiter.api.Test;
import util.IsolatedTestProcess;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FrequencyTracker_2671Test {
    @Test
    void testAbsentAndZeroCrossings() {
        FrequencyTracker_2671 tracker = new FrequencyTracker_2671();
        assertFalse(tracker.hasFrequency(1));
        tracker.deleteOne(7);
        assertFalse(tracker.hasFrequency(1));
        tracker.add(7);
        assertTrue(tracker.hasFrequency(1));
        tracker.deleteOne(7);
        assertFalse(tracker.hasFrequency(1));
        tracker.deleteOne(7);
        assertFalse(tracker.hasFrequency(1));
    }

    @Test
    void testSeveralValuesAndFrequencyTies() {
        FrequencyTracker_2671 tracker = new FrequencyTracker_2671();
        tracker.add(1);
        tracker.add(2);
        assertTrue(tracker.hasFrequency(1));
        assertFalse(tracker.hasFrequency(2));
        tracker.add(1);
        tracker.add(2);
        assertTrue(tracker.hasFrequency(2));
        tracker.add(3);
        assertTrue(tracker.hasFrequency(1));
        tracker.deleteOne(1);
        assertTrue(tracker.hasFrequency(1));
        assertTrue(tracker.hasFrequency(2));
    }

    @Test
    void testDeletingOneOfMultipleValuesInSameBucketKeepsBucketPresent() {
        FrequencyTracker_2671 tracker = new FrequencyTracker_2671();
        for (int value = 1; value <= 5; value++) {
            tracker.add(value);
        }
        tracker.deleteOne(3);
        assertTrue(tracker.hasFrequency(1));
        assertFalse(tracker.hasFrequency(2));
    }

    @Test
    void testIntermediateFrequencyTransitions() {
        FrequencyTracker_2671 tracker = new FrequencyTracker_2671();
        tracker.add(11);
        tracker.add(11);
        tracker.add(11);
        assertTrue(tracker.hasFrequency(3));
        assertFalse(tracker.hasFrequency(2));
        tracker.deleteOne(11);
        assertTrue(tracker.hasFrequency(2));
        assertFalse(tracker.hasFrequency(3));
        tracker.add(11);
        assertTrue(tracker.hasFrequency(3));
    }

    @Test
    void testDifferentNumbersCanOccupyDifferentFrequencyBuckets() {
        FrequencyTracker_2671 tracker = new FrequencyTracker_2671();
        for (int i = 0; i < 4; i++) tracker.add(20);
        for (int i = 0; i < 2; i++) tracker.add(30);
        tracker.add(40);
        assertTrue(tracker.hasFrequency(1));
        assertTrue(tracker.hasFrequency(2));
        assertTrue(tracker.hasFrequency(4));
        assertFalse(tracker.hasFrequency(3));
    }

    @Test
    void testMaximumNumberBoundary() {
        FrequencyTracker_2671 tracker = new FrequencyTracker_2671();
        tracker.add(100_000);
        tracker.add(100_000);
        assertTrue(tracker.hasFrequency(2));
        tracker.deleteOne(100_000);
        assertTrue(tracker.hasFrequency(1));
        assertFalse(tracker.hasFrequency(2));
    }

    @Test
    void testIndependentTrackersDoNotShareState() {
        FrequencyTracker_2671 first = new FrequencyTracker_2671();
        FrequencyTracker_2671 second = new FrequencyTracker_2671();
        first.add(5);
        first.add(5);
        second.add(9);
        assertTrue(first.hasFrequency(2));
        assertFalse(second.hasFrequency(2));
        assertTrue(second.hasFrequency(1));
    }

    @Test
    void testRepeatedCallsPreserveStateAcrossQueries() {
        FrequencyTracker_2671 tracker = new FrequencyTracker_2671();
        tracker.add(8);
        assertTrue(tracker.hasFrequency(1));
        assertTrue(tracker.hasFrequency(1));
        tracker.add(8);
        assertTrue(tracker.hasFrequency(2));
        assertFalse(tracker.hasFrequency(1));
        tracker.deleteOne(8);
        assertTrue(tracker.hasFrequency(1));
    }

    @Test
    void testLargeFrequencyBoundaryAndMaximumCallBudget() throws IOException, InterruptedException {
        IsolatedTestProcess.run(FrequencyTracker_2671Test.class,
                "largeFrequencyBoundaryAndMaximumCallBudget", 10);
    }

    void largeFrequencyBoundaryAndMaximumCallBudget() {
        FrequencyTracker_2671 tracker = new FrequencyTracker_2671();
        for (int i = 0; i < 199_998; i++) tracker.add(100_000);
        assertFalse(tracker.hasFrequency(100_000));
        assertFalse(tracker.hasFrequency(1));

        // Exactly 200,000 additions reach the final allocated frequency slot; no further query fits the call cap.
        FrequencyTracker_2671 maximumCountTracker = new FrequencyTracker_2671();
        for (int i = 0; i < 200_000; i++) maximumCountTracker.add(100_000);
    }

    @Test
    void testExhaustiveShortMutationSequencesAgainstIndependentModel() throws IOException, InterruptedException {
        IsolatedTestProcess.run(FrequencyTracker_2671Test.class,
                "exhaustiveShortMutationSequencesAgainstIndependentModel", 10);
    }

    void exhaustiveShortMutationSequencesAgainstIndependentModel() {
        // Enumerates every length-0..5 sequence over add/delete for values 1 and 2; the oracle rescans value counts.
        int[] operations = {0, 1, 2, 3};
        for (int length = 0; length <= 5; length++) {
            enumerate(new int[length], 0, operations);
        }
    }

    private void enumerate(int[] sequence, int index, int[] operations) {
        if (index < sequence.length) {
            for (int operation : operations) {
                sequence[index] = operation;
                enumerate(sequence, index + 1, operations);
            }
            return;
        }
        FrequencyTracker_2671 tracker = new FrequencyTracker_2671();
        Map<Integer, Integer> counts = new HashMap<>();
        for (int step = 0; step < sequence.length; step++) {
            int operation = sequence[step];
            int value = operation < 2 ? 1 : 2;
            if ((operation & 1) == 0) {
                tracker.add(value);
                counts.merge(value, 1, Integer::sum);
            } else {
                tracker.deleteOne(value);
                counts.computeIfPresent(value, (ignored, count) -> count == 1 ? null : count - 1);
            }
            Map<Integer, Integer> histogram = new HashMap<>();
            for (int count : counts.values()) histogram.merge(count, 1, Integer::sum);
            for (int frequency = 1; frequency <= 5; frequency++) {
                assertEquals(histogram.getOrDefault(frequency, 0) > 0, tracker.hasFrequency(frequency),
                        "sequence=" + java.util.Arrays.toString(sequence) + ", step=" + step + ", frequency=" + frequency);
            }
        }
    }

    @Test
    void testSeededLongHistoryAgainstIndependentModel() throws IOException, InterruptedException {
        IsolatedTestProcess.run(FrequencyTracker_2671Test.class,
                "seededLongHistoryAgainstIndependentModel", 10);
    }

    void seededLongHistoryAgainstIndependentModel() {
        long seed = 2671;
        Random random = new Random(seed);
        FrequencyTracker_2671 tracker = new FrequencyTracker_2671();
        Map<Integer, Integer> counts = new HashMap<>();
        for (int step = 0; step < 10_000; step++) {
            int value = random.nextInt(100) + 1;
            if (random.nextBoolean()) {
                tracker.add(value);
                counts.merge(value, 1, Integer::sum);
            } else {
                tracker.deleteOne(value);
                counts.computeIfPresent(value, (ignored, count) -> count == 1 ? null : count - 1);
            }
            int frequency = random.nextInt(20) + 1;
            boolean expected = counts.containsValue(frequency);
            assertEquals(expected, tracker.hasFrequency(frequency),
                    "seed=" + seed + ", step=" + step + ", value=" + value + ", frequency=" + frequency);
        }
    }
}
