package solutions.stack;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DecodeString_394Test {
    private final DecodeString_394 d = new DecodeString_394();

    @Test
    public void testSimple() {
        assertEquals("accaccacc", d.decodeString("3[a2[c]]"));
    }

    @Test
    public void testNoRepeat() {
        assertEquals("abc", d.decodeString("abc"));
    }

    @Test
    public void testSingleRepeat() {
        assertEquals("aaa", d.decodeString("3[a]"));
    }

    @Test
    public void testNull() {
        assertNull(d.decodeString(null));
    }

    @Test
    public void testEmpty() {
        assertEquals("", d.decodeString(""));
    }

    @Test
    public void testEmptyBracketBodyKeepsSurroundingText() {
        assertEquals("a", d.decodeString("2[]a"));
    }

    @Test
    public void testMultipleRepeat() {
        assertEquals("ababab", d.decodeString("3[ab]"));
    }

    @Test
    public void testNestedDeep() {
        assertEquals("aaaaaa", d.decodeString("2[3[a]]"));
    }

    @Test
    public void testAdjacentBrackets() {
        assertEquals("aaabbb", d.decodeString("3[a]3[b]"));
    }

    @Test
    public void testMixedTextAndBrackets() {
        assertEquals("xaaay", d.decodeString("x3[a]y"));
    }

    @Test
    public void testDoubleDigitRepeat() {
        assertEquals("a".repeat(12), d.decodeString("12[a]"));
    }

    @Test
    public void testGiant() {
        // 100[ab] = "ab" repeated 100 times
        assertEquals("ab".repeat(100), d.decodeString("100[ab]"));
    }

    @Test
    public void testAdjacentWithTrailingLetters() {
        // 2[abc]3[cd]ef -> abcabccdcdcdef
        assertEquals("abcabccdcdcdef", d.decodeString("2[abc]3[cd]ef"));
    }

    @Test
    public void testTenRepeat() {
        // 10[a] -> aaaaaaaaaa
        assertEquals("a".repeat(10), d.decodeString("10[a]"));
    }

    @Test
    public void testMultiDigitNested() {
        // 12[a3[b]] -> (abbb) repeated 12 times
        assertEquals("abbb".repeat(12), d.decodeString("12[a3[b]]"));
    }

    @Test
    public void testDeepNesting5Levels() {
        // 2[a2[b2[c2[d2[e]]]]] - 5 levels deep
        // innermost: 2[e] = ee
        // 2[dee] = deedee
        // 2[cdeedee] = cdeedeecdeedee
        // 2[bcdeedeecdeedee] = bcdeedeecdeede ebcdeedeecdeedee
        // 2[a...] = ...
        String l5 = "ee";
        String l4 = ("d" + l5).repeat(2);
        String l3 = ("c" + l4).repeat(2);
        String l2 = ("b" + l3).repeat(2);
        String l1 = ("a" + l2).repeat(2);
        assertEquals(l1, d.decodeString("2[a2[b2[c2[d2[e]]]]]"));
    }

    @Test
    public void testSingleRepetition() {
        // 1[a] -> a
        assertEquals("a", d.decodeString("1[a]"));
    }

    @Test
    public void testSingleRepetitionLonger() {
        // 1[abc] -> abc
        assertEquals("abc", d.decodeString("1[abc]"));
    }

    @Test
    public void testOnlyLettersLong() {
        // No brackets at all, long string
        assertEquals("abcdefghij", d.decodeString("abcdefghij"));
    }

    @Test
    public void testMaximumLiteralInputAndRepeatedCalls() {
        String maximumLiteralInput = "a".repeat(30);
        assertEquals(maximumLiteralInput, d.decodeString(maximumLiteralInput));
        assertEquals("zz", d.decodeString("2[z]"));
        assertEquals("", d.decodeString(""));
    }

    @Test
    public void testResultLengthProperty() {
        // 3[a2[c]] -> accaccacc, length = 3 * (1 + 2*1) = 9
        String result = d.decodeString("3[a2[c]]");
        assertEquals(9, result.length());
        assertEquals("accaccacc", result);
    }

    @Test
    public void testLargeMultiDigit() {
        // 50[ab] -> "ab" * 50, length 100
        String result = d.decodeString("50[ab]");
        assertEquals(100, result.length());
        assertEquals("ab".repeat(50), result);
    }

    @Test
    public void testRepeatWithExistingPrefixAndSuffix() {
        assertEquals("prefixxyxy-suffix", d.decodeString("prefix2[xy]-suffix"));
    }

    @Test
    public void testMaximumDecodedLengthWithinPublishedConstraints() throws Exception {
        runWorker("stress");
    }

    @Test
    public void testExhaustiveShortSequencesOfEncodedAtoms() throws Exception {
        runWorker("exhaustive");
    }

    private void runWorker(String mode) throws Exception {
        String java = System.getProperty("java.home") + "/bin/java";
        String classPath = System.getProperty("surefire.test.class.path", System.getProperty("java.class.path"));
        Process process = new ProcessBuilder(java, "-cp", classPath, DecodeString_394Worker.class.getName(), mode)
                .redirectErrorStream(true).start();
        if (!process.waitFor(10, TimeUnit.SECONDS)) {
            process.destroyForcibly();
            if (!process.waitFor(2, TimeUnit.SECONDS)) {
                throw new AssertionError("worker could not be terminated after timeout: " + mode);
            }
            throw new AssertionError("worker timed out: " + mode);
        }
        String output;
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
            output = reader.lines().reduce("", (left, right) -> left + right);
        }
        assertEquals(0, process.exitValue(), mode + ": " + output);
        assertTrue(output.contains("OK"), mode + ": " + output);
    }

    public static final class DecodeString_394Worker {
        static void main(String[] args) {
            if ("exhaustive".equals(args[0])) {
                exhaustiveShortSequences();
            } else {
                stressWithinConstraints();
            }
            System.out.println("OK");
        }

        private static void exhaustiveShortSequences() {
            // Exhaust ordered sequences of one to three atoms; expected strings come from this independent table.
            List<EncodedAtom> atoms = List.of(
                    new EncodedAtom("a", "a"),
                    new EncodedAtom("b", "b"),
                    new EncodedAtom("1[a]", "a"),
                    new EncodedAtom("2[a]", "aa"),
                    new EncodedAtom("3[a]", "aaa"),
                    new EncodedAtom("1[b]", "b"),
                    new EncodedAtom("2[b]", "bb"),
                    new EncodedAtom("3[b]", "bbb"),
                    new EncodedAtom("2[ab]", "abab"),
                    new EncodedAtom("2[a2[b]]", "abbabb"));
            DecodeString_394 decoder = new DecodeString_394();
            for (int sequenceLength = 1; sequenceLength <= 3; sequenceLength++) {
                assertAtomSequences(decoder, atoms, sequenceLength, new StringBuilder(), new StringBuilder());
            }
        }

        private static void assertAtomSequences(DecodeString_394 decoder, List<EncodedAtom> atoms, int remaining,
                                                StringBuilder encoded, StringBuilder expected) {
            if (remaining == 0) {
                String input = encoded.toString();
                String actual = decoder.decodeString(input);
                if (!expected.toString().equals(actual)) {
                    throw new AssertionError("input=" + input + ", expected=" + expected + ", actual=" + actual);
                }
                return;
            }

            for (EncodedAtom atom : atoms) {
                int encodedLength = encoded.length();
                int expectedLength = expected.length();
                encoded.append(atom.encoded());
                expected.append(atom.decoded());
                assertAtomSequences(decoder, atoms, remaining - 1, encoded, expected);
                encoded.setLength(encodedLength);
                expected.setLength(expectedLength);
            }
        }

        private static void stressWithinConstraints() {
            String expected = ("ab" + "c".repeat(300)).repeat(300);
            String actual = new DecodeString_394().decodeString("300[ab300[c]]");
            if (actual.length() != 90_600 || !expected.equals(actual)) {
                throw new AssertionError("maximum decoded-size case mismatch");
            }

            String maximumOutput = "a".repeat(100_000);
            actual = new DecodeString_394().decodeString("10[100[100[a]]]");
            if (!maximumOutput.equals(actual)) {
                throw new AssertionError("100,000-character output case mismatch");
            }

            StringBuilder encoded = new StringBuilder();
            int nestingDepth = 7;
            encoded.repeat("1[a", nestingDepth);
            encoded.append('b').repeat("]", nestingDepth);
            String nestedExpected = "a".repeat(nestingDepth) + "b";
            actual = new DecodeString_394().decodeString(encoded.toString());
            if (!nestedExpected.equals(actual)) {
                throw new AssertionError("nested single-repeat case mismatch");
            }
        }

        private record EncodedAtom(String encoded, String decoded) {
        }
    }
}
