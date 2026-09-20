package solutions.trie;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PalindromePairs_336Test {
    private final PalindromePairs_336 solver = new PalindromePairs_336();

    @Test public void testExample1() {
        List<List<Integer>> result = solver.palindromePairs(new String[]{"a", ""});
        assertTrue(result.contains(List.of(0, 1)));
        assertTrue(result.contains(List.of(1, 0)));
    }

    @Test public void testExample2() {
        List<List<Integer>> result = solver.palindromePairs(new String[]{"abcd", "dcba", "", "l", "d", "c"});
        assertTrue(result.contains(List.of(0, 1)));
        assertTrue(result.contains(List.of(1, 0)));
        assertTrue(result.contains(List.of(2, 3)));
        assertTrue(result.contains(List.of(3, 2)));
        assertTrue(result.contains(List.of(2, 4)));
        assertTrue(result.contains(List.of(4, 2)));
        assertTrue(result.contains(List.of(2, 5)));
        assertTrue(result.contains(List.of(5, 2)));
    }

    @Test public void testSingleWord() {
        List<List<Integer>> result = solver.palindromePairs(new String[]{"a"});
        assertTrue(result.isEmpty());
    }

    @Test public void testEmptyArray() {
        List<List<Integer>> result = solver.palindromePairs(new String[]{});
        assertTrue(result.isEmpty());
    }

    @Test public void testNoPairs() {
        List<List<Integer>> result = solver.palindromePairs(new String[]{"abc", "def", "ghi"});
        assertTrue(result.isEmpty());
    }

    @Test public void testPalindromeWord() {
        List<List<Integer>> result = solver.palindromePairs(new String[]{"aba", ""});
        assertTrue(result.contains(List.of(0, 1)));
        assertTrue(result.contains(List.of(1, 0)));
    }

    @Test public void testSelfPair() {
        List<List<Integer>> result = solver.palindromePairs(new String[]{"a", "b", "c"});
        assertTrue(result.isEmpty());
    }

    @Test public void testSameWord() {
        List<List<Integer>> result = solver.palindromePairs(new String[]{"ab", "ba"});
        assertTrue(result.contains(List.of(0, 1)));
        assertTrue(result.contains(List.of(1, 0)));
    }

    @Test public void testEmptyStringPairs() {
        List<List<Integer>> result = solver.palindromePairs(new String[]{"", "a", "b"});
        assertTrue(result.contains(List.of(0, 1)));
        assertTrue(result.contains(List.of(1, 0)));
        assertTrue(result.contains(List.of(0, 2)));
        assertTrue(result.contains(List.of(2, 0)));
    }

    @Test public void testLongerPalindrome() {
        // "abc"+"cba" = "abccba" (palindrome), "cba"+"abc" = "cbaabc" (palindrome)
        // ""+"abc" = "abc" (not palindrome), ""+"cba" = "cba" (not palindrome)
        List<List<Integer>> result = solver.palindromePairs(new String[]{"abc", "cba", ""});
        assertTrue(result.contains(List.of(0, 1)));
        assertTrue(result.contains(List.of(1, 0)));
        assertEquals(2, result.size());
    }

    private void assertPairs(String[] words, List<List<Integer>> expected) {
        List<List<Integer>> actual = solver.palindromePairs(words);
        assertEquals(expected.size(), actual.size());
        assertEquals(expected.stream().map(List::copyOf).collect(java.util.stream.Collectors.toSet()),
            actual.stream().map(List::copyOf).collect(java.util.stream.Collectors.toSet()));
    }

    @Test public void testOfficialExampleWithSplitPalindrome() {
        assertPairs(new String[]{"abcd", "dcba", "lls", "s", "sssll"},
            List.of(List.of(0, 1), List.of(1, 0), List.of(3, 2), List.of(2, 4)));
    }

    @Test public void testOfficialBatTabExample() {
        assertPairs(new String[]{"bat", "tab", "cat"}, List.of(List.of(0, 1), List.of(1, 0)));
    }

    @Test public void testNoPairsWhenOnlyNonPalindromicConcatenations() {
        assertPairs(new String[]{"abc", "def", "ghi"}, List.of());
    }

    @Test public void testEmptyWordWithMultiplePalindromes() {
        assertPairs(new String[]{"", "a", "aa", "b"},
            List.of(List.of(0, 1), List.of(1, 0), List.of(0, 2), List.of(2, 0),
                List.of(0, 3), List.of(3, 0), List.of(1, 2), List.of(2, 1)));
    }

    @Test public void testOddAndEvenSplitPalindromes() {
        assertPairs(new String[]{"a", "bc", "cb"}, List.of(List.of(1, 2), List.of(2, 1)));
    }

    @Test public void testPrefixPalindromeRemainder() {
        assertPairs(new String[]{"abcd", "cba"}, List.of(List.of(0, 1)));
    }

    @Test public void testSuffixPalindromeRemainder() {
        assertPairs(new String[]{"abc", "cba"}, List.of(List.of(0, 1), List.of(1, 0)));
    }

    @Test public void testSingleCharactersDoNotSelfPair() {
        assertPairs(new String[]{"a", "b", "c", "d"}, List.of());
    }

    @Test public void testLongWordsAndSharedPrefixes() {
        assertPairs(new String[]{"race", "ecar", "car", "rac"}, List.of(
            List.of(0, 1), List.of(0, 2), List.of(1, 0), List.of(2, 3), List.of(3, 1), List.of(3, 2)));
    }

    @Test public void testInputOrderDoesNotChangePairSemantics() {
        assertPairs(new String[]{"tab", "cat", "bat"}, List.of(List.of(0, 2), List.of(2, 0)));
    }

    @Test public void testRepeatedCallsDoNotRetainTrieState() {
        assertPairs(new String[]{"ab", "ba"}, List.of(List.of(0, 1), List.of(1, 0)));
        assertPairs(new String[]{"abc", "def"}, List.of());
    }
}
