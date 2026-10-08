package solutions.intervals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.fail;

@Timeout(15)
@ExtendWith(Insert_57Test.ProcessTimeoutExtension.class)
public class Insert_57Test {
    private final Insert_57 solver = new Insert_57();

    @Test
    public void testNoOverlap() {
        int[][] intervals = {{1, 3}, {6, 9}};
        int[][] expected = {{1, 3}, {4, 5}, {6, 9}};
        // Wait - [4,5] needs no merge with [1,3] or [6,9]
        // But inserting [4,5] into [{1,3},{6,9}] produces [[1,3],[4,5],[6,9]]
        assertArrayEquals(expected, solver.insert(intervals, new int[]{4, 5}));
    }

    @Test
    public void testOverlap() {
        int[][] intervals = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[][] expected = {{1, 2}, {3, 10}, {12, 16}};
        assertArrayEquals(expected, solver.insert(intervals, new int[]{4, 8}));
    }

    @Test
    public void testEmpty() {
        int[][] intervals = {};
        int[][] expected = {{5, 7}};
        assertArrayEquals(expected, solver.insert(intervals, new int[]{5, 7}));
    }

    @Test
    public void testInsertAtBeginning() {
        int[][] intervals = {{3, 5}, {6, 7}};
        int[][] expected = {{1, 2}, {3, 5}, {6, 7}};
        assertArrayEquals(expected, solver.insert(intervals, new int[]{1, 2}));
    }

    @Test
    public void testInsertAtEnd() {
        int[][] intervals = {{1, 2}, {3, 4}};
        int[][] expected = {{1, 2}, {3, 4}, {5, 6}};
        assertArrayEquals(expected, solver.insert(intervals, new int[]{5, 6}));
    }

    @Test
    public void testMergeAll() {
        int[][] intervals = {{1, 3}, {4, 6}, {7, 9}};
        int[][] expected = {{0, 10}};
        assertArrayEquals(expected, solver.insert(intervals, new int[]{0, 10}));
    }

    @Test
    public void testNullIntervals() {
        assertArrayEquals(new int[0][0], solver.insert(null, new int[]{1, 2}));
    }

    @Test
    public void testTouchingBoundary() {
        int[][] intervals = {{1, 5}, {6, 10}};
        int[][] expected = {{1, 10}};
        assertArrayEquals(expected, solver.insert(intervals, new int[]{5, 6}));
    }

    @Test
    public void testGiantCase() {
        runGiantScenario();
    }

    private static void runGiantScenario() {
        int size = 10000;
        int[][] intervals = new int[size][2];
        for (int i = 0; i < size; i++) {
            intervals[i] = new int[]{i * 3, i * 3 + 1};
        }
        // Insert interval that doesn't overlap with any
        int[][] result = new Insert_57().insert(intervals, new int[]{size * 3, size * 3 + 1});
        assertEquals(size + 1, result.length);
        for (int i = 0; i <= size; i++) {
            assertArrayEquals(new int[]{i * 3, i * 3 + 1}, result[i]);
        }
    }

    @Test
    public void testNewIntervalFillsGapMergingNeighbors() {
        // [1,3] gap [5,7] -> insert [3,5] merges all into [1,7]
        int[][] intervals = {{1, 3}, {5, 7}};
        int[][] expected = {{1, 7}};
        assertArrayEquals(expected, solver.insert(intervals, new int[]{3, 5}));
    }

    @Test
    public void testNewIntervalContainedInsideExisting() {
        // [1,10] already covers [3,5], result unchanged
        int[][] intervals = {{1, 10}, {15, 20}};
        int[][] expected = {{1, 10}, {15, 20}};
        assertArrayEquals(expected, solver.insert(intervals, new int[]{3, 5}));
    }

    @Test
    public void testExistingContainedInsideNew() {
        // New [0,20] swallows [3,5],[7,9],[11,13]
        int[][] intervals = {{3, 5}, {7, 9}, {11, 13}};
        int[][] expected = {{0, 20}};
        assertArrayEquals(expected, solver.insert(intervals, new int[]{0, 20}));
    }

    @Test
    public void testOverlapsMultipleConsecutive() {
        // New [2,14] overlaps [1,3],[5,8],[10,12],[14,16]
        int[][] intervals = {{1, 3}, {5, 8}, {10, 12}, {14, 16}};
        int[][] expected = {{1, 16}};
        assertArrayEquals(expected, solver.insert(intervals, new int[]{2, 14}));
    }

    @Test
    public void testTouchingBoundaryPrepend() {
        // New [0,1] touches [1,3] -> merge to [0,3]
        int[][] intervals = {{1, 3}, {5, 7}};
        int[][] expected = {{0, 3}, {5, 7}};
        assertArrayEquals(expected, solver.insert(intervals, new int[]{0, 1}));
    }

    @Test
    public void testNegativeCoordinates() {
        int[][] intervals = {{-10, -5}, {-3, -1}, {2, 4}};
        int[][] expected = {{-10, -5}, {-4, -1}, {2, 4}};
        assertArrayEquals(expected, solver.insert(intervals, new int[]{-4, -2}));
    }

    @Test
    public void testLarge1000IntervalsInsertMiddle() {
        int size = 1000;
        int[][] intervals = new int[size][2];
        for (int i = 0; i < size; i++) {
            intervals[i] = new int[]{i * 4, i * 4 + 1}; // gaps of 2 between intervals
        }
        // Insert in middle merging intervals 400-600 (indices)
        int[] newInterval = new int[]{400 * 4, 600 * 4 + 1};
        int[][] result = solver.insert(intervals, newInterval);
        // 400 before + 1 merged + 399 after = 800
        assertEquals(800, result.length);
        assertArrayEquals(new int[]{1600, 2401}, result[400]);
    }

    @Test
    public void testSingleIntervalSameAsNew() {
        // Inserting exact duplicate
        int[][] intervals = {{2, 5}};
        int[][] expected = {{2, 5}};
        assertArrayEquals(expected, solver.insert(intervals, new int[]{2, 5}));
    }

    @Test
    public void testNewIntervalSpansEntireRange() {
        int[][] intervals = {{-100, -50}, {0, 10}, {20, 30}, {40, 50}};
        int[][] expected = {{-200, 200}};
        assertArrayEquals(expected, solver.insert(intervals, new int[]{-200, 200}));
    }

    @Test
    public void testGiantMiddleInsertionChecksEntireResult() {
        int[][] intervals = new int[2000][2];
        java.util.List<int[]> expected = new java.util.ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            intervals[i] = new int[]{4 * i, 4 * i + 1};
            if (i < 500 || i > 1500) expected.add(intervals[i].clone());
            if (i == 500) expected.add(new int[]{2000, 6001});
        }
        assertArrayEquals(expected.toArray(new int[0][]), solver.insert(intervals, new int[]{2000, 6001}));
    }

    @Test
    public void testPointInGapIsPreservedAsSeparateClosedInterval() {
        assertArrayEquals(new int[][]{{0, 2}, {3, 3}, {4, 6}},
                solver.insert(new int[][]{{0, 2}, {4, 6}}, new int[]{3, 3}));
    }

    @Test
    public void testPointAtExistingEndpointDoesNotSplitInterval() {
        assertArrayEquals(new int[][]{{0, 2}, {4, 6}},
                solver.insert(new int[][]{{0, 2}, {4, 6}}, new int[]{4, 4}));
    }

    @Test
    public void testMaximumEndpointAndZeroWidthIntervals() {
        assertArrayEquals(new int[][]{{99999, 99999}, {100000, 100000}},
                solver.insert(new int[][]{{99999, 99999}}, new int[]{100000, 100000}));
        assertArrayEquals(new int[][]{{5, 5}},
                solver.insert(new int[][]{{5, 5}}, new int[]{5, 5}));
    }

    @Test
    public void testDoesNotMutateInputRows() {
        int[][] intervals = {{1, 2}, {5, 7}};
        int[][] original = deepCopy(intervals);
        int[] newInterval = {3, 6};
        int[] originalNewInterval = newInterval.clone();
        solver.insert(intervals, newInterval);
        assertArrayEquals(original, intervals);
        assertArrayEquals(originalNewInterval, newInterval);
    }

    @Test
    public void testRepeatedCallsDoNotShareInsertedIntervalState() {
        int[][] intervals = {{2, 4}};
        int[][] first = solver.insert(intervals, new int[]{0, 1});
        first[0][0] = 99;
        assertArrayEquals(new int[][]{{2, 4}, {5, 6}},
                solver.insert(intervals, new int[]{5, 6}));
    }

    @Test
    public void testSharesOnlyExistingPrefixRows() {
        int[][] intervals = {{0, 1}, {5, 6}, {10, 11}};
        int[] addition = {5, 7};
        int[][] result = solver.insert(intervals, addition);
        assertSame(intervals[0], result[0]);
        assertNotSame(intervals[1], result[1]);
        assertNotSame(intervals[2], result[2]);
        result[0][0] = 99;
        result[1][0] = 88;
        result[1][1] = 89;
        addition[0] = 77;
        assertEquals(99, intervals[0][0]);
        assertEquals(5, intervals[1][0]);
        assertEquals(6, intervals[1][1]);
        assertEquals(88, result[1][0]);
        assertEquals(89, result[1][1]);
        assertEquals(77, addition[0]);
    }

    @Test
    public void testExhaustiveSmallIntervalFamiliesAgainstIndependentOracle() {
        runExhaustiveScenarios();
    }

    private static void runExhaustiveScenarios() {
        // Enumerate every sorted family over endpoints 0..5 whose closed rows
        // are strictly disjoint (next start is at least previous end + 1),
        // and every inserted range in that domain. Expected values come from a
        // separate sort-and-merge oracle.
        Insert_57 solver = new Insert_57();
        List<int[][]> families = new ArrayList<>();
        enumerateFamilies(0, new ArrayList<>(), families);
        List<int[]> inserted = new ArrayList<>();
        for (int start = 0; start <= 5; start++) {
            for (int end = start; end <= 5; end++) {
                inserted.add(new int[]{start, end});
            }
        }
        assertEquals(233, families.size());
        assertEquals(21, inserted.size());
        assertEquals(4893, families.size() * inserted.size());

        for (int familyIndex = 0; familyIndex < families.size(); familyIndex++) {
            for (int intervalIndex = 0; intervalIndex < inserted.size(); intervalIndex++) {
                int[][] input = deepCopy(families.get(familyIndex));
                int[] addition = inserted.get(intervalIndex).clone();
                int[][] expected = oracle(input, addition);
                assertArrayEquals(expected, solver.insert(input, addition),
                        "family=" + Arrays.deepToString(input)
                                + ", insertion=" + Arrays.toString(addition));
            }
        }
    }

    private static void enumerateFamilies(int nextStart, List<int[]> current,
                                          List<int[][]> families) {
        families.add(current.stream().map(int[]::clone).toArray(int[][]::new));
        for (int start = nextStart; start <= 5; start++) {
            for (int end = start; end <= 5; end++) {
                current.add(new int[]{start, end});
                enumerateFamilies(end + 1, current, families);
                current.remove(current.size() - 1);
            }
        }
    }

    private static int[][] oracle(int[][] intervals, int[] newInterval) {
        List<int[]> all = new ArrayList<>();
        for (int[] interval : intervals) {
            all.add(interval.clone());
        }
        all.add(newInterval.clone());
        all.sort(Comparator.comparingInt(interval -> interval[0]));
        List<int[]> merged = new ArrayList<>();
        for (int[] interval : all) {
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]) {
                merged.add(interval.clone());
            } else {
                int[] previous = merged.get(merged.size() - 1);
                previous[1] = Math.max(previous[1], interval[1]);
            }
        }
        return merged.toArray(new int[0][]);
    }

    private static int[][] deepCopy(int[][] intervals) {
        return Arrays.stream(intervals).map(int[]::clone).toArray(int[][]::new);
    }

    /**
     * Runs each test method in a fresh JVM so an uninterruptible loop cannot hang the suite.
     */
    static final class ProcessTimeoutExtension implements InvocationInterceptor {
        @Override
        public void interceptTestMethod(Invocation<Void> invocation,
                                        ReflectiveInvocationContext<Method> context, ExtensionContext extensionContext)
                throws Throwable {
            Path log = Files.createTempFile("insert-57-test-", ".log");
            Process process = null;
            try {
                process = new ProcessBuilder(
                        System.getProperty("java.home") + "/bin/java", "-cp",
                        System.getProperty("java.class.path"), Insert_57Test.class.getName(),
                        context.getExecutable().getName())
                        .redirectErrorStream(true)
                        .redirectOutput(log.toFile())
                        .start();
                if (!process.waitFor(10, java.util.concurrent.TimeUnit.SECONDS)) {
                    process.destroyForcibly();
                    process.waitFor(2, java.util.concurrent.TimeUnit.SECONDS);
                    fail("test method exceeded enforceable 10-second process limit: "
                            + context.getExecutable().getName());
                }
                if (process.exitValue() != 0) {
                    fail("child test failed: " + Files.readString(log));
                }
                invocation.skip();
            } catch (InterruptedException exception) {
                if (process != null) {
                    process.destroyForcibly();
                    process.waitFor(2, java.util.concurrent.TimeUnit.SECONDS);
                }
                Thread.currentThread().interrupt();
                fail("test process was interrupted", exception);
            } finally {
                if (process != null && process.isAlive()) {
                    process.destroyForcibly();
                    process.waitFor(2, java.util.concurrent.TimeUnit.SECONDS);
                }
                Files.deleteIfExists(log);
            }
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) throw new IllegalArgumentException("missing test method");
        try {
            Insert_57Test.class.getDeclaredMethod(args[0]).invoke(new Insert_57Test());
        } catch (InvocationTargetException exception) {
            throw new RuntimeException(exception.getCause());
        } catch (ReflectiveOperationException exception) {
            throw new RuntimeException(exception);
        }
    }
}
