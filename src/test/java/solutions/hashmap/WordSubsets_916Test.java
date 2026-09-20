package solutions.hashmap;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link WordSubsets_916}.
 */
public class WordSubsets_916Test {

    private final WordSubsets_916 solver = new WordSubsets_916();

    @Test
    public void testLeetCodeExample1() {
        // words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["e","o"]
        // universal strings: "facebook" (has 'e','o'), "google" (has 'e','o'), "leetcode" (has 'e','o')
        List<String> result = solver.wordSubsets(
            new String[]{"amazon", "apple", "facebook", "google", "leetcode"},
            new String[]{"e", "o"}
        );
        assertTrue(result.contains("facebook"));
        assertTrue(result.contains("google"));
        assertTrue(result.contains("leetcode"));
        assertEquals(3, result.size());
    }

    @Test
    public void testLeetCodeExample2() {
        // Official example 2: the combined requirements are l:1, c:1, e:1, o:1.
        // Only "leetcode" contains all of them.
        List<String> result = solver.wordSubsets(
            new String[]{"amazon", "apple", "facebook", "google", "leetcode"},
            new String[]{"lc", "eo"}
        );
        assertTrue(result.contains("leetcode"));
        assertEquals(1, result.size());
    }

    @Test
    public void testEmptyWords1() {
        List<String> result = solver.wordSubsets(new String[]{}, new String[]{"a"});
        assertTrue(result.isEmpty());
    }

    @Test
    public void testEmptyWords2() {
        List<String> result = solver.wordSubsets(new String[]{"hello"}, new String[]{});
        assertTrue(result.isEmpty());
    }

    @Test
    public void testNullWords1() {
        List<String> result = solver.wordSubsets(null, new String[]{"a"});
        assertTrue(result.isEmpty());
    }

    @Test
    public void testNullWords2() {
        List<String> result = solver.wordSubsets(new String[]{"hello"}, null);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testAllUniversal() {
        // words1 = ["abc","def"], words2 = ["a","b"]
        // "abc" has 'a','b' ✓, "def" has 'a','b'? No.
        List<String> result = solver.wordSubsets(
            new String[]{"abc", "def"},
            new String[]{"a", "b"}
        );
        assertTrue(result.contains("abc"));
        assertEquals(1, result.size());
    }

    @Test
    public void testNoUniversal() {
        // words1 = ["cat","dog"], words2 = ["a","b"]
        // neither has both 'a' and 'b'
        List<String> result = solver.wordSubsets(
            new String[]{"cat", "dog"},
            new String[]{"a", "b"}
        );
        assertTrue(result.isEmpty());
    }

    @Test
    public void testMultiplicty() {
        // words1 = ["wrr","warrior"], words2 = ["wr"]
        // "wrr" has 'w':1, 'r':2. words2 needs 'w':1, 'r':1. ✓
        // "warrior" has 'w':1, 'r':2. ✓
        List<String> result = solver.wordSubsets(
            new String[]{"wrr", "warrior"},
            new String[]{"wr"}
        );
        assertTrue(result.contains("wrr"));
        assertTrue(result.contains("warrior"));
        assertEquals(2, result.size());
    }

    @Test
    public void testMaxFrequency() {
        // words1 = ["abc","abcc"], words2 = ["aa","a"]
        // combined need 'a':2 (max of 2 and 1)
        // "abc" has 'a':1 < 2. ✗
        // "abcc" has 'a':1 < 2. ✗
        List<String> result = solver.wordSubsets(
            new String[]{"abc", "abcc"},
            new String[]{"aa", "a"}
        );
        assertTrue(result.isEmpty());
    }

    @Test
    public void testLeetCodeExample3() {
        List<String> result = solver.wordSubsets(
            new String[]{"acaac", "cccbb", "aacbb", "caacc", "bcbbb"},
            new String[]{"c", "cc", "b"}
        );
        assertEquals(List.of("cccbb"), result);
    }

    @Test
    public void testRequirementsAreCombinedByMaximumFrequency() {
        List<String> result = solver.wordSubsets(
            new String[]{"abca", "aabb", "abcc", "baaa"},
            new String[]{"ab", "aa"}
        );
        assertEquals(List.of("abca", "aabb", "baaa"), result);
    }

    @Test
    public void testOrderDoesNotMatter() {
        List<String> result = solver.wordSubsets(
            new String[]{"zyxwv", "vwxyz", "xyzzv"},
            new String[]{"xv", "z"}
        );
        assertEquals(List.of("zyxwv", "vwxyz", "xyzzv"), result);
    }

    @Test
    public void testSingleCharacterRequirement() {
        List<String> result = solver.wordSubsets(
            new String[]{"a", "b", "aa", "ba"},
            new String[]{"a"}
        );
        assertEquals(List.of("a", "aa", "ba"), result);
    }

    @Test
    public void testAllRequiredCharacters() {
        List<String> result = solver.wordSubsets(
            new String[]{"abcdefghij", "abcdefghi", "jihgfedcba"},
            new String[]{"abc", "defgh", "ij"}
        );
        assertEquals(List.of("abcdefghij", "jihgfedcba"), result);
    }

    @Test
    public void testCandidateWithInsufficientMultiplicityIsRejected() {
        List<String> result = solver.wordSubsets(
            new String[]{"aab", "ab", "baa", "abc"},
            new String[]{"aa", "ab"}
        );
        assertEquals(List.of("aab", "baa"), result);
    }

    @Test
    public void testCandidateMayContainExtraCharacters() {
        List<String> result = solver.wordSubsets(
            new String[]{"zzabzz", "ab", "zazb", "ac"},
            new String[]{"ab"}
        );
        assertEquals(List.of("zzabzz", "ab", "zazb"), result);
    }

    @Test
    public void testNoCandidateCanSatisfyRepeatedRequirement() {
        List<String> result = solver.wordSubsets(
            new String[]{"abc", "bc", "accc"},
            new String[]{"aa", "bbb"}
        );
        assertTrue(result.isEmpty());
    }

    @Test
    public void testDuplicateRequirementsDoNotOvercountAcrossWords() {
        List<String> result = solver.wordSubsets(
            new String[]{"abc", "aabc", "abbc", "ab"},
            new String[]{"a", "b", "ab"}
        );
        assertEquals(List.of("abc", "aabc", "abbc", "ab"), result);
    }

    @Test
    public void testContractScaleInput() {
        String[] words1 = new String[10_000];
        for (int i = 0; i < words1.length; i++) {
            if (i == words1.length - 1) {
                words1[i] = "abcdefghij";
            } else {
                // Encode each index with letters outside the required set so words1 remains unique.
                int value = i;
                char[] filler = new char[4];
                for (int position = 0; position < filler.length; position++) {
                    filler[position] = (char) ('k' + value % 16);
                    value /= 16;
                }
                words1[i] = new String(filler);
            }
        }
        List<String> result = solver.wordSubsets(words1, new String[]{"abc", "defgh", "ij"});
        assertEquals(List.of("abcdefghij"), result);
    }
}
