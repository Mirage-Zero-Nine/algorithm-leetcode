package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

/**
 * Stateful contract tests for {@link SnapshotArray_1146}.
 *
 * <p>The oracle in the seeded tests stores a complete copy of the array for every snapshot. It
 * therefore validates the observable contract independently of the implementation's sparse,
 * backward-search representation.</p>
 */
public class SnapshotArray_1146Test {

    @Test
    public void shouldImplementTheOfficialExample() {
        SnapshotArray_1146 array = new SnapshotArray_1146(3);

        array.set(0, 5);
        assertEquals(0, array.snap());
        array.set(0, 6);

        assertEquals(5, array.get(0, 0));
    }

    @Test
    public void shouldInitializeEveryIndexToZero() {
        SnapshotArray_1146 array = new SnapshotArray_1146(4);

        int snapshot = array.snap();

        assertEquals(0, snapshot);
        for (int index = 0; index < 4; index++) {
            assertEquals(0, array.get(index, snapshot));
        }
    }

    @Test
    public void shouldSupportSetBeforeTheFirstSnapshot() {
        SnapshotArray_1146 array = new SnapshotArray_1146(1);

        array.set(0, 9);

        assertEquals(0, array.snap());
        assertEquals(9, array.get(0, 0));
    }

    @Test
    public void shouldKeepAnEarlierSnapshotImmutableAfterLaterSet() {
        SnapshotArray_1146 array = new SnapshotArray_1146(2);

        array.set(1, 8);
        assertEquals(0, array.snap());
        array.set(1, 13);
        assertEquals(1, array.snap());

        assertEquals(8, array.get(1, 0));
        assertEquals(13, array.get(1, 1));
    }

    @Test
    public void shouldUseTheLastSetWhenAnIndexIsSetRepeatedlyBeforeSnap() {
        SnapshotArray_1146 array = new SnapshotArray_1146(2);

        array.set(1, 4);
        array.set(1, 7);
        array.set(1, 3);

        int snapshot = array.snap();

        assertEquals(3, array.get(1, snapshot));
    }

    @Test
    public void shouldPreserveValuesAcrossSnapshotsWithoutNewSets() {
        SnapshotArray_1146 array = new SnapshotArray_1146(2);

        array.set(0, 11);
        int first = array.snap();
        int second = array.snap();
        int third = array.snap();

        assertEquals(11, array.get(0, first));
        assertEquals(11, array.get(0, second));
        assertEquals(11, array.get(0, third));
    }

    @Test
    public void shouldReturnZeroForAnUnsetIndexInEverySnapshot() {
        SnapshotArray_1146 array = new SnapshotArray_1146(4);

        array.set(1, 3);
        int first = array.snap();
        int second = array.snap();

        assertEquals(0, array.get(3, first));
        assertEquals(0, array.get(3, second));
    }

    @Test
    public void shouldCaptureSeveralIndicesInOneSnapshot() {
        SnapshotArray_1146 array = new SnapshotArray_1146(5);

        array.set(0, 1);
        array.set(2, 3);
        array.set(4, 5);
        int snapshot = array.snap();

        assertEquals(1, array.get(0, snapshot));
        assertEquals(0, array.get(1, snapshot));
        assertEquals(3, array.get(2, snapshot));
        assertEquals(0, array.get(3, snapshot));
        assertEquals(5, array.get(4, snapshot));
    }

    @Test
    public void shouldKeepEachHistoricalValueWhenAnIndexChangesRepeatedly() {
        SnapshotArray_1146 array = new SnapshotArray_1146(1);

        array.set(0, 2);
        int first = array.snap();
        array.set(0, 8);
        int second = array.snap();
        array.set(0, 1);
        int third = array.snap();

        assertEquals(2, array.get(0, first));
        assertEquals(8, array.get(0, second));
        assertEquals(1, array.get(0, third));
    }

    @Test
    public void shouldSupportTheMinimumAndMaximumValidIndices() {
        SnapshotArray_1146 array = new SnapshotArray_1146(50_000);

        array.set(0, 17);
        array.set(49_999, 23);
        int snapshot = array.snap();

        assertEquals(17, array.get(0, snapshot));
        assertEquals(23, array.get(49_999, snapshot));
        assertEquals(0, array.get(25_000, snapshot));
    }

    @Test
    public void shouldAssignSnapshotIdsStartingAtZeroAndIncreasingByOne() {
        SnapshotArray_1146 array = new SnapshotArray_1146(1);

        for (int expected = 0; expected < 12; expected++) {
            assertEquals(expected, array.snap());
        }
    }

    @Test
    public void shouldSupportRepeatedReadsOfTheSameSnapshot() {
        SnapshotArray_1146 array = new SnapshotArray_1146(3);

        array.set(2, 42);
        int snapshot = array.snap();

        for (int i = 0; i < 20; i++) {
            assertEquals(42, array.get(2, snapshot));
            assertEquals(0, array.get(0, snapshot));
        }
    }

    @Test
    public void shouldSupportZeroAndNegativeValuesBecauseTheClassAcceptsIntValues() {
        SnapshotArray_1146 array = new SnapshotArray_1146(3);

        array.set(0, -5);
        array.set(1, 0);
        array.set(2, Integer.MAX_VALUE);
        int snapshot = array.snap();

        assertEquals(-5, array.get(0, snapshot));
        assertEquals(0, array.get(1, snapshot));
        assertEquals(Integer.MAX_VALUE, array.get(2, snapshot));
    }

    @Test
    public void shouldSupportTheFullJavaIntValueRangeAcrossSnapshots() {
        SnapshotArray_1146 array = new SnapshotArray_1146(1);

        array.set(0, Integer.MIN_VALUE);
        int minimum = array.snap();
        array.set(0, -1);
        int negativeOne = array.snap();
        array.set(0, Integer.MAX_VALUE);
        int maximum = array.snap();

        assertEquals(Integer.MIN_VALUE, array.get(0, minimum));
        assertEquals(-1, array.get(0, negativeOne));
        assertEquals(Integer.MAX_VALUE, array.get(0, maximum));
    }

    @Test
    public void shouldAllowChangingSeveralIndicesBetweenSnapshots() {
        SnapshotArray_1146 array = new SnapshotArray_1146(4);

        array.set(0, 1);
        array.set(1, 2);
        int first = array.snap();
        array.set(1, 20);
        array.set(2, 30);
        int second = array.snap();
        array.set(0, 10);
        array.set(3, 40);
        int third = array.snap();

        assertSnapshot(array, first, 1, 2, 0, 0);
        assertSnapshot(array, second, 1, 20, 30, 0);
        assertSnapshot(array, third, 10, 20, 30, 40);
    }

    @Test
    public void shouldResolveSparseValuesFromTheMostRecentEarlierSnapshot() {
        SnapshotArray_1146 array = new SnapshotArray_1146(6);

        array.set(0, 10);
        int first = array.snap();
        array.set(5, 50);
        int second = array.snap();
        array.set(0, 100);
        int third = array.snap();
        int fourth = array.snap();

        assertSnapshot(array, first, 10, 0, 0, 0, 0, 0);
        assertSnapshot(array, second, 10, 0, 0, 0, 0, 50);
        assertSnapshot(array, third, 100, 0, 0, 0, 0, 50);
        assertSnapshot(array, fourth, 100, 0, 0, 0, 0, 50);
    }

    @Test
    public void shouldPreserveHistoryWhenAValueIsResetToZero() {
        SnapshotArray_1146 array = new SnapshotArray_1146(1);

        array.set(0, 9);
        int nonzero = array.snap();
        array.set(0, 0);
        int zero = array.snap();
        array.set(0, 4);
        int later = array.snap();

        assertEquals(9, array.get(0, nonzero));
        assertEquals(0, array.get(0, zero));
        assertEquals(4, array.get(0, later));
    }

    @Test
    public void shouldKeepInstancesAndTheirSnapshotIdsIndependent() {
        SnapshotArray_1146 first = new SnapshotArray_1146(2);
        SnapshotArray_1146 second = new SnapshotArray_1146(2);

        first.set(0, 7);
        assertEquals(0, first.snap());
        assertEquals(0, second.snap());
        second.set(1, 8);
        assertEquals(1, second.snap());

        assertEquals(7, first.get(0, 0));
        assertEquals(0, first.get(1, 0));
        assertEquals(0, second.get(0, 0));
        assertEquals(8, second.get(1, 1));
    }

    @Test
    public void shouldMatchAnIndependentOracleForASeededStatefulSequence() {
        int length = 17;
        SnapshotArray_1146 actual = new SnapshotArray_1146(length);
        VersionedArrayOracle oracle = new VersionedArrayOracle(length);
        Random random = new Random(1_146_2026L);

        for (int operation = 0; operation < 4_000; operation++) {
            int kind = random.nextInt(3);
            if (kind == 0 || oracle.snapshotCount() == 0) {
                int index = random.nextInt(length);
                int value = random.nextInt(2_001) - 1_000;
                actual.set(index, value);
                oracle.set(index, value);
            } else if (kind == 1) {
                int snapshot = random.nextInt(oracle.snapshotCount());
                int index = random.nextInt(length);
                assertEquals(oracle.get(index, snapshot), actual.get(index, snapshot));
            } else {
                assertEquals(oracle.snap(), actual.snap());
            }
        }
    }

    @Test
    public void shouldMatchAnIndependentOracleForAllShortOperationPatterns() {
        for (int seed = 0; seed < 40; seed++) {
            SnapshotArray_1146 actual = new SnapshotArray_1146(3);
            VersionedArrayOracle oracle = new VersionedArrayOracle(3);
            Random random = new Random(seed);

            for (int operation = 0; operation < 40; operation++) {
                if (random.nextBoolean()) {
                    int index = random.nextInt(3);
                    int value = random.nextInt(11) - 5;
                    actual.set(index, value);
                    oracle.set(index, value);
                } else {
                    assertEquals(oracle.snap(), actual.snap());
                }
            }
            for (int snapshot = 0; snapshot < oracle.snapshotCount(); snapshot++) {
                for (int index = 0; index < 3; index++) {
                    assertEquals(oracle.get(index, snapshot), actual.get(index, snapshot));
                }
            }
        }
    }

    @Test
    public void shouldRemainCorrectWhenTheLatestSnapshotIsReadInterleavedWithOlderOnes() {
        SnapshotArray_1146 array = new SnapshotArray_1146(2);

        array.set(0, 1);
        int first = array.snap();
        array.set(0, 2);
        int second = array.snap();
        array.set(1, 3);
        int third = array.snap();

        assertEquals(1, array.get(0, first));
        assertEquals(3, array.get(1, third));
        assertEquals(2, array.get(0, second));
        assertEquals(0, array.get(1, first));
        assertEquals(2, array.get(0, third));
        assertEquals(0, array.get(1, second));
    }

    @Test
    public void shouldHandleTheMaximumArrayLengthWithSeveralBoundaryUpdates() {
        SnapshotArray_1146 array = new SnapshotArray_1146(50_000);

        array.set(0, 100);
        array.set(49_999, 200);
        int first = array.snap();
        array.set(25_000, 300);
        int second = array.snap();

        assertEquals(100, array.get(0, second));
        assertEquals(200, array.get(49_999, first));
        assertEquals(0, array.get(25_000, first));
        assertEquals(300, array.get(25_000, second));
    }

    @Test
    public void shouldSupportTheMaximumNumberOfSnapshots() {
        SnapshotArray_1146 array = new SnapshotArray_1146(1);

        for (int expected = 0; expected < 50_000; expected++) {
            assertEquals(expected, array.snap());
        }
    }

    @Test
    public void shouldSupportTheMaximumNumberOfCalls() {
        SnapshotArray_1146 array = new SnapshotArray_1146(1);
        int lastSnapshot = -1;

        for (int value = 0; value < 16_666; value++) {
            array.set(0, value);
            lastSnapshot = array.snap();
            assertEquals(value, array.get(0, lastSnapshot));
        }
        // 16,666 * 3 = 49,998 calls; these two reads reach the 50,000-call contract boundary.
        assertEquals(16_665, array.get(0, lastSnapshot));
        assertEquals(16_665, array.get(0, lastSnapshot));
    }

    @Test
    public void shouldKeepTheMostRecentValueAfterManySnapshotsAndUpdates() {
        SnapshotArray_1146 array = new SnapshotArray_1146(3);
        List<Integer> snapshots = new ArrayList<>();

        for (int value = 0; value < 200; value++) {
            array.set(value % 3, value);
            snapshots.add(array.snap());
        }

        assertEquals(198, array.get(0, snapshots.get(198)));
        assertEquals(199, array.get(1, snapshots.get(199)));
        assertEquals(197, array.get(2, snapshots.get(197)));
        assertEquals(198, array.get(0, snapshots.get(199)));
        assertEquals(197, array.get(2, snapshots.get(199)));
    }

    @Test
    public void shouldSupportAZeroLengthConstructionAsAnImplementationGuardWithoutAValidRead() {
        // LeetCode requires length >= 1. The class does not reject zero, but no index operation is
        // valid for such an instance, so this test only verifies construction and snapshot IDs.
        SnapshotArray_1146 array = new SnapshotArray_1146(0);

        assertEquals(0, array.snap());
        assertEquals(1, array.snap());
    }

    private static void assertSnapshot(SnapshotArray_1146 array, int snapshot, int... expected) {
        for (int index = 0; index < expected.length; index++) {
            assertEquals(expected[index], array.get(index, snapshot), "index=" + index);
        }
    }

    private static final class VersionedArrayOracle {
        private final int[] current;
        private final List<int[]> snapshots = new ArrayList<>();

        private VersionedArrayOracle(int length) {
            current = new int[length];
        }

        private void set(int index, int value) {
            current[index] = value;
        }

        private int snap() {
            snapshots.add(current.clone());
            return snapshots.size() - 1;
        }

        private int get(int index, int snapshot) {
            return snapshots.get(snapshot)[index];
        }

        private int snapshotCount() {
            return snapshots.size();
        }
    }
}
