package solutions.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
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

    @Test
    public void testEmptyS_returnsEmpty() {
        assertEquals(List.of(), test.findAnagrams("", "abc"));
    }

    @Test
    public void testPLongerThanS_returnsEmpty() {
        assertEquals(List.of(), test.findAnagrams("ab", "abcd"));
    }

    @Test
    public void testPEqualsS_isAnagram() {
        assertEquals(List.of(0), test.findAnagrams("cba", "abc"));
        assertEquals(List.of(0), test.findAnagrams("abc", "abc"));
    }

    @Test
    public void testAllUniqueCharsExactMatch() {
        // p="xyz", s contains "zyx" at index 2
        assertEquals(List.of(2), test.findAnagrams("abzyx", "xyz"));
    }

    @Test
    public void testSingleCharP() {
        assertEquals(List.of(1, 3), test.findAnagrams("ababa", "b"));
        assertEquals(List.of(), test.findAnagrams("aaaa", "b"));
    }

    @Test
    public void testRepeatingCharsInBoth() {
        // p="aab" (a:2,b:1), s="aabaa": aab(0)✓, aba(1)=a:2,b:1✓, baa(2)=a:2,b:1✓
        assertEquals(List.of(0, 1, 2), test.findAnagrams("aabaa", "aab"));
    }

    @Test
    public void testLargeRandomString_seed42() {
        Random rng = new Random(42L);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append((char) ('a' + rng.nextInt(4))); // a-d
        }
        String s = sb.toString();
        String p = "abcd";
        List<Integer> result = test.findAnagrams(s, p);
        // Property: every index valid and substring is anagram of p
        int[] pCount = new int[26];
        for (char c : p.toCharArray()) pCount[c - 'a']++;
        for (int idx : result) {
            assertTrue(idx >= 0 && idx + p.length() <= s.length());
            int[] wCount = new int[26];
            for (int k = idx; k < idx + p.length(); k++) wCount[s.charAt(k) - 'a']++;
            assertTrue(Arrays.equals(pCount, wCount), "Index " + idx + " is not an anagram");
        }
    }

    @Test
    public void testProperty_indicesSortedAscending() {
        String[] inputs = {"cbaebabacd", "abab", "aabaa", "aaaaa"};
        String[] patterns = {"abc", "ab", "aab", "aaa"};
        for (int i = 0; i < inputs.length; i++) {
            List<Integer> result = test.findAnagrams(inputs[i], patterns[i]);
            for (int j = 1; j < result.size(); j++) {
                assertTrue(result.get(j) > result.get(j - 1), "Indices not sorted for input: " + inputs[i]);
            }
        }
    }

    @Test
    public void testProperty_everyIndexIsAnagram() {
        String s = "cbaebabacd";
        String p = "abc";
        List<Integer> result = test.findAnagrams(s, p);
        int[] pCount = new int[26];
        for (char c : p.toCharArray()) pCount[c - 'a']++;
        for (int idx : result) {
            int[] wCount = new int[26];
            for (int k = idx; k < idx + p.length(); k++) wCount[s.charAt(k) - 'a']++;
            assertTrue(Arrays.equals(pCount, wCount));
        }
    }
}
