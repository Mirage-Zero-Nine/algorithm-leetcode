package solutions.backtracking;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Partition_131Test {
    private final Partition_131 solution = new Partition_131();

    @Test
    void testBasic() {
        List<List<String>> result = solution.partition("aab");
        assertEquals(2, result.size());
    }

    @Test
    void testSingleChar() {
        List<List<String>> result = solution.partition("a");
        assertEquals(1, result.size());
    }

    @Test
    void testAllSame() {
        List<List<String>> result = solution.partition("aaa");
        assertEquals(4, result.size());
    }

    @Test
    void testNoPalindrome() {
        List<List<String>> result = solution.partition("abc");
        assertEquals(1, result.size());
    }

    @Test
    void testLonger() {
        assertMatchesIndependentOracle("aabb");
    }

    @Test
    void testEmptyString() {
        List<List<String>> result = solution.partition("");
        assertEquals(1, result.size());
        assertEquals(0, result.get(0).size());
    }

    @Test
    void testTwoChars() {
        List<List<String>> result = solution.partition("ab");
        assertEquals(1, result.size()); // only ["a","b"]
    }

    @Test
    void testPalindromeString() {
        List<List<String>> result = solution.partition("aba");
        // ["a","b","a"], ["aba"]
        assertEquals(2, result.size());
    }

    @Test
    void testAllPartitionsContainPalindromes() {
        List<List<String>> result = solution.partition("aab");
        for (List<String> partition : result) {
            for (String s : partition) {
                assertEquals(s, new StringBuilder(s).reverse().toString());
            }
        }
    }

    @Test
    void testSixChars() {
        assertMatchesIndependentOracle("abcabc");
    }

    @Test
    void testGiantInput() {
        // "aaaaaaaaaa" (10 a's) - many palindrome partitions
        List<List<String>> result = solution.partition("aaaaaaaaaa");
        assertEquals(512, result.size());
        assertMatchesIndependentOracle("aaaaaaaaaa");
    }

    @Test
    void testTwoSameCharsContent() {
        List<List<String>> result = solution.partition("aa");
        Set<List<String>> expected = Set.of(
                List.of("a", "a"),
                List.of("aa")
        );
        assertEquals(expected, new HashSet<>(result));
    }

    @Test
    void testTwoDiffCharsContent() {
        List<List<String>> result = solution.partition("ab");
        assertEquals(List.of(List.of("a", "b")), result);
    }

    @Test
    void testAllSameCharsAaaContent() {
        List<List<String>> result = solution.partition("aaa");
        Set<List<String>> expected = Set.of(
                List.of("a", "a", "a"),
                List.of("a", "aa"),
                List.of("aa", "a"),
                List.of("aaa")
        );
        assertEquals(expected, new HashSet<>(result));
    }

    @Test
    void testAabExactContent() {
        List<List<String>> result = solution.partition("aab");
        Set<List<String>> expected = Set.of(
                List.of("a", "a", "b"),
                List.of("aa", "b")
        );
        assertEquals(expected, new HashSet<>(result));
    }

    @Test
    void testLongPalindromeAbcba() {
        List<List<String>> result = solution.partition("abcba");
        // Must contain at least ["a","b","c","b","a"] and ["abcba"]
        assertTrue(result.contains(List.of("a", "b", "c", "b", "a")));
        assertTrue(result.contains(List.of("abcba")));
        assertTrue(result.contains(List.of("a", "bcb", "a")));
        assertAllPartitionsValid("abcba", result);
    }

    @Test
    void testMixedAbacd() {
        List<List<String>> result = solution.partition("abacd");
        assertAllPartitionsValid("abacd", result);
        // Must contain ["a","b","a","c","d"] and ["aba","c","d"]
        assertTrue(result.contains(List.of("a", "b", "a", "c", "d")));
        assertTrue(result.contains(List.of("aba", "c", "d")));
    }

    @Test
    void testConcatenationPropertyAabb() {
        String input = "aabb";
        List<List<String>> result = solution.partition(input);
        for (List<String> partition : result) {
            assertEquals(input, String.join("", partition),
                    "Concatenation of " + partition + " should equal " + input);
        }
    }

    @Test
    void testResultsUnique() {
        List<List<String>> result = solution.partition("abcba");
        Set<List<String>> unique = new HashSet<>(result);
        assertEquals(result.size(), unique.size(), "All partitions should be unique");
    }

    @Test
    void testLargerString12Chars() {
        Random rng = new Random(42L);
        StringBuilder input = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            input.append((char) ('a' + rng.nextInt(4)));
        }
        assertMatchesIndependentOracle(input.toString());
    }

    @Test
    void testSingleCharacterAlphabetCases() {
        assertMatchesIndependentOracle("z");
        assertMatchesIndependentOracle("zz");
    }

    @Test
    void testNoMultiCharacterPalindromes() {
        assertMatchesIndependentOracle("abcd");
        assertMatchesIndependentOracle("abcdefg");
    }

    @Test
    void testOddAndEvenPalindromes() {
        assertMatchesIndependentOracle("abba");
        assertMatchesIndependentOracle("abccba");
        assertMatchesIndependentOracle("racecar");
        assertMatchesIndependentOracle("abacaba");
    }

    @Test
    void testAlternatingCharacters() {
        assertMatchesIndependentOracle("abab");
        assertMatchesIndependentOracle("ababab");
        assertMatchesIndependentOracle("abababab");
    }

    @Test
    void testRepeatedBlocks() {
        assertMatchesIndependentOracle("aabaa");
        assertMatchesIndependentOracle("aabbaa");
        assertMatchesIndependentOracle("aabbaaab");
    }

    @Test
    void testMixedLowercaseAlphabetCharacters() {
        assertMatchesIndependentOracle("aabbcc");
        assertMatchesIndependentOracle("abacabad");
        assertMatchesIndependentOracle("cabac");
    }

    @Test
    void testExhaustiveBinaryStringsThroughLengthSeven() {
        for (int length = 1; length <= 7; length++) {
            int inputs = 1 << length;
            for (int value = 0; value < inputs; value++) {
                StringBuilder input = new StringBuilder(length);
                for (int bit = length - 1; bit >= 0; bit--) {
                    input.append(((value >>> bit) & 1) == 0 ? 'a' : 'b');
                }
                assertMatchesIndependentOracle(input.toString());
            }
        }
    }

    @Test
    void testMaximumLengthAllSameString() {
        String input = "aaaaaaaaaaaaaaaa";
        List<List<String>> result = solution.partition(input);
        assertEquals(1 << (input.length() - 1), result.size());
        assertAllPartitionsValid(input, result);
        assertEquals(independentOracle(input), new HashSet<>(result));
    }

    @Test
    void testMaximumLengthMixedString() {
        assertMatchesIndependentOracle("abcdefghijklmnop");
    }

    @Test
    void testRepeatedInvocationDoesNotLeakState() {
        assertMatchesIndependentOracle("aab");
        assertMatchesIndependentOracle("abba");
        assertMatchesIndependentOracle("abc");
        assertMatchesIndependentOracle("aab");
    }

    @Test
    void testResultListsAreIndependentSnapshots() {
        List<List<String>> result = solution.partition("aaa");
        result.get(0).clear();
        assertEquals(independentOracle("aaa"), new HashSet<>(solution.partition("aaa")));
    }

    private void assertAllPartitionsValid(String original, List<List<String>> partitions) {
        for (List<String> partition : partitions) {
            assertEquals(original, String.join("", partition),
                    "Concatenation of " + partition + " should equal " + original);
            for (String part : partition) {
                assertTrue(part.length() > 0, "Each part should be non-empty");
                assertEquals(part, new StringBuilder(part).reverse().toString(),
                        part + " should be a palindrome");
            }
        }
    }

    private void assertMatchesIndependentOracle(String input) {
        List<List<String>> result = solution.partition(input);
        assertAllPartitionsValid(input, result);
        Set<List<String>> actual = new HashSet<>(result);
        assertEquals(result.size(), actual.size(), "Partitions must be unique for: " + input);
        assertEquals(independentOracle(input), actual, "Unexpected partitions for: " + input);
    }

    /**
     * Enumerates every possible cut mask independently of the recursive solution. A set bit
     * after character i ends one part; masks whose parts are all palindromes are the oracle.
     */
    private Set<List<String>> independentOracle(String input) {
        Set<List<String>> expected = new HashSet<>();
        if (input.isEmpty()) {
            expected.add(List.of());
            return expected;
        }
        int cutCount = input.length() - 1;
        int masks = 1 << cutCount;
        for (int mask = 0; mask < masks; mask++) {
            List<String> partition = new java.util.ArrayList<>();
            int start = 0;
            boolean valid = true;
            for (int end = 0; end < input.length(); end++) {
                if (end == input.length() - 1 || ((mask >>> end) & 1) != 0) {
                    String part = input.substring(start, end + 1);
                    if (!isPalindrome(part)) {
                        valid = false;
                        break;
                    }
                    partition.add(part);
                    start = end + 1;
                }
            }
            if (valid) {
                expected.add(partition);
            }
        }
        return expected;
    }

    private boolean isPalindrome(String value) {
        return value.contentEquals(new StringBuilder(value).reverse());
    }
}
