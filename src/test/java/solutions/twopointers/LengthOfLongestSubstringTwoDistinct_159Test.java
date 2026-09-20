package solutions.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.Test;

public class LengthOfLongestSubstringTwoDistinct_159Test {

    private final LengthOfLongestSubstringTwoDistinct_159 test = new LengthOfLongestSubstringTwoDistinct_159();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.lengthOfLongestSubstringTwoDistinct("eceba"));
        assertEquals(5, test.lengthOfLongestSubstringTwoDistinct("ccaabbb"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(1, test.lengthOfLongestSubstringTwoDistinct("a"));
        assertEquals(2, test.lengthOfLongestSubstringTwoDistinct("ab"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(4, test.lengthOfLongestSubstringTwoDistinct("aabbccaabb"));
    }

    @Test
    public void testAllSameChars() {
        assertEquals(5, test.lengthOfLongestSubstringTwoDistinct("aaaaa"));
    }

    @Test
    public void testTwoDistinctCharsOnly() {
        assertEquals(6, test.lengthOfLongestSubstringTwoDistinct("ababab"));
    }

    @Test
    public void testEmptyString() {
        assertEquals(0, test.lengthOfLongestSubstringTwoDistinct(""));
    }

    @Test
    public void testThreeDistinctAlternating() {
        assertEquals(2, test.lengthOfLongestSubstringTwoDistinct("abcabc"));
    }

    @Test
    public void testLongRunAtEnd() {
        assertEquals(8, test.lengthOfLongestSubstringTwoDistinct("abcbbbbbb"));
    }

    @Test
    public void testTwoCharsString() {
        assertEquals(2, test.lengthOfLongestSubstringTwoDistinct("ac"));
    }

    @Test
    public void testGiantCase() {
        // 5000 'a' + 5000 'b' = 10000 chars, all two distinct
        String s = "a".repeat(5000) + "b".repeat(5000);
        assertEquals(10000, test.lengthOfLongestSubstringTwoDistinct(s));
    }

    @ParameterizedTest(name = "{index}: {0} -> {1}")
    @MethodSource("boundaryAndPatternCases")
    public void testBoundaryAndPatternCases(String input, int expected) {
        assertEquals(expected, new LengthOfLongestSubstringTwoDistinct_159()
                .lengthOfLongestSubstringTwoDistinct(input));
    }

    private static Stream<Arguments> boundaryAndPatternCases() {
        return Stream.of(
                Arguments.of("abcde", 2),
                Arguments.of("abaccc", 4),
                Arguments.of("abcbbbbcccbdddadacb", 10),
                Arguments.of("aaabbbccc", 6),
                Arguments.of("cabba", 4),
                Arguments.of("aabacbebebe", 6),
                Arguments.of("aabbccddeeff", 4),
                Arguments.of("xyzzzyx", 5),
                Arguments.of("aabbaccc", 5),
                Arguments.of("abccabb", 3),
                Arguments.of("zzzzxyxyx", 5),
                Arguments.of("mnopqrmn", 2),
                Arguments.of("abcdefgggg", 5),
                Arguments.of("bbaac", 4),
                Arguments.of("abcddcba", 4),
                Arguments.of("aabbccaa", 4),
                Arguments.of("ababccbaa", 4),
                Arguments.of("azbycxdwevfughsirjqkplomn", 2),
                Arguments.of("abcdefghijklmnopqrstuvwxyza", 2),
                Arguments.of("ababababccddeeff", 8));
    }

    @Test
    public void testExhaustiveSmallAlphabetAgainstIndependentOracle() {
        List<String> inputs = new ArrayList<>();
        for (int length = 0; length <= 5; length++) {
            collectStrings("abc", length, new StringBuilder(), inputs);
        }

        LengthOfLongestSubstringTwoDistinct_159 solution =
                new LengthOfLongestSubstringTwoDistinct_159();
        for (String input : inputs) {
            assertEquals(longestAtMostTwoDistinct(input),
                    solution.lengthOfLongestSubstringTwoDistinct(input), input);
        }
    }

    private static void collectStrings(String alphabet, int remaining, StringBuilder current,
            List<String> output) {
        if (remaining == 0) {
            output.add(current.toString());
            return;
        }
        for (int i = 0; i < alphabet.length(); i++) {
            current.append(alphabet.charAt(i));
            collectStrings(alphabet, remaining - 1, current, output);
            current.deleteCharAt(current.length() - 1);
        }
    }

    /** Brute-force oracle used only for short, exhaustively generated inputs. */
    private static int longestAtMostTwoDistinct(String input) {
        int best = 0;
        for (int start = 0; start < input.length(); start++) {
            boolean[] present = new boolean[Character.MAX_VALUE + 1];
            int distinct = 0;
            for (int end = start; end < input.length(); end++) {
                char current = input.charAt(end);
                if (!present[current]) {
                    present[current] = true;
                    distinct++;
                }
                if (distinct > 2) {
                    break;
                }
                best = Math.max(best, end - start + 1);
            }
        }
        return best;
    }

    @Test
    public void testLegalMaximumLengthAndRepeatedCalls() {
        String maximumInput = "a".repeat(50_000) + "b".repeat(49_999) + "c";
        assertEquals(99_999,
                test.lengthOfLongestSubstringTwoDistinct(maximumInput));

        assertEquals(2, test.lengthOfLongestSubstringTwoDistinct("xyz"));
        assertEquals(5, test.lengthOfLongestSubstringTwoDistinct("aabbbcc"));
        assertEquals(1, test.lengthOfLongestSubstringTwoDistinct("q"));
        assertEquals(0, test.lengthOfLongestSubstringTwoDistinct(""));
    }
}
