package solutions.intervals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the greedy interval-removal solution with explicit independent answers.
 */
public class EraseOverlapIntervals_435Test {
    private int solve(int expected, int[][] intervals) {
        int actual = new EraseOverlapIntervals_435().eraseOverlapIntervals(intervals);
        assertEquals(expected, actual, Arrays.deepToString(intervals));
        return actual;
    }

    @Test
    public void testNullInput() {
        solve(0, null);
    }

    @Test
    public void testEmptyInput() {
        solve(0, new int[][]{});
    }

    @Test
    public void testSingleInterval() {
        solve(0, new int[][]{{1, 2}});
    }

    @Test
    public void testTwoDisjointIntervals() {
        solve(0, new int[][]{{1, 2}, {3, 4}});
    }

    @Test
    public void testTwoTouchingIntervals() {
        solve(0, new int[][]{{1, 2}, {2, 3}});
    }

    @Test
    public void testTwoOverlappingIntervals() {
        solve(1, new int[][]{{1, 3}, {2, 4}});
    }

    @Test
    public void testNestedIntervalKeepsShorter() {
        solve(1, new int[][]{{1, 10}, {2, 3}});
    }

    @Test
    public void testNestedIntervalArrivesFirst() {
        solve(1, new int[][]{{2, 3}, {1, 10}});
    }

    @Test
    public void testSameIntervals() {
        solve(2, new int[][]{{1, 2}, {1, 2}, {1, 2}});
    }

    @Test
    public void testSameStartDifferentEnds() {
        solve(2, new int[][]{{1, 5}, {1, 3}, {1, 2}});
    }

    @Test
    public void testSameEndDifferentStarts() {
        solve(2, new int[][]{{1, 5}, {2, 5}, {3, 5}});
    }

    @Test
    public void testChainOfOverlaps() {
        solve(2, new int[][]{{1, 4}, {2, 5}, {3, 6}});
    }

    @Test
    public void testChainWithBestMiddleInterval() {
        solve(1, new int[][]{{1, 4}, {2, 3}, {3, 5}});
    }

    @Test
    public void testLongIntervalContainsAllOthers() {
        solve(1, new int[][]{{0, 100}, {1, 2}, {2, 3}, {3, 4}});
    }

    @Test
    public void testAllIntervalsDisjointUnsorted() {
        solve(0, new int[][]{{9, 10}, {1, 2}, {5, 6}, {3, 4}});
    }

    @Test
    public void testOverlapAfterDisjointPrefix() {
        solve(1, new int[][]{{0, 1}, {2, 3}, {2, 4}, {5, 6}});
    }

    @Test
    public void testOverlapBeforeDisjointSuffix() {
        solve(1, new int[][]{{0, 3}, {1, 2}, {5, 6}, {7, 8}});
    }

    @Test
    public void testTwoSeparateOverlapGroups() {
        solve(2, new int[][]{{0, 3}, {1, 2}, {5, 8}, {6, 7}});
    }

    @Test
    public void testThreeSeparateOverlapGroups() {
        solve(3, new int[][]{{0, 2}, {1, 3}, {5, 7}, {6, 8}, {10, 12}, {11, 13}});
    }

    @Test
    public void testNegativeCoordinates() {
        solve(1, new int[][]{{-10, -5}, {-8, -3}, {-2, 0}});
    }

    @Test
    public void testNegativeAndPositiveCoordinates() {
        solve(1, new int[][]{{-5, 2}, {-3, -1}, {2, 5}});
    }

    @Test
    public void testZeroAsBoundary() {
        solve(0, new int[][]{{-2, 0}, {0, 2}, {2, 5}});
    }

    @Test
    public void testPublishedCoordinateBounds() {
        solve(1, new int[][]{{-10_000, -1}, {-5, 10_000}});
    }

    @Test
    public void testNearMaximumAdjacentValues() {
        solve(0, new int[][]{{9_998, 9_999}, {9_999, 10_000}});
    }

    @Test
    public void testNearMinimumAdjacentValues() {
        solve(0, new int[][]{{-10_000, -9_999}, {-9_999, -9_998}});
    }

    @Test
    public void testUnsortedOverlappingInput() {
        solve(1, new int[][]{{5, 7}, {1, 4}, {3, 6}});
    }

    @Test
    public void testTieStartSortUsesStableAnswer() {
        solve(1, new int[][]{{1, 4}, {1, 2}, {2, 3}});
    }

    @Test
    public void testTieStartAllTouchAfterShortest() {
        solve(1, new int[][]{{0, 5}, {0, 1}, {1, 2}, {2, 3}});
    }

    @Test
    public void testTouchingAfterReplacement() {
        solve(1, new int[][]{{0, 4}, {1, 2}, {2, 5}});
    }

    @Test
    public void testLaterIntervalOverlapsOnlyCurrentEnd() {
        solve(1, new int[][]{{0, 2}, {1, 10}, {2, 3}});
    }

    @Test
    public void testGreedyChoosesShorterEnd() {
        solve(1, new int[][]{{0, 10}, {1, 2}, {2, 3}, {3, 4}});
    }

    @Test
    public void testGreedyChoosesShorterEndInUnsortedInput() {
        solve(1, new int[][]{{3, 4}, {0, 10}, {1, 2}, {2, 3}});
    }

    @Test
    public void testAllIntervalsOverlapAtCommonPoint() {
        solve(3, new int[][]{{0, 5}, {1, 6}, {2, 7}, {3, 8}});
    }

    @Test
    public void testOnlyOneRemovalNeededAmongMany() {
        solve(1, new int[][]{{0, 1}, {1, 2}, {2, 4}, {3, 5}, {5, 6}});
    }

    @Test
    public void testMaximumCompatibleCount() {
        solve(0, new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}, {4, 5}});
    }

    @Test
    public void testMinimumCompatibleCount() {
        solve(4, new int[][]{{0, 10}, {1, 9}, {2, 8}, {3, 7}, {4, 6}});
    }

    @Test
    public void testDuplicateAndDisjointMix() {
        solve(2, new int[][]{{0, 1}, {0, 1}, {2, 3}, {2, 3}});
    }

    @Test
    public void testIntervalsWithUnitLength() {
        solve(2, new int[][]{{0, 1}, {0, 1}, {1, 2}, {1, 2}});
    }

    @Test
    public void testBroadIntervalsWithGaps() {
        solve(1, new int[][]{{0, 100}, {10, 20}, {30, 40}, {50, 60}});
    }

    @Test
    public void testNegativeUnsortedTouching() {
        solve(0, new int[][]{{-1, 0}, {-3, -1}, {-5, -3}});
    }

    @Test
    public void testOverlapAtNegativeBoundary() {
        solve(1, new int[][]{{-5, -2}, {-3, 0}});
    }

    @Test
    public void testOneIntervalStartsAtAnotherEnd() {
        solve(1, new int[][]{{-1, 2}, {2, 3}, {1, 4}});
    }

    @Test
    public void testFourIntervalsOneRemoval() {
        solve(1, new int[][]{{1, 3}, {3, 5}, {4, 6}, {6, 8}});
    }

    @Test
    public void testFourIntervalsTwoRemovals() {
        solve(2, new int[][]{{1, 4}, {2, 3}, {3, 6}, {5, 7}});
    }

    @Test
    public void testFiveIntervalsNested() {
        solve(4, new int[][]{{0, 10}, {1, 9}, {2, 8}, {3, 7}, {4, 6}});
    }

    @Test
    public void testFiveIntervalsStaggered() {
        solve(1, new int[][]{{0, 5}, {1, 2}, {2, 6}, {6, 7}, {7, 10}});
    }

    @Test
    public void testInputOrderIsMutatedBySort() {
        int[][] intervals = {{5, 6}, {1, 2}, {3, 4}};
        solve(0, intervals);
        assertEquals(1, intervals[0][0]);
        assertEquals(3, intervals[1][0]);
        assertEquals(5, intervals[2][0]);
    }

    @Test
    public void testRepeatedCallsDoNotShareState() {
        EraseOverlapIntervals_435 solver = new EraseOverlapIntervals_435();
        assertEquals(1, solver.eraseOverlapIntervals(new int[][]{{0, 3}, {1, 2}}));
        assertEquals(0, solver.eraseOverlapIntervals(new int[][]{{0, 1}, {1, 2}}));
        assertEquals(2, solver.eraseOverlapIntervals(new int[][]{{0, 4}, {1, 3}, {2, 5}}));
    }

    @Test
    @Timeout(value = 10, unit = TimeUnit.SECONDS)
    public void testExhaustiveSmallIntervalSets() {
        int[][] universe = {{0, 1}, {0, 2}, {1, 2}, {1, 3}, {2, 3}};
        for (int mask = 0; mask < (1 << universe.length); mask++) {
            int[][] input = new int[Integer.bitCount(mask)][2];
            int index = 0;
            for (int bit = 0; bit < universe.length; bit++) {
                if ((mask & (1 << bit)) != 0) input[index++] = universe[bit].clone();
            }
            assertEquals(bruteForceRemovals(input), new EraseOverlapIntervals_435().eraseOverlapIntervals(input),
                    "mask=" + mask);
        }
    }

    @Test
    @Timeout(value = 10, unit = TimeUnit.SECONDS)
    public void testExhaustiveTwoIntervalValues() {
        for (int a = 0; a <= 3; a++)
            for (int b = a + 1; b <= 4; b++)
                for (int c = 0; c <= 3; c++)
                    for (int d = c + 1; d <= 4; d++) {
                        int[][] input = {{a, b}, {c, d}};
                        assertEquals(bruteForceRemovals(input), new EraseOverlapIntervals_435().eraseOverlapIntervals(input),
                                Arrays.deepToString(input));
                    }
    }

    @Test
    @Timeout(value = 10, unit = TimeUnit.SECONDS)
    public void testMaximumPublishedIntervalCountWithRepeatedOverlap() {
        int[][] intervals = new int[100_000][2];
        for (int i = 0; i < intervals.length; i++) intervals[i] = new int[]{0, 1};
        solve(99_999, intervals);
    }

    @Test
    @Timeout(value = 10, unit = TimeUnit.SECONDS)
    public void testMaximumPublishedIntervalCountWithBoundedStarts() {
        int[][] intervals = new int[100_000][2];
        for (int i = 0; i < intervals.length; i++) intervals[i] = new int[]{i % 10_000, 10_000};
        solve(99_999, intervals);
    }

    @Test
    public void testOneHundredAlternatingIntervals() {
        int[][] intervals = new int[100][2];
        for (int i = 0; i < intervals.length; i++) intervals[i] = new int[]{i * 2, i * 2 + (i % 2 == 0 ? 2 : 1)};
        solve(0, intervals);
    }

    private int bruteForceRemovals(int[][] intervals) {
        if (intervals.length == 0) return 0;
        int best = 0;
        int subsets = 1 << intervals.length;
        for (int mask = 0; mask < subsets; mask++) {
            int[][] chosen = new int[Integer.bitCount(mask)][];
            int index = 0;
            for (int i = 0; i < intervals.length; i++) if ((mask & (1 << i)) != 0) chosen[index++] = intervals[i];
            Arrays.sort(chosen, Comparator.comparingInt(interval -> interval[0]));
            boolean valid = true;
            for (int i = 1; i < chosen.length; i++) if (chosen[i - 1][1] > chosen[i][0]) valid = false;
            if (valid) best = Math.max(best, chosen.length);
        }
        return intervals.length - best;
    }
}
