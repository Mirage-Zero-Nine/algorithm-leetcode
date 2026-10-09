package solutions.heap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;

import java.util.Arrays;
import java.util.Random;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(FindKthLargest_215Test.IsolatedTestExtension.class)
public class FindKthLargest_215Test {
    private final FindKthLargest_215 solver = new FindKthLargest_215();

    @Test
    public void testBasic() {
        assertBoth(new int[]{3, 2, 1, 5, 6, 4}, 2, 5);
    }

    @Test
    public void testDuplicates() {
        assertBoth(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4, 4);
    }

    @Test
    public void testSingleElement() {
        assertBoth(new int[]{7}, 1, 7);
    }

    @Test
    public void testKIsLength() {
        assertBoth(new int[]{3, 2, 1, 5, 6, 4}, 6, 1);
    }

    @Test
    public void testMinHeapApproach() {
        assertBoth(new int[]{3, 2, 1, 5, 6, 4}, 2, 5);
    }

    @Test
    public void testMinHeapWithDuplicates() {
        assertBoth(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4, 4);
    }

    @Test
    public void testNegativeNumbers() {
        assertBoth(new int[]{-1, -2, -3, -4}, 1, -1);
    }

    @Test
    public void testAllSame() {
        assertBoth(new int[]{5, 5, 5, 5}, 2, 5);
    }

    @Test
    public void testMinHeapSingle() {
        assertBoth(new int[]{7}, 1, 7);
    }

    @Test
    public void testMinHeapKIsLength() {
        assertBoth(new int[]{3, 2, 1, 5, 6, 4}, 6, 1);
    }

    @Test
    public void testGiantCase() {
        int[] nums = new int[10000];
        for (int i = 0; i < 10000; i++) nums[i] = i;
        assertBoth(nums, 1, 9999);
        assertBoth(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, 10, 0);
    }

    @Test
    public void testMinHeapNegative() {
        assertBoth(new int[]{-1, -2, -3, -4}, 2, -2);
    }

    @Test
    public void testKEqualsOneIsMaximum() {
        assertEquals(9, solver.findKthLargestPartition(new int[]{1, 3, 5, 7, 9}, 1));
        assertEquals(9, solver.findKthLargest(new int[]{1, 3, 5, 7, 9}, 1));
    }

    @Test
    public void testSortedAscending() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8};
        assertEquals(6, solver.findKthLargestPartition(nums.clone(), 3));
        assertEquals(6, solver.findKthLargest(nums.clone(), 3));
    }

    @Test
    public void testSortedDescending() {
        int[] nums = {8, 7, 6, 5, 4, 3, 2, 1};
        assertEquals(6, solver.findKthLargestPartition(nums.clone(), 3));
        assertEquals(6, solver.findKthLargest(nums.clone(), 3));
    }

    @Test
    public void testMixPositiveNegative() {
        int[] nums = {-3, 2, -1, 5, 0, -4, 3};
        // sorted desc: 5, 3, 2, 0, -1, -3, -4 -> k=4 -> 0
        assertEquals(0, solver.findKthLargestPartition(nums.clone(), 4));
        assertEquals(0, solver.findKthLargest(nums.clone(), 4));
    }

    @Test
    public void testAllSameBothImpls() {
        int[] nums = {2, 2, 2, 2, 2};
        assertEquals(2, solver.findKthLargestPartition(nums.clone(), 1));
        assertEquals(2, solver.findKthLargestPartition(nums.clone(), 5));
        assertEquals(2, solver.findKthLargest(nums.clone(), 1));
        assertEquals(2, solver.findKthLargest(nums.clone(), 5));
    }

    @Test
    public void testLeetCodeDuplicateExample() {
        // LeetCode example: [3,2,3,1,2,4,5,5,6] k=4 -> 4
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        assertEquals(4, solver.findKthLargestPartition(nums.clone(), 4));
        assertEquals(4, solver.findKthLargest(nums.clone(), 4));
        // k=9 -> minimum = 1
        assertEquals(1, solver.findKthLargestPartition(nums.clone(), 9));
        assertEquals(1, solver.findKthLargest(nums.clone(), 9));
    }

    @Test
    public void testLargeArrayRandomSeed42() {
        Random rng = new Random(42L);
        int[] nums = new int[10000];
        for (int i = 0; i < nums.length; i++) nums[i] = rng.nextInt();
        int k = 500;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        int expected = sorted[sorted.length - k]; // kth largest
        assertEquals(expected, solver.findKthLargestPartition(nums.clone(), k));
        assertEquals(expected, solver.findKthLargest(nums.clone(), k));
    }

    @Test
    public void testPropertySortedDescKMinus1() {
        int[] nums = {10, 4, 3, 8, 7, 1, 12, 6};
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        for (int k = 1; k <= nums.length; k++) {
            int expected = sorted[sorted.length - k];
            assertEquals(expected, solver.findKthLargestPartition(nums.clone(), k), "quickselect k=" + k);
            assertEquals(expected, solver.findKthLargest(nums.clone(), k), "minheap k=" + k);
        }
    }

    @Test
    public void testAllNegativeValues() {
        int[] nums = {-10, -20, -30, -5, -15};
        // sorted desc: -5, -10, -15, -20, -30
        assertEquals(-5, solver.findKthLargestPartition(nums.clone(), 1));
        assertEquals(-30, solver.findKthLargestPartition(nums.clone(), 5));
        assertEquals(-5, solver.findKthLargest(nums.clone(), 1));
        assertEquals(-30, solver.findKthLargest(nums.clone(), 5));
    }

    @Test
    public void testMinHeapAllSameKIsLength() {
        assertBoth(new int[]{3, 3, 3, 3}, 4, 3);
    }

    @Test
    public void testInvalidRanksReturnSentinelForBothApproaches() {
        assertBoth(new int[]{4, 1, 9}, 0, -1);
        assertBoth(new int[]{4, 1, 9}, -2, -1);
        assertBoth(new int[]{4, 1, 9}, 4, -1);
        assertBoth(new int[]{}, 1, -1);
        assertEquals(-1, solver.findKthLargest(null, 1));
        assertEquals(-1, solver.findKthLargestPartition(null, 1));
    }

    @Test
    public void testIntegerBoundaries() {
        // Full int-range values are a supported comparison-based extension;
        // LeetCode's ordinary value bounds are narrower.
        assertBoth(new int[]{Integer.MIN_VALUE, 0, Integer.MAX_VALUE, -1}, 1, Integer.MAX_VALUE);
        assertBoth(new int[]{Integer.MIN_VALUE, 0, Integer.MAX_VALUE, -1}, 2, 0);
        assertBoth(new int[]{Integer.MIN_VALUE, 0, Integer.MAX_VALUE, -1}, 4, Integer.MIN_VALUE);
    }

    @Test
    public void testPartitionMutatesInputButHeapDoesNot() {
        int[] partitionInput = {1, 9, 2, 8, 3, 7};
        int[] partitionOriginal = partitionInput.clone();
        int result = solver.findKthLargestPartition(partitionInput, 2);
        assertEquals(8, result);
        int[] sortedOriginal = partitionOriginal.clone();
        int[] sortedAfter = partitionInput.clone();
        Arrays.sort(sortedOriginal);
        Arrays.sort(sortedAfter);
        org.junit.jupiter.api.Assertions.assertArrayEquals(sortedOriginal, sortedAfter);
        int greaterCount = 0;
        int greaterOrEqualCount = 0;
        for (int value : partitionInput) {
            if (value > result) greaterCount++;
            if (value >= result) greaterOrEqualCount++;
        }
        org.junit.jupiter.api.Assertions.assertTrue(greaterCount < 2);
        org.junit.jupiter.api.Assertions.assertTrue(greaterOrEqualCount >= 2);

        int[] heapInput = {1, 9, 2, 8, 3, 7};
        int[] heapOriginal = heapInput.clone();
        assertEquals(8, solver.findKthLargest(heapInput, 2));
        org.junit.jupiter.api.Assertions.assertArrayEquals(heapOriginal, heapInput);
    }

    @Test
    public void testRepeatedCallsDoNotShareState() {
        int[] first = {10, 4, 7, 2};
        assertEquals(7, solver.findKthLargestPartition(first, 2));
        assertEquals(2, solver.findKthLargestPartition(new int[]{10, 4, 7, 2}, 4));
        assertEquals(10, solver.findKthLargest(new int[]{10, 4, 7, 2}, 1));
        assertEquals(4, solver.findKthLargest(new int[]{10, 4, 7, 2}, 3));
    }

    @Test
    public void testExhaustiveSmallArraysAgainstSortedOracle() {
        int[] values = {-2, 0, 3};
        for (int length = 1; length <= 5; length++) {
            int combinations = (int) Math.pow(values.length, length);
            for (int code = 0; code < combinations; code++) {
                int[] input = new int[length];
                int remaining = code;
                for (int index = 0; index < length; index++) {
                    input[index] = values[remaining % values.length];
                    remaining /= values.length;
                }
                int[] sorted = input.clone();
                Arrays.sort(sorted);
                for (int k = 1; k <= length; k++) {
                    int expected = sorted[length - k];
                    assertEquals(expected, solver.findKthLargestPartition(input.clone(), k),
                            "partition input=" + Arrays.toString(input) + ", k=" + k);
                    assertEquals(expected, solver.findKthLargest(input.clone(), k),
                            "heap input=" + Arrays.toString(input) + ", k=" + k);
                }
            }
        }
    }

    @Test
    public void testAdversarialDuplicateAndOrderedInputs() {
        int[] duplicateBlocks = new int[50000];
        Arrays.fill(duplicateBlocks, 7);
        duplicateBlocks[0] = Integer.MIN_VALUE;
        duplicateBlocks[duplicateBlocks.length - 1] = Integer.MAX_VALUE;
        assertBoth(duplicateBlocks, 2, 7);

        int[] descending = new int[20000];
        for (int i = 0; i < descending.length; i++) {
            descending[i] = descending.length - i;
        }
        assertBoth(descending, 10000, 10001);
    }

    @Test
    public void testMaximumSizedInput() {
        int[] input = new int[100000];
        for (int i = 0; i < input.length; i++) {
            input[i] = (i * 1103515245) ^ (i >>> 3);
        }
        int[] sorted = input.clone();
        Arrays.sort(sorted);
        int k = 54321;
        int expected = sorted[sorted.length - k];
        assertEquals(expected, solver.findKthLargestPartition(input.clone(), k));
        assertEquals(expected, solver.findKthLargest(input.clone(), k));
    }

    @Test
    public void testSeededRandomArraysAcrossRanks() {
        Random random = new Random(215L);
        for (int trial = 0; trial < 100; trial++) {
            int length = 1 + random.nextInt(40);
            int[] input = new int[length];
            for (int i = 0; i < length; i++) {
                input[i] = random.nextInt(9) - 4;
            }
            int[] sorted = input.clone();
            Arrays.sort(sorted);
            int k = 1 + random.nextInt(length);
            int expected = sorted[length - k];
            assertEquals(expected, solver.findKthLargestPartition(input.clone(), k));
            assertEquals(expected, solver.findKthLargest(input.clone(), k));
        }
    }

    private void assertBoth(int[] input, int k, int expected) {
        assertEquals(expected, solver.findKthLargestPartition(input.clone(), k),
                "partition input=" + Arrays.toString(input) + ", k=" + k);
        assertEquals(expected, solver.findKthLargest(input.clone(), k),
                "heap input=" + Arrays.toString(input) + ", k=" + k);
    }

    /**
     * Runs each ordinary test method in a separate JVM so a hung algorithm can
     * be forcibly terminated. The child invokes only the selected method, so
     * it does not recursively activate this JUnit extension.
     */
    public static final class IsolatedTestExtension implements InvocationInterceptor {
        private static final Duration DEADLINE = Duration.ofSeconds(10);

        @Override
        public void interceptTestMethod(Invocation<Void> invocation,
                                        ReflectiveInvocationContext<Method> context,
                                        ExtensionContext extensionContext) throws Throwable {
            Path output = Files.createTempFile("find-kth-largest-test-", ".log");
            List<String> command = List.of(
                    javaCommand(),
                    "-cp",
                    testClasspath(),
                    FindKthLargest_215Test.class.getName() + "$Worker",
                    context.getExecutable().getName());
            Process process = new ProcessBuilder(command)
                    .redirectOutput(output.toFile())
                    .redirectErrorStream(true)
                    .start();
            try {
                if (!process.waitFor(DEADLINE.toMillis(), java.util.concurrent.TimeUnit.MILLISECONDS)) {
                    process.destroyForcibly();
                    process.waitFor(2, java.util.concurrent.TimeUnit.SECONDS);
                    throw new AssertionError("Timed out: " + context.getExecutable().getName()
                            + "\n" + Files.readString(output));
                }
                if (process.exitValue() != 0) {
                    throw new AssertionError("Child test failed: " + context.getExecutable().getName()
                            + "\n" + Files.readString(output));
                }
                // The child already executed this method; prevent the parent
                // JUnit engine from invoking the body a second time.
                invocation.skip();
            } finally {
                Files.deleteIfExists(output);
            }
        }

        private static String javaCommand() {
            return Path.of(System.getProperty("java.home"), "bin", "java").toString();
        }

        private static String testClasspath() {
            String surefireClasspath = System.getProperty("surefire.test.class.path");
            return surefireClasspath == null ? System.getProperty("java.class.path") : surefireClasspath;
        }
    }

    /** Child-process entry point used by {@link IsolatedTestExtension}. */
    public static final class Worker {
        public static void main(String[] args) throws Exception {
            if (args.length != 1) {
                throw new IllegalArgumentException("Expected one test method name");
            }
            FindKthLargest_215Test test = new FindKthLargest_215Test();
            Method method = FindKthLargest_215Test.class.getDeclaredMethod(args[0]);
            try {
                method.invoke(test);
            } catch (InvocationTargetException exception) {
                Throwable cause = exception.getCause();
                if (cause instanceof Exception checked) throw checked;
                if (cause instanceof Error error) throw error;
                throw exception;
            }
        }
    }
}
