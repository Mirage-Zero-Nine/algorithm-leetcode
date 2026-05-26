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
        // words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["l","e"]
        // universal strings: "apple" (has 'l','e'), "leetcode" (has 'l','e')
        List<String> result = solver.wordSubsets(
            new String[]{"amazon", "apple", "facebook", "google", "leetcode"},
            new String[]{"l", "e"}
        );
        assertTrue(result.contains("apple"));
        assertTrue(result.contains("leetcode"));
        assertEquals(3, result.size());
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
}
