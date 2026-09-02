package solutions.hashmap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class IsAnagram_242Test {

    private final IsAnagram_242 anagram = new IsAnagram_242();

    @ParameterizedTest(name = "case {index}: expected={2}, s=\"{0}\", t=\"{1}\"")
    @MethodSource("lowercaseCases")
    void bothApproachesHandleLowercaseCases(String s, String t, boolean expected) {
        assertEquals(expected, anagram.isAnagram(s, t));
        assertEquals(expected, anagram.isAnagramWithHashMap(s, t));
    }

    @ParameterizedTest(name = "null corner case {index}")
    @MethodSource("nullCases")
    void bothApproachesRejectNullInputs(String s, String t) {
        assertFalse(anagram.isAnagram(s, t));
        assertFalse(anagram.isAnagramWithHashMap(s, t));
    }

    private static Stream<Arguments> nullCases() {
        return Stream.of(
                Arguments.of((String) null, "a"),
                Arguments.of("a", (String) null),
                Arguments.of((String) null, (String) null)
        );
    }

    private static Stream<Arguments> lowercaseCases() {
        return Stream.of(
                // Official examples.
                Arguments.of("anagram", "nagaram", true),
                Arguments.of("rat", "car", false),

                // Minimum-length and single-character inputs.
                Arguments.of("a", "a", true),
                Arguments.of("a", "b", false),
                Arguments.of("ab", "ba", true),
                Arguments.of("ab", "aa", false),

                // Same letters in different orders.
                Arguments.of("listen", "silent", true),
                Arguments.of("evil", "vile", true),
                Arguments.of("rail", "liar", true),
                Arguments.of("elbow", "below", true),
                Arguments.of("study", "dusty", true),
                Arguments.of("abc", "cba", true),
                Arguments.of("abcdefghijklmnopqrstuvwxyz", "zyxwvutsrqponmlkjihgfedcba", true),

                // Repeated letters and frequency mismatches.
                Arguments.of("aabb", "baba", true),
                Arguments.of("aabbcc", "ccbaab", true),
                Arguments.of("aabbcc", "bcabca", true),
                Arguments.of("aabb", "abbb", false),
                Arguments.of("aaaa", "aaab", false),
                Arguments.of("aab", "abb", false),
                Arguments.of("abcabc", "abccab", true),
                Arguments.of("abcabc", "abccac", false),

                // Same length but different character sets.
                Arguments.of("hello", "world", false),
                Arguments.of("anagram", "nagarom", false),
                Arguments.of("abcdef", "abcdee", false),
                Arguments.of("aabbcc", "aabbcd", false),
                Arguments.of("racecar", "aaccerr", true),

                // Different lengths can never be anagrams.
                Arguments.of("a", "ab", false),
                Arguments.of("ab", "abc", false),
                Arguments.of("abc", "abcd", false),
                Arguments.of("anagram", "nagarams", false),
                Arguments.of("listen", "silents", false)
        );
    }

    @Test
    void handlesTheMaximumAllowedStringLength() {
        String s = repeatedAlphabet(50_000);
        String t = new StringBuilder(s).reverse().toString();

        assertBothApproaches(s, t, true);

        char[] changed = t.toCharArray();
        changed[0] = changed[0] == 'a' ? 'b' : 'a';
        assertBothApproaches(s, new String(changed), false);
    }

    @Test
    void handlesAnagramsWithPairPositionsAtBothEnds() {
        String s = "a" + "m".repeat(100) + "z";
        String t = "z" + "m".repeat(100) + "a";

        assertBothApproaches(s, t, true);
    }

    @Test
    void handlesRandomLowercaseAnagrams() {
        Random random = new Random(42L);
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

        for (int trial = 0; trial < 100; trial++) {
            int length = random.nextInt(1_000) + 1;
            char[] source = new char[length];
            for (int i = 0; i < source.length; i++) {
                source[i] = alphabet[random.nextInt(alphabet.length)];
            }

            char[] shuffled = source.clone();
            for (int i = shuffled.length - 1; i > 0; i--) {
                int swapIndex = random.nextInt(i + 1);
                char temporary = shuffled[i];
                shuffled[i] = shuffled[swapIndex];
                shuffled[swapIndex] = temporary;
            }

            assertBothApproaches(sourceAsString(source), sourceAsString(shuffled), true);
        }
    }

    @Test
    void agreesWithSortingOracleForRandomLowercaseInputs() {
        Random random = new Random(7L);
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

        for (int trial = 0; trial < 200; trial++) {
            int firstLength = random.nextInt(100) + 1;
            int secondLength = random.nextInt(100) + 1;
            String s = randomLowercaseString(random, alphabet, firstLength);
            String t = randomLowercaseString(random, alphabet, secondLength);
            boolean expected = isAnagramBySorting(s, t);

            assertBothApproaches(s, t, expected);
        }
    }

    @ParameterizedTest(name = "HashMap follow-up case {index}: expected={2}")
    @MethodSource("hashMapFollowUpCases")
    void hashMapApproachHandlesUnicodeFollowUp(String s, String t, boolean expected) {
        assertEquals(expected, anagram.isAnagramWithHashMap(s, t));
    }

    private static Stream<Arguments> hashMapFollowUpCases() {
        return Stream.of(
                Arguments.of("ABCdef", "fedCBA", true),
                Arguments.of("Hello!", "!olleH", true),
                Arguments.of("12345", "54321", true),
                Arguments.of("caf\u00e9", "\u00e9caf", true),
                Arguments.of("你好世界", "界世好你", true),
                Arguments.of("a💡b", "b💡a", true),
                Arguments.of("Hello!", "!olleh", false),
                Arguments.of("你好世界", "你好世世", false),
                Arguments.of("abc", "abcd", false)
        );
    }

    private void assertBothApproaches(String s, String t, boolean expected) {
        assertEquals(expected, anagram.isAnagram(s, t));
        assertEquals(expected, anagram.isAnagramWithHashMap(s, t));
    }

    private static String repeatedAlphabet(int length) {
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            builder.append((char) ('a' + i % 26));
        }
        return builder.toString();
    }

    private static String randomLowercaseString(Random random, char[] alphabet, int length) {
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            builder.append(alphabet[random.nextInt(alphabet.length)]);
        }
        return builder.toString();
    }

    private static String sourceAsString(char[] chars) {
        return new String(chars);
    }

    private static boolean isAnagramBySorting(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        char[] first = s.toCharArray();
        char[] second = t.toCharArray();
        Arrays.sort(first);
        Arrays.sort(second);
        return Arrays.equals(first, second);
    }
}
