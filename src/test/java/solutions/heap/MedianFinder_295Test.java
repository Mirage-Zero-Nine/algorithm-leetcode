package solutions.heap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import java.util.concurrent.TimeUnit;

/** Checks stream medians against an independent sorted-list oracle. */
public class MedianFinder_295Test {

    @Test
    public void testHappyCases() {
        MedianFinder_295 test = new MedianFinder_295();
        test.addNum(1);
        test.addNum(2);
        assertEquals(1.5, test.findMedian());
        test.addNum(3);
        assertEquals(2.0, test.findMedian());
    }

    @Test
    public void testNegativeCases() {
        MedianFinder_295 test = new MedianFinder_295();
        test.addNum(-5);
        test.addNum(-1);
        test.addNum(-3);
        assertEquals(-3.0, test.findMedian());
    }

    @Test
    public void testEdgeCases() {
        MedianFinder_295 test = new MedianFinder_295();
        test.addNum(42);
        assertEquals(42.0, test.findMedian());
        test.addNum(42);
        assertEquals(42.0, test.findMedian());
    }

    @Test
    public void testLargeCase() {
        MedianFinder_295 test = new MedianFinder_295();
        for (int i = 1; i <= 100; i++) {
            test.addNum(i);
        }
        assertEquals(50.5, test.findMedian());
    }

    @Test
    public void testDescendingOrder() {
        MedianFinder_295 test = new MedianFinder_295();
        test.addNum(5);
        test.addNum(4);
        test.addNum(3);
        test.addNum(2);
        test.addNum(1);
        assertEquals(3.0, test.findMedian());
    }

    @Test
    public void testAllSameValues() {
        MedianFinder_295 test = new MedianFinder_295();
        test.addNum(7);
        test.addNum(7);
        test.addNum(7);
        test.addNum(7);
        assertEquals(7.0, test.findMedian());
    }

    @Test
    public void testTwoElements() {
        MedianFinder_295 test = new MedianFinder_295();
        test.addNum(1);
        test.addNum(100);
        assertEquals(50.5, test.findMedian());
    }

    @Test
    public void testLargeNegatives() {
        MedianFinder_295 test = new MedianFinder_295();
        test.addNum(-100);
        test.addNum(-50);
        test.addNum(-1);
        assertEquals(-50.0, test.findMedian());
    }

    @Test
    public void testMixedPositiveNegative() {
        MedianFinder_295 test = new MedianFinder_295();
        test.addNum(-10);
        test.addNum(10);
        assertEquals(0.0, test.findMedian());
        test.addNum(0);
        assertEquals(0.0, test.findMedian());
    }

    @Test
    public void testGiantCaseOdd() {
        MedianFinder_295 test = new MedianFinder_295();
        for (int i = 1; i <= 999; i++) {
            test.addNum(i);
        }
        assertEquals(500.0, test.findMedian());
    }

    @Test
    public void testSingleAdd() {
        MedianFinder_295 test = new MedianFinder_295();
        test.addNum(99);
        assertEquals(99.0, test.findMedian(), 1e-9);
    }

    @Test
    public void testTwoAddsSameValue() {
        MedianFinder_295 test = new MedianFinder_295();
        test.addNum(5);
        test.addNum(5);
        assertEquals(5.0, test.findMedian(), 1e-9);
    }

    @Test
    public void testTwoAddsDifferentAverage() {
        MedianFinder_295 test = new MedianFinder_295();
        test.addNum(1);
        test.addNum(2);
        assertEquals(1.5, test.findMedian(), 1e-9);
    }

    @Test
    public void testStrictlyIncreasingMedianAtEachStep() {
        MedianFinder_295 test = new MedianFinder_295();
        // Add 1..7, check median after each add
        double[] expected = {1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0};
        for (int i = 1; i <= 7; i++) {
            test.addNum(i);
            assertEquals(expected[i - 1], test.findMedian(), 1e-9, "After adding " + i);
        }
    }

    @Test
    public void testStrictlyDecreasingMedianAtEachStep() {
        MedianFinder_295 test = new MedianFinder_295();
        // Add 7,6,5,4,3,2,1 - sorted is always [1..k] reversed
        List<Integer> sorted = new ArrayList<>();
        for (int i = 7; i >= 1; i--) {
            test.addNum(i);
            sorted.add(i);
            Collections.sort(sorted);
            int n = sorted.size();
            double expectedMedian = n % 2 == 1 ? sorted.get(n / 2) : (sorted.get(n / 2 - 1) + sorted.get(n / 2)) / 2.0;
            assertEquals(expectedMedian, test.findMedian(), 1e-9, "After adding " + i);
        }
    }

    @Test
    public void testAllSameValueAlwaysMedian() {
        MedianFinder_295 test = new MedianFinder_295();
        for (int i = 0; i < 20; i++) {
            test.addNum(3);
            assertEquals(3.0, test.findMedian(), 1e-9, "After " + (i + 1) + " adds");
        }
    }

    @Test
    public void testNegativeNumbersIncludingIntMin() {
        MedianFinder_295 test = new MedianFinder_295();
        test.addNum(-1_000_000_000);
        assertEquals(-1_000_000_000.0, test.findMedian(), 1e-9);
        test.addNum(0);
        assertEquals(-500_000_000.0, test.findMedian(), 1e-9);
        test.addNum(-1);
        assertEquals(-1.0, test.findMedian(), 1e-9);
    }

    @Test
    public void testLargeStream1000RandomSeed42() {
        // Use bounded random values to avoid int addition overflow in findMedian's (a+b)/2.0
        MedianFinder_295 test = new MedianFinder_295();
        Random rng = new Random(42L);
        List<Integer> sorted = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            int val = rng.nextInt(2_000_001) - 1_000_000; // range [-1_000_000, 1_000_000]
            test.addNum(val);
            sorted.add(val);
            Collections.sort(sorted);
            int n = sorted.size();
            double expectedMedian = n % 2 == 1 ? sorted.get(n / 2) : (sorted.get(n / 2 - 1) + sorted.get(n / 2)) / 2.0;
            assertEquals(expectedMedian, test.findMedian(), 1e-9, "Mismatch at step " + (i + 1));
        }
    }

    @Test
    public void testOverflowRiskLargeInts() {
        // The implementation uses (small.peek() + large.peek()) / 2.0 which overflows for large ints.
        // This test uses large-but-safe values that don't overflow int addition.
        MedianFinder_295 test = new MedianFinder_295();
        test.addNum(Integer.MAX_VALUE / 2);
        test.addNum(Integer.MAX_VALUE / 2 - 1);
        double expected = (Integer.MAX_VALUE / 2 + (double) (Integer.MAX_VALUE / 2 - 1)) / 2.0;
        assertEquals(expected, test.findMedian(), 1e-9);
    }

    @Test public void testInterleavedOrder() { MedianFinder_295 t = new MedianFinder_295(); for (int n : new int[]{10, -10, 5, -5}) t.addNum(n); assertEquals(0.0, t.findMedian(), 1e-9); }
    @Test
    public void testEmptyStreamUsesDocumentedSentinel() {
        assertEquals(-1.0, new MedianFinder_295().findMedian(), 1e-9);
    }

    @Test
    public void testBoundaryValuesAndOverflowSafeAverage() {
        assertStream(new int[]{-100_000, 100_000}, new double[]{-100_000.0, 0.0});
        assertStream(new int[]{100_000, 100_000, -100_000, -100_000},
                new double[]{100_000.0, 100_000.0, 100_000.0, 0.0});
    }

    @Test
    public void testFullIntegerExtensionUsesWidenedAverage() {
        assertStream(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE},
                new double[]{Integer.MAX_VALUE, Integer.MAX_VALUE});
        assertStream(new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE},
                new double[]{Integer.MIN_VALUE, Integer.MIN_VALUE});
        assertStream(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE},
                new double[]{Integer.MIN_VALUE, -0.5});
    }

    @Test
    public void testMedianAfterEveryInsertionWithDuplicateBoundaries() {
        assertStream(new int[]{5, 5, 4, 5, 4, 4, 6, 3},
                new double[]{5.0, 5.0, 5.0, 5.0, 5.0, 4.5, 5.0, 4.5});
    }

    @Test
    public void testRepeatedCallsDoNotChangeState() {
        MedianFinder_295 finder = new MedianFinder_295();
        finder.addNum(-7);
        finder.addNum(11);
        assertEquals(2.0, finder.findMedian(), 1e-9);
        assertEquals(2.0, finder.findMedian(), 1e-9);
        finder.addNum(100);
        assertEquals(11.0, finder.findMedian(), 1e-9);
    }

    @Test
    public void testExhaustiveShortStreamsAgainstSortedOracle() {
        // Exhaust every stream of lengths 1..5 over {-2, 0, 3}: 363 streams
        // and every prefix, covering all parity transitions independently of
        // the heap implementation.
        runWorker("exhaustive");
    }

    @Test
    public void testMaximumSizeIncreasingStream() {
        runWorker("increasing");
    }

    @Test
    public void testLargeDeterministicRandomStream() {
        runWorker("random");
    }

    private static void verifyAllStreams(int[] stream, int index) {
        if (index == stream.length) {
            assertStream(stream, expectedMedians(stream));
            return;
        }
        for (int value : new int[]{-2, 0, 3}) {
            stream[index] = value;
            verifyAllStreams(stream, index + 1);
        }
    }

    private static void runWorker(String workload) {
        Path output = null;
        Process process = null;
        try {
            output = Files.createTempFile("median295-worker-", ".log");
            process = new ProcessBuilder(
                    javaExecutable(), "-cp", System.getProperty("java.class.path"),
                    "solutions.heap.MedianFinder_295Test$Worker", workload)
                    .redirectErrorStream(true)
                    .redirectOutput(output.toFile())
                    .start();
            if (!process.waitFor(10, TimeUnit.SECONDS)) {
                process.destroyForcibly();
                if (process.waitFor(2, TimeUnit.SECONDS)) {
                    throw new AssertionError("worker timed out: " + workload);
                }
                throw new AssertionError("worker did not terminate after forced cleanup: " + workload);
            }
            String report = Files.readString(output);
            assertEquals(0, process.exitValue(), "worker failed: " + workload + "\n" + report);
        } catch (IOException exception) {
            throw new AssertionError("could not start worker: " + workload, exception);
        } catch (InterruptedException exception) {
            if (process != null) {
                process.destroyForcibly();
                try {
                    if (!process.waitFor(2, TimeUnit.SECONDS)) {
                        throw new AssertionError("worker did not terminate after interruption: " + workload);
                    }
                } catch (InterruptedException cleanupInterrupted) {
                    Thread.currentThread().interrupt();
                }
            }
            Thread.currentThread().interrupt();
            throw new AssertionError("worker interrupted: " + workload, exception);
        } finally {
            if (output != null) {
                try {
                    Files.deleteIfExists(output);
                } catch (IOException ignored) {
                    // The temporary report is only diagnostic and is safe to leave.
                }
            }
        }
    }

    private static String javaExecutable() {
        return Path.of(System.getProperty("java.home"), "bin", "java").toString();
    }

    /** Isolated entry point for bounded workloads that must be forcibly stoppable. */
    public static final class Worker {
        public static void main(String[] args) {
            switch (args[0]) {
                case "exhaustive" -> {
                    for (int length = 1; length <= 5; length++) {
                        verifyAllStreams(new int[length], 0);
                    }
                }
                case "increasing" -> {
                    MedianFinder_295 finder = new MedianFinder_295();
                    for (int i = 1; i <= 49_999; i++) {
                        finder.addNum(i - 25_001);
                    }
                    assertEquals(-1.0, finder.findMedian(), 1e-9);
                }
                case "random" -> {
                    Random random = new Random(295L);
                    List<Integer> values = new ArrayList<>();
                    MedianFinder_295 finder = new MedianFinder_295();
                    for (int i = 0; i < 25_000; i++) {
                        int value = random.nextInt(200_001) - 100_000;
                        values.add(value);
                        finder.addNum(value);
                    }
                    Collections.sort(values);
                    assertEquals(expectedMedian(values), finder.findMedian(), 1e-9);
                }
                default -> throw new IllegalArgumentException(args[0]);
            }
        }
    }

    private static void assertStream(int[] values, double[] expectedAfterPrefixes) {
        MedianFinder_295 finder = new MedianFinder_295();
        List<Integer> sorted = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            finder.addNum(values[i]);
            sorted.add(values[i]);
            Collections.sort(sorted);
            double expected = expectedMedian(sorted);
            assertEquals(expected, finder.findMedian(), 1e-9,
                    "median after prefix " + (i + 1));
            if (expectedAfterPrefixes != null && i < expectedAfterPrefixes.length) {
                assertEquals(expectedAfterPrefixes[i], expected, 1e-9);
            }
        }
    }

    private static double[] expectedMedians(int[] values) {
        double[] medians = new double[values.length];
        List<Integer> sorted = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            sorted.add(values[i]);
            Collections.sort(sorted);
            medians[i] = expectedMedian(sorted);
        }
        return medians;
    }

    private static double expectedMedian(List<Integer> sorted) {
        int size = sorted.size();
        if ((size & 1) == 1) {
            return sorted.get(size / 2);
        }
        return ((double) sorted.get(size / 2 - 1) + sorted.get(size / 2)) / 2.0;
    }
}
