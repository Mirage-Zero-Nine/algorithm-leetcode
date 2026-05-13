package solution.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

public class FindAnagrams_438Test {

    private final FindAnagrams_438 test = new FindAnagrams_438();

    @Test
    public void testHappyCase1() {
        assertEquals(List.of(0, 6), test.findAnagrams("cbaebabacd", "abc"));
    }

    @Test
    public void testHappyCase2() {
        assertEquals(List.of(0, 1, 2), test.findAnagrams("abab", "ab"));
    }

    @Test
    public void testHappyCase3() {
        assertEquals(List.of(0, 1, 2), test.findAnagrams("aaaaa", "aaa"));
    }

    @Test
    public void testHappyCase4() {
        // bacb, abc
        // bac -> anagram (0)
        // acb -> anagram (1)
        assertEquals(List.of(0, 1), test.findAnagrams("bacb", "abc"));
    }

    @Test
    public void testHappyCase5() {
        // abacb, abc
        // aba -> no
        // bac -> yes (1)
        // acb -> yes (2)
        assertEquals(List.of(1, 2), test.findAnagrams("abacb", "abc"));
    }

    @Test
    public void testNegativeCase() {
        assertEquals(List.of(), test.findAnagrams("abcd", "e"));
        assertEquals(List.of(), test.findAnagrams("a", "aa"));
    }

    @Test
    public void testEdgeCaseEmpty() {
        assertEquals(List.of(), test.findAnagrams("", "abc"));
        assertEquals(List.of(), test.findAnagrams("abc", ""));
        assertEquals(List.of(), test.findAnagrams(null, "abc"));
    }

    @Test
    public void testEdgeCaseSingleChar() {
        assertEquals(List.of(0, 1, 2), test.findAnagrams("aaa", "a"));
    }

    @Test
    public void testEdgeCaseSameLengthNotAnagram() {
        assertEquals(List.of(), test.findAnagrams("abcd", "abce"));
    }

    @Test
    public void testGiantCase() {
        int n = 10000;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append('a');
        }
        String s = sb.toString();
        String p = "aa";
        // p length 2, s length n. Anagrams at 0, 1, ..., n-2. Total n-1 indices.
        List<Integer> result = test.findAnagrams(s, p);
        assertEquals(n - 1, result.size());
        assertEquals(0, result.get(0));
        assertEquals(n - 2, result.get(result.size() - 1));
    }
}
