package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.Test;

/** Tests for the binary-search/Rabin-Karp solution to LeetCode 1044. */
public class LongestDupSubstring_1044Test {
    private final LongestDupSubstring_1044 test = new LongestDupSubstring_1044();

    @Test
    public void testOfficialBananaExample() {
        assertEquals("ana", test.longestDupSubstring("banana"));
    }

    @Test
    public void testOfficialNoDuplicateExample() {
        assertEquals("", test.longestDupSubstring("abcd"));
    }

    @Test
    public void testMinimumLengthWithNoDuplicate() {
        assertEquals("", test.longestDupSubstring("ab"));
    }

    @Test
    public void testSingleCharacterImplementationBehavior() {
        // The class handles this smaller-than-LeetCode-minimum input as no duplicate.
        assertEquals("", test.longestDupSubstring("a"));
    }

    @Test
    public void testDistinctCharactersAtLargerSize() {
        assertMatchesOracle("abcdefghijk");
    }

    @Test
    public void testAllSameCharacters() {
        assertMatchesOracle("zzzz");
    }

    @Test
    public void testLargeAllSameCharacters() {
        String source = "a".repeat(1_000);
        String result = test.longestDupSubstring(source);

        assertEquals(999, result.length());
        assertDuplicateValidity(source, result);
    }

    @Test
    public void testOverlappingDuplicateAllowed() {
        assertMatchesOracle("ababa");
    }

    @Test
    public void testStronglyOverlappingDuplicate() {
        assertMatchesOracle("aaaaab");
    }

    @Test
    public void testMultipleCandidatesAnyLongestAccepted() {
        assertMatchesOracle("abcdabc");
    }

    @Test
    public void testTieBetweenDifferentLongestSubstrings() {
        assertMatchesOracle("abcXabcY");
    }

    @Test
    public void testRepeatedPrefixSuffixPattern() {
        assertMatchesOracle("abcabcabcx");
    }

    @Test
    public void testDuplicateOfLengthOneOnly() {
        assertMatchesOracle("abca");
    }

    @Test
    public void testAlternatingPatternWithOverlappingOccurrences() {
        assertMatchesOracle("abababa");
    }

    @Test
    public void testDuplicateSurroundedByDifferentNoise() {
        assertMatchesOracle("qxyzmnopxyzrst");
    }

    @Test
    public void testMixedRepeatedBlocks() {
        assertMatchesOracle("cabacabae");
    }

    @Test
    public void testRollingHashCollisionStillRequiresEqualText() {
        // These two distinct length-7 substrings collide under this class's base-26,
        // 2^30-modulus hash. Exact substring comparison must reject the collision.
        String source = "lckdnidedquujf";
        assertMatchesOracle(source);

        test.longestDupSubstring(source); // initialize the helper's rolling-hash state
        assertEquals(-1, test.hasRepeating(source, 7));
    }

    @Test
    public void testHasRepeatingFindsOverlappingMatch() {
        String source = "aaaa";
        test.longestDupSubstring(source); // initialize arr used by the public helper

        assertEquals(1, test.hasRepeating(source, 3));
        assertEquals(-1, test.hasRepeating(source, 4));
    }

    @Test
    public void testExhaustiveBinaryAlphabetInputs() {
        for (int length = 2; length <= 9; length++) {
            int cases = 1 << length;
            for (int mask = 0; mask < cases; mask++) {
                StringBuilder source = new StringBuilder(length);
                for (int bit = length - 1; bit >= 0; bit--) {
                    source.append(((mask >>> bit) & 1) == 0 ? 'a' : 'b');
                }
                assertMatchesOracle(source.toString());
            }
        }
    }

    @Test
    public void testSeededSmallInputsAgainstIndependentOracle() {
        Random random = new Random(1044L);
        for (int caseNumber = 0; caseNumber < 100; caseNumber++) {
            int length = 2 + random.nextInt(35);
            StringBuilder source = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                source.append((char) ('a' + random.nextInt(5)));
            }
            assertMatchesOracle(source.toString());
        }
    }

    @Test
    public void testRepeatedCallsDoNotShareState() {
        String first = test.longestDupSubstring("banana");
        String second = test.longestDupSubstring("abcd");
        String third = test.longestDupSubstring("mississippi");

        assertEquals(3, first.length());
        assertEquals("", second);
        assertEquals(4, third.length());
        assertDuplicateValidity("banana", first);
        assertDuplicateValidity("mississippi", third);
    }

    @Test
    public void testFreshInstancesGiveIndependentResults() {
        String first = new LongestDupSubstring_1044().longestDupSubstring("abcabcx");
        String second = new LongestDupSubstring_1044().longestDupSubstring("zzzzzz");

        assertEquals(3, first.length());
        assertEquals(5, second.length());
        assertDuplicateValidity("abcabcx", first);
        assertDuplicateValidity("zzzzzz", second);
    }

    @Test
    public void testSourceRemainsUnchanged() {
        String source = "abccabccx";
        String snapshot = source;

        assertMatchesOracle(source);
        assertEquals(snapshot, source);
    }

    @Test
    public void testShortRepeatedTwoCharacterBlock() {
        assertMatchesOracle("aab");
    }

    @Test
    public void testLongRepeatedBlock() {
        assertMatchesOracle("mnopqmnopq");
    }

    @Test
    public void testMaximumConstraintAllSameInput() {
        String source = "a".repeat(30_000);
        String result = test.longestDupSubstring(source);

        assertEquals(29_999, result.length());
        assertDuplicateValidity(source, result);
    }

    @Test
    public void testMaximumConstraintPeriodicInput() {
        String source = "abcde".repeat(6_000);
        String result = test.longestDupSubstring(source);

        // The two occurrences can start five characters apart, but not one apart.
        assertEquals(29_995, result.length());
        assertDuplicateValidity(source, result);
    }

    private void assertMatchesOracle(String source) {
        String result = test.longestDupSubstring(source);
        String expected = bruteForceLongestDuplicate(source);

        assertEquals(expected.length(), result.length(),
                () -> "Unexpected maximum duplicate length for " + source);
        assertDuplicateValidity(source, result);
    }

    private void assertDuplicateValidity(String source, String duplicate) {
        if (duplicate.isEmpty()) {
            assertEquals("", bruteForceLongestDuplicate(source));
            return;
        }

        int occurrences = 0;
        for (int start = 0; start + duplicate.length() <= source.length(); start++) {
            if (source.startsWith(duplicate, start)) {
                occurrences++;
            }
        }
        assertTrue(occurrences >= 2,
                () -> "Result is not duplicated in source: " + duplicate + " / " + source);
    }

    /** Independent O(n^2) oracle suitable for the small deterministic cases above. */
    private String bruteForceLongestDuplicate(String source) {
        for (int length = source.length() - 1; length >= 1; length--) {
            Set<String> seen = new HashSet<>();
            for (int start = 0; start + length <= source.length(); start++) {
                String candidate = source.substring(start, start + length);
                if (!seen.add(candidate)) {
                    return candidate;
                }
            }
        }
        return "";
    }
}
