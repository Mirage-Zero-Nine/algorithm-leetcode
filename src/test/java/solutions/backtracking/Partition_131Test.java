package solutions.backtracking;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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
        List<List<String>> result = solution.partition("aabb");
        assertTrue(result.size() >= 4);
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
        List<List<String>> result = solution.partition("abcabc");
        assertTrue(result.size() >= 1);
    }

    @Test
    void testGiantInput() {
        // "aaaaaaaaaa" (10 a's) - many palindrome partitions
        List<List<String>> result = solution.partition("aaaaaaaaaa");
        assertTrue(result.size() > 50);
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
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            sb.append((char) ('a' + rng.nextInt(4))); // small alphabet for more palindromes
        }
        String input = sb.toString();
        List<List<String>> result = solution.partition(input);

        // Property 1: every part is a palindrome
        // Property 2: concatenation gives original
        assertAllPartitionsValid(input, result);

        // Property 3: results are unique
        Set<List<String>> unique = new HashSet<>(result);
        assertEquals(result.size(), unique.size(), "All partitions should be unique for: " + input);

        // Should produce at least one partition (all single chars)
        assertTrue(result.size() >= 1);
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
}
