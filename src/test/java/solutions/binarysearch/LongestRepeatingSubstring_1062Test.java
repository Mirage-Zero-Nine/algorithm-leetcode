package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class LongestRepeatingSubstring_1062Test {

    private final LongestRepeatingSubstring_1062 test = new LongestRepeatingSubstring_1062();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.longestRepeatingSubstring("aabcaabdaab"));
        assertEquals(3, test.longestRepeatingSubstring("abcabc"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.longestRepeatingSubstring("a"));
        assertEquals(0, test.longestRepeatingSubstring("abcd"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(6, test.longestRepeatingSubstring("aaaaaaa"));
    }

    @Test
    public void testTwoSameCharacters() {
        assertEquals(1, test.longestRepeatingSubstring("aa"));
    }

    @Test
    public void testTwoDifferentCharacters() {
        assertEquals(0, test.longestRepeatingSubstring("ab"));
    }

    @Test
    public void testOverlapRepeatingSubstring() {
        assertEquals(3, test.longestRepeatingSubstring("ababa"));
    }

    @Test
    public void testRepeatedPrefix() {
        assertEquals(6, test.longestRepeatingSubstring("abcabcabc"));
    }

    @Test
    public void testNoRepeatBeyondSingleCharacter() {
        assertEquals(1, test.longestRepeatingSubstring("abca"));
    }

    @Test
    public void testExactRepeatBlock() {
        assertEquals(3, test.longestRepeatingSubstring("xyzxyz"));
    }

    @Test
    public void testGiantCase() {
        String s = "a".repeat(1500);
        assertEquals(1499, test.longestRepeatingSubstring(s));
    }

    @Test
    public void testOfficialExamples() {
        assertEquals(0, test.longestRepeatingSubstring("abcd"));
        assertEquals(2, test.longestRepeatingSubstring("abbaba"));
        assertEquals(3, test.longestRepeatingSubstring("aabcaabdaab"));
    }

    @Test
    public void testEmptyAndMinimumInputs() {
        assertEquals(0, test.longestRepeatingSubstring(""));
        assertEquals(0, test.longestRepeatingSubstring("z"));
        assertEquals(0, test.longestRepeatingSubstring("az"));
        assertEquals(1, test.longestRepeatingSubstring("zz"));
    }

    @Test
    public void testOverlappingOccurrences() {
        assertEquals(3, test.longestRepeatingSubstring("ababa"));
        assertEquals(5, test.longestRepeatingSubstring("abababa"));
        assertEquals(3, test.longestRepeatingSubstring("banana"));
        assertEquals(4, test.longestRepeatingSubstring("aaaaa"));
        assertEquals(3, test.longestRepeatingSubstring("aaaa"));
    }

    @Test
    public void testRepeatedBlocksAndInteriorMatches() {
        assertEquals(3, test.longestRepeatingSubstring("abcabc"));
        assertEquals(6, test.longestRepeatingSubstring("abcabcabc"));
        assertEquals(3, test.longestRepeatingSubstring("xyzxyz"));
        assertEquals(3, test.longestRepeatingSubstring("abcdabce"));
        assertEquals(1, test.longestRepeatingSubstring("abcddcba"));
        assertEquals(2, test.longestRepeatingSubstring("aabaa"));
    }

    @Test
    public void testNoRepeatAndSingleCharacterRepeatNegatives() {
        assertEquals(0, test.longestRepeatingSubstring("abcdefg"));
        assertEquals(0, test.longestRepeatingSubstring("abcdefghijklmnopqrstuvwxyz"));
        assertEquals(1, test.longestRepeatingSubstring("abca"));
        assertEquals(1, test.longestRepeatingSubstring("abcb"));
        assertEquals(1, test.longestRepeatingSubstring("aabbcc"));
    }

    @Test
    public void testMaximumPeriodicInput() {
        assertEquals(1497, test.longestRepeatingSubstring("abc".repeat(500)));
    }

    @Test
    public void testMaximumLongRunWithTrailingDifferentCharacter() {
        assertEquals(1498, test.longestRepeatingSubstring("a".repeat(1499) + "b"));
    }

    @Test
    public void testExhaustiveBinaryAlphabetAgainstIndependentOracle() {
        for (int length = 0; length <= 8; length++) {
            int inputs = 1 << length;
            for (int mask = 0; mask < inputs; mask++) {
                char[] chars = new char[length];
                for (int index = 0; index < length; index++) {
                    chars[index] = ((mask >>> index) & 1) == 0 ? 'a' : 'b';
                }
                String input = new String(chars);
                assertEquals(bruteForceLongestRepeatingSubstring(input),
                        test.longestRepeatingSubstring(input), "input=" + input);
            }
        }
    }

    @Test
    public void testSeededRandomInputsAgainstIndependentOracle() {
        Random random = new Random(1062L);
        for (int caseNumber = 0; caseNumber < 250; caseNumber++) {
            int length = random.nextInt(12);
            StringBuilder input = new StringBuilder(length);
            for (int index = 0; index < length; index++) {
                input.append((char) ('a' + random.nextInt(4)));
            }
            String value = input.toString();
            assertEquals(bruteForceLongestRepeatingSubstring(value),
                    test.longestRepeatingSubstring(value), "case=" + caseNumber + ", input=" + value);
        }
    }

    @Test
    public void testSameInstanceCanBeReusedWithoutStateLeakage() {
        assertEquals(4, test.longestRepeatingSubstring("aaaaa"));
        assertEquals(0, test.longestRepeatingSubstring("qwerty"));
        assertEquals(2, test.longestRepeatingSubstring("aabaa"));
        assertEquals(0, test.longestRepeatingSubstring(""));
        assertEquals(1, test.longestRepeatingSubstring("aa"));
    }

    /**
     * Enumerates every substring length and uses exact strings as keys, independently of the
     * production rolling hash. Overlap is naturally allowed because every start is considered.
     */
    private static int bruteForceLongestRepeatingSubstring(String value) {
        int best = 0;
        for (int length = 1; length < value.length(); length++) {
            Set<String> seen = new HashSet<>();
            for (int start = 0; start + length <= value.length(); start++) {
                if (!seen.add(value.substring(start, start + length))) {
                    best = length;
                    break;
                }
            }
        }
        return best;
    }
}
