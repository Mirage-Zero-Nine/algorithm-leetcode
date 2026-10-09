package solutions.treemap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout.ThreadMode;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;

/**
 * @author BorisMirage
 * Time: 2022/11/06 00:07
 * Created with IntelliJ IDEA
 */

@ExtendWith(FrequencySort_451Test.IsolatedTestExtension.class)
public class FrequencySort_451Test {

    private final FrequencySort_451 test = new FrequencySort_451();

    @Test
    public void testHappyCases() {
        assertFrequencySorted("tree", test.frequencySort("tree"));
        assertFrequencySorted("cccaaa", test.frequencySort("cccaaa"));
    }

    @Test
    public void testNegativeTieCase() {
        assertFrequencySorted("Aabb", test.frequencySort("Aabb"));
    }

    @Test
    public void testInvalidAndEdgeCases() {
        assertNull(test.frequencySort(null));
        assertEquals("", test.frequencySort(""));
        assertEquals("a", test.frequencySort("a"));
    }

    @Test
    public void testLargeCase() {
        String input = "a".repeat(40) + "b".repeat(25) + "c".repeat(10);
        assertFrequencySorted(input, test.frequencySort(input));
    }

    @Test
    public void testHappyAllDistinct() {
        assertFrequencySorted("abcdef", test.frequencySort("abcdef"));
    }

    @Test
    public void testHappyDigitsAndLetters() {
        String input = "112233aabb";
        assertFrequencySorted(input, test.frequencySort(input));
    }

    @Test
    public void testHappyAllSameChar() {
        assertEquals("aaaa", test.frequencySort("aaaa"));
    }

    @Test
    public void testNegativeCaseSensitive() {
        // 'A' and 'a' are different characters
        String input = "AaAa";
        assertFrequencySortCase(input);
    }

    @Test
    public void testEdgeTwoChars() {
        assertEquals("aa", test.frequencySort("aa"));
    }

    @Test
    public void testGiantCase() {
        String input = "x".repeat(5000) + "y".repeat(3000) + "z".repeat(1000);
        String result = test.frequencySort(input);
        assertFrequencySorted(input, result);
        assertTrue(result.startsWith("x"));
    }

    @Test
    public void testWhitespaceAndRepeatedCharacters() {
        String input = "  aa bb  ";
        assertFrequencySorted(input, test.frequencySort(input));
    }

    @Test
    public void testPunctuationCharacters() {
        String input = "!!??!!.";
        assertFrequencySorted(input, test.frequencySort(input));
    }

    @Test
    public void testMixedCaseCharacters() {
        String input = "aAaBbBaaa";
        assertFrequencySorted(input, test.frequencySort(input));
    }

    @Test
    public void testEqualFrequencyGroups() {
        String input = "aabbccddeeff";
        assertFrequencySorted(input, test.frequencySort(input));
    }

    @Test
    public void testRepeatedCallsDoNotShareState() {
        // A single solver instance must create fresh local state for each call.
        assertFrequencySorted("aab", test.frequencySort("aab"));
        assertEquals("x", test.frequencySort("x"));
        assertFrequencySorted("bb", test.frequencySort("bb"));
        assertEquals("y", test.frequencySort("y"));
    }

    @Test
    public void testControlCharactersArePreserved() {
        String input = "\n\n\tA";
        assertFrequencySortCase(input);
    }

    @Test
    public void testNumericOnlyInput() {
        String input = "111223333";
        assertFrequencySorted(input, test.frequencySort(input));
    }

    @Test
    public void testSingleCharacter() {
        assertFrequencySortCase("!");
    }

    @Test
    public void testAllCharactersTie() {
        String input = "abcd";
        assertFrequencySorted(input, test.frequencySort(input));
    }

    @Test
    public void testFrequencyOrderWithThreeGroups() {
        String input = "aaabbbcccdde";
        assertFrequencySortCase(input);
    }

    @Test
    public void testUtf16CharactersOutsideAscii() {
        // Intentional Java-UTF-16 extension: LeetCode restricts input to
        // English letters and digits, while this implementation counts char
        // values, including a BMP character and both emoji surrogate units.
        assertFrequencySortCase("\u0100\u0100\u03a9\ud83d\ude00\ud83d\ude00");
    }

    @Test
    @Timeout(value = 10, unit = java.util.concurrent.TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
    public void testExhaustiveShortStringsAgainstIndependentCounts() {
        // Exhaust every string of lengths 0..5 over four distinct code units.
        // The expected result is checked independently by counts and run
        // frequencies, so the solver is checked against an independent
        // contract rather than against another implementation.
        String alphabet = "abC1";
        for (int length = 0; length <= 5; length++) {
            enumerateAndCheck(alphabet, new StringBuilder(), length);
        }
    }

    @Test
    @Timeout(value = 10, unit = java.util.concurrent.TimeUnit.SECONDS, threadMode = ThreadMode.SEPARATE_THREAD)
    public void testMaximumPublishedInput() {
        String dominant = "x".repeat(250_000) + "y".repeat(150_000) + "z".repeat(100_000);
        assertFrequencySortCase(dominant);

        String lettersAndDigits = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder allPublishedCharacters = new StringBuilder(500_000);
        for (int i = 0; i < 500_000; i++) {
            allPublishedCharacters.append(lettersAndDigits.charAt(i % lettersAndDigits.length()));
        }
        assertFrequencySortCase(allPublishedCharacters.toString());
    }

    private void enumerateAndCheck(String alphabet, StringBuilder prefix, int remaining) {
        if (remaining == 0) {
            assertFrequencySortCase(prefix.toString());
            return;
        }
        for (int i = 0; i < alphabet.length(); i++) {
            prefix.append(alphabet.charAt(i));
            enumerateAndCheck(alphabet, prefix, remaining - 1);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    private void assertFrequencySortCase(String input) {
        assertFrequencySorted(input, test.frequencySort(input));
    }

    private static void assertFrequencySorted(String input, String output) {
        assertEquals(input.length(), output.length());

        Map<Character, Integer> inCount = new HashMap<>();
        Map<Character, Integer> outCount = new HashMap<>();
        for (char c : input.toCharArray()) {
            inCount.merge(c, 1, Integer::sum);
        }
        for (char c : output.toCharArray()) {
            outCount.merge(c, 1, Integer::sum);
        }
        assertEquals(inCount, outCount);

        int previous = Integer.MAX_VALUE;
        int index = 0;
        while (index < output.length()) {
            char current = output.charAt(index);
            int count = 0;
            while (index < output.length() && output.charAt(index) == current) {
                index++;
                count++;
            }
            assertEquals(inCount.get(current).intValue(), count);
            assertTrue(count <= previous);
            previous = count;
        }
    }

    /**
     * Runs each named test in a child JVM so a non-cooperative timeout can be forcibly stopped.
     */
    public static final class IsolatedTestExtension implements InvocationInterceptor {
        private static final Duration DEADLINE = Duration.ofSeconds(10);

        @Override
        public void interceptTestMethod(Invocation<Void> invocation,
                                        ReflectiveInvocationContext<Method> context,
                                        ExtensionContext extensionContext) throws Throwable {
            Path output = Files.createTempFile("frequency-sort-test-", ".log");
            List<String> command = List.of(
                    Path.of(System.getProperty("java.home"), "bin", "java").toString(),
                    "-cp",
                    testClasspath(),
                    FrequencySort_451Test.class.getName() + "$Worker",
                    context.getExecutable().getName());
            Process process = new ProcessBuilder(command)
                    .redirectOutput(output.toFile())
                    .redirectErrorStream(true)
                    .start();
            try {
                if (!process.waitFor(DEADLINE.toMillis(), java.util.concurrent.TimeUnit.MILLISECONDS)) {
                    process.destroyForcibly();
                    if (!process.waitFor(2, java.util.concurrent.TimeUnit.SECONDS) || process.isAlive()) {
                        throw new AssertionError("Timed-out child could not be stopped: "
                                + context.getExecutable().getName());
                    }
                    throw new AssertionError("Timed out: " + context.getExecutable().getName()
                            + "\n" + Files.readString(output));
                }
                if (process.exitValue() != 0) {
                    throw new AssertionError("Child test failed: " + context.getExecutable().getName()
                            + "\n" + Files.readString(output));
                }
                invocation.skip();
            } finally {
                if (process.isAlive()) {
                    process.destroyForcibly();
                    if (!process.waitFor(2, java.util.concurrent.TimeUnit.SECONDS) || process.isAlive()) {
                        throw new AssertionError("Child process could not be stopped: "
                                + context.getExecutable().getName());
                    }
                }
                Files.deleteIfExists(output);
            }
        }

        private static String testClasspath() {
            String surefireClasspath = System.getProperty("surefire.test.class.path");
            return surefireClasspath == null ? System.getProperty("java.class.path") : surefireClasspath;
        }
    }

    /**
     * Child entry point for the timeout-boundary extension; it invokes one ordinary test method.
     */
    public static final class Worker {
        public static void main(String[] args) throws Exception {
            if (args.length != 1) {
                throw new IllegalArgumentException("Expected one test method name");
            }
            FrequencySort_451Test test = new FrequencySort_451Test();
            Method method = FrequencySort_451Test.class.getDeclaredMethod(args[0]);
            try {
                method.invoke(test);
            } catch (InvocationTargetException exception) {
                Throwable cause = exception.getCause();
                if (cause instanceof Exception checked) {
                    throw checked;
                }
                if (cause instanceof Error error) {
                    throw error;
                }
                throw exception;
            }
        }
    }
}
