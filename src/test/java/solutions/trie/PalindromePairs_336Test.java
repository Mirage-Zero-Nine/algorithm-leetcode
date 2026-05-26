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
}
