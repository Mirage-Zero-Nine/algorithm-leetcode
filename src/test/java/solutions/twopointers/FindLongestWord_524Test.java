package solutions.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class FindLongestWord_524Test {

    private final FindLongestWord_524 test = new FindLongestWord_524();

    @Test
    public void testHappyCases() {
        assertEquals("apple", test.findLongestWord("abpcplea", List.of("ale", "apple", "monkey", "plea")));
        assertEquals("abc", test.findLongestWord("abce", List.of("abe", "abc")));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals("", test.findLongestWord("", List.of("a")));
        assertEquals("", test.findLongestWord("abc", List.of("xyz")));
    }

    @Test
    public void testLargeCase() {
        assertEquals("abc", test.findLongestWord("aabbcc", List.of("abc", "ab", "a")));
    }

    @Test
    public void testAdditionalHappyCases() {
        assertEquals("ab", test.findLongestWord("bab", List.of("ba", "ab", "a", "b")));
        assertEquals("appl", test.findLongestWord("abpcplea", List.of("ale", "plea", "appl")));
        assertEquals("long", test.findLongestWord("lxoong", List.of("long", "loo", "lg")));
    }

    @Test
    public void testAdditionalEdgeCases() {
        assertEquals("", test.findLongestWord(null, List.of("a")));
        assertEquals("a", test.findLongestWord("a", List.of("a", "")));
        assertEquals("aa", test.findLongestWord("aaa", List.of("aa", "a")));
    }

    @Test
    public void testAdditionalGiantCase() {
        String source = "ab".repeat(100) + "xyz";
        assertEquals("abababab",
            test.findLongestWord(source, List.of("abababab", "abababaz", "abxyz", "xyz")));
    }

    @Test
    public void testLexicographicOrder() {
        // "a" and "b" both length 1, "a" is lexicographically smaller
        assertEquals("a", test.findLongestWord("ab", List.of("b", "a")));
    }

    @Test
    public void testEmptyDictionary() {
        assertEquals("", test.findLongestWord("abc", List.of()));
    }

    @Test
    public void testGiantSourceString() {
        String source = "a".repeat(5000) + "b".repeat(5000);
        String target = "a".repeat(2500) + "b".repeat(2500);
        assertEquals(target, test.findLongestWord(source, List.of(target, "ab", "a")));
    }

    @Test
    public void testNoSubsequenceMatch() {
        assertEquals("", test.findLongestWord("abc", List.of("def", "ghi", "jkl")));
    }

    @Test public void testTieChoosesLexicographicallySmallest() { assertEquals("abc", test.findLongestWord("abcde", List.of("abd", "abc"))); }
    @Test public void testDictionaryDuplicateEntries() { assertEquals("cat", test.findLongestWord("cat", List.of("cat", "cat", "at"))); }
    @Test public void testSingleCharacterTie() { assertEquals("a", test.findLongestWord("ba", List.of("b", "a"))); }
    @Test public void testWordRequiresOrder() { assertEquals("ac", test.findLongestWord("abc", List.of("ca", "ac"))); }
    @Test public void testEmptyCandidateWinsNothing() { assertEquals("", test.findLongestWord("xyz", List.of("", "ab"))); }
    @Test public void testRepeatedSourceLetters() { assertEquals("aaa", test.findLongestWord("aaaa", List.of("aa", "aaa", "aaaaa"))); }
    @Test public void testLongCandidateSubsequence() { assertEquals("acegi", test.findLongestWord("a1c2e3g4i", List.of("acegi", "acegi"))); }
    @Test public void testCandidateLongerThanSource() { assertEquals("ab", test.findLongestWord("ab", List.of("abc", "ba", "ab"))); }
    @Test public void testCapitalLettersPreserved() { assertEquals("ABC", test.findLongestWord("xAyBzC", List.of("ABC", "AbC", "xA"))); }
    @Test public void testManyCandidates() { assertEquals("algorithm", test.findLongestWord("algorithmic", List.of("algo", "rhythm", "algorithm", "logic"))); }
}
