package solutions.twopointers;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class FindDuplicate_287Test {

    @Test
    void findsClassicExample() { runInIsolatedJvm("findsClassicExample", () -> assertDuplicate(2, 1, 3, 4, 2, 2)); }

    @Test
    void findsDuplicateAtStart() { runInIsolatedJvm("findsDuplicateAtStart", () -> assertDuplicate(2, 2, 1, 3, 2)); }

    @Test
    void findsDuplicateAtEnd() { runInIsolatedJvm("findsDuplicateAtEnd", () -> assertDuplicate(3, 1, 2, 3, 3)); }

    @Test
    void findsValueRepeatedMoreThanTwice() { runInIsolatedJvm("findsValueRepeatedMoreThanTwice", () -> assertDuplicate(3, 3, 3, 3, 3)); }

    @Test
    void handlesMinimumValidArray() { runInIsolatedJvm("handlesMinimumValidArray", () -> assertDuplicate(1, 1, 1)); }

    @Test
    void handlesMissingValuesBeforeDuplicate() { runInIsolatedJvm("handlesMissingValuesBeforeDuplicate", () -> assertDuplicate(5, 1, 5, 3, 4, 2, 5)); }

    @Test
    void handlesAllValuesWithOneRepeated() { runInIsolatedJvm("handlesAllValuesWithOneRepeated", () -> assertDuplicate(3, 1, 2, 3, 4, 5, 3)); }

    @Test
    void handlesDuplicateNearEnd() { runInIsolatedJvm("handlesDuplicateNearEnd", () -> assertDuplicate(5, 1, 2, 3, 4, 5, 5)); }

    @Test
    void handlesDuplicateNearBeginning() { runInIsolatedJvm("handlesDuplicateNearBeginning", () -> assertDuplicate(2, 2, 1, 3, 4, 5, 2)); }

    @Test
    void handlesDuplicateInMiddle() { runInIsolatedJvm("handlesDuplicateInMiddle", () -> assertDuplicate(3, 1, 2, 3, 4, 3, 5, 6)); }

    @Test
    void handlesHistoricalRangePropertyInput() {
        runInIsolatedJvm("handlesHistoricalRangePropertyInput", () -> assertDuplicate(3, 1, 2, 3, 4, 5, 6, 7, 3, 8, 9, 10));
    }

    @Test
    void handlesHistoricalRepeatedResultPropertyInput() {
        runInIsolatedJvm("handlesHistoricalRepeatedResultPropertyInput", () -> assertDuplicate(5, 5, 1, 4, 2, 5, 3));
    }

    @Test
    void handlesSeparatedCycle() { runInIsolatedJvm("handlesSeparatedCycle", () -> assertDuplicate(3, 4, 1, 6, 3, 5, 2, 3)); }

    @Test
    void handlesUnorderedArray() { runInIsolatedJvm("handlesUnorderedArray", () -> assertDuplicate(2, 4, 2, 1, 3, 2)); }

    @Test
    void handlesMaximumValueAsDuplicate() { runInIsolatedJvm("handlesMaximumValueAsDuplicate", () -> assertDuplicate(8, 7, 1, 8, 3, 6, 4, 2, 5, 8)); }

    @Test
    void handlesSmallestNontrivialDuplicate() { runInIsolatedJvm("handlesSmallestNontrivialDuplicate", () -> assertDuplicate(1, 4, 2, 1, 3, 1)); }

    @Test
    void leavesReadOnlyInputUnchanged() {
        runInIsolatedJvm("leavesReadOnlyInputUnchanged", () -> {
            int[] nums = {3, 1, 3, 4, 2};
            int[] original = nums.clone();
            assertEquals(3, new FindDuplicate_287().findDuplicate(nums));
            assertArrayEquals(original, nums);
        });
    }

    @Test
    void supportsRepeatedCallsOnOneSolver() {
        runInIsolatedJvm("supportsRepeatedCallsOnOneSolver", () -> {
            FindDuplicate_287 solver = new FindDuplicate_287();
            assertEquals(2, solver.findDuplicate(new int[]{1, 3, 4, 2, 2}));
            assertEquals(4, solver.findDuplicate(new int[]{4, 1, 2, 3, 4}));
            assertEquals(1, solver.findDuplicate(new int[]{2, 4, 1, 3, 1}));
        });
    }

    @Test
    void returnsMinusOneForSupportedNullAndEmptyExtension() {
        runInIsolatedJvm("returnsMinusOneForSupportedNullAndEmptyExtension", () -> {
            FindDuplicate_287 solver = new FindDuplicate_287();
            assertEquals(-1, solver.findDuplicate(null));
            assertEquals(-1, solver.findDuplicate(new int[0]));
        });
    }

    @Test
    void exhaustivelyChecksSmallDomainsAgainstFrequencyOracle() {
        runInIsolatedJvm("exhaustivelyChecksSmallDomainsAgainstFrequencyOracle", () -> {
            // Every array for n <= 5 is generated; invalid multi-duplicate
            // arrays are ignored by the independent contract oracle.
            for (int n = 1; n <= 5; n++) {
                enumerateAndCheck(new int[n + 1], 0, n);
            }
        });
    }

    @Test
    void handlesLargeInputWithinContract() {
        int n = 100_000;
        int duplicate = 50_000;
        int[] nums = new int[n + 1];
        for (int i = 0; i < n; i++) nums[i] = i + 1;
        nums[n] = duplicate;
        runInIsolatedJvm("handlesLargeInputWithinContract",
                () -> assertEquals(duplicate, new FindDuplicate_287().findDuplicate(nums)));
    }

    @Test
    void handlesHistoricalTenThousandElementInput() {
        int n = 10_000;
        int duplicate = 4_217;
        int[] nums = new int[n + 1];
        for (int i = 0; i < n; i++) nums[i] = i + 1;
        nums[n] = duplicate;
        shuffle(nums, new Random(42L));
        runInIsolatedJvm("handlesHistoricalTenThousandElementInput",
                () -> assertEquals(duplicate, new FindDuplicate_287().findDuplicate(nums)));
    }

    public static void main(String[] args) throws Exception {
        if (!Boolean.getBoolean("findDuplicate.child") || args.length != 1) {
            throw new IllegalArgumentException("child JVM requires exactly one test method");
        }
        FindDuplicate_287Test test = new FindDuplicate_287Test();
        Method method = FindDuplicate_287Test.class.getDeclaredMethod(args[0]);
        try {
            method.invoke(test);
        } catch (InvocationTargetException exception) {
            Throwable cause = exception.getCause();
            if (cause instanceof Error error) throw error;
            if (cause instanceof Exception exceptionCause) throw exceptionCause;
            throw exception;
        }
    }

    private static void runInIsolatedJvm(String methodName, Runnable body) {
        if (Boolean.getBoolean("findDuplicate.child")) {
            body.run();
            return;
        }
        Path output = null;
        Process process = null;
        try {
            output = Files.createTempFile("find-duplicate-test-", ".log");
            String classPath = System.getProperty("surefire.test.class.path",
                    System.getProperty("java.class.path"));
            process = new ProcessBuilder(
                    Path.of(System.getProperty("java.home"), "bin", "java").toString(),
                    "-DfindDuplicate.child=true", "-cp", classPath,
                    FindDuplicate_287Test.class.getName(), methodName)
                    .redirectErrorStream(true)
                    .redirectOutput(output.toFile())
                    .start();
            if (!process.waitFor(10, TimeUnit.SECONDS)) {
                process.destroyForcibly();
                if (!process.waitFor(5, TimeUnit.SECONDS)) {
                    fail("child JVM did not terminate after forced cleanup: " + methodName);
                }
                fail("child JVM timed out: " + methodName + "\n" + readOutput(output));
            }
            if (process.exitValue() != 0) {
                fail("child JVM failed for " + methodName + " (exit " + process.exitValue()
                        + ")\n" + readOutput(output));
            }
        } catch (IOException | InterruptedException exception) {
            Thread.currentThread().interrupt();
            fail("unable to run child JVM for " + methodName, exception);
        } finally {
            if (process != null && process.isAlive()) {
                process.destroyForcibly();
                try {
                    if (!process.waitFor(5, TimeUnit.SECONDS)) {
                        fail("child JVM remained alive during final cleanup: " + methodName);
                    }
                } catch (InterruptedException exception) {
                    Thread.currentThread().interrupt();
                    fail("interrupted while cleaning child JVM: " + methodName, exception);
                }
            }
            if (output != null) {
                try {
                    Files.deleteIfExists(output);
                } catch (IOException exception) {
                    fail("unable to delete child output: " + output, exception);
                }
            }
        }
    }

    private static String readOutput(Path output) {
        try {
            return Files.readString(output);
        } catch (IOException exception) {
            return "<unable to read child output: " + exception + ">";
        }
    }

    private static void enumerateAndCheck(int[] nums, int index, int n) {
        if (index == nums.length) {
            int expected = independentDuplicate(nums, n);
            if (expected != -1) {
                int[] fresh = nums.clone();
                assertEquals(expected, new FindDuplicate_287().findDuplicate(fresh),
                        "failed input: " + Arrays.toString(nums));
                assertArrayEquals(nums, fresh);
            }
            return;
        }
        for (int value = 1; value <= n; value++) {
            nums[index] = value;
            enumerateAndCheck(nums, index + 1, n);
        }
    }

    private static int independentDuplicate(int[] nums, int n) {
        int repeated = -1;
        for (int value = 1; value <= n; value++) {
            int count = 0;
            for (int num : nums) if (num == value) count++;
            if (count > 1) {
                if (repeated != -1) return -1;
                repeated = value;
            }
        }
        return repeated;
    }

    private static void shuffle(int[] nums, Random random) {
        for (int i = nums.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int value = nums[i];
            nums[i] = nums[j];
            nums[j] = value;
        }
    }

    private static void assertDuplicate(int expected, int... nums) {
        assertEquals(expected, new FindDuplicate_287().findDuplicate(nums));
    }
}
