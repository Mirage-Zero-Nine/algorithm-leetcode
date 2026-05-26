package solutions.backtracking;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GeneratePalindromes_267Test {
    private final GeneratePalindromes_267 solution = new GeneratePalindromes_267();

    @Test
    void testBasic() {
        List<String> result = solution.generatePalindromes("aabb");
        assertEquals(2, result.size());
    }

    @Test
    void testSingleChar() {
        List<String> result = solution.generatePalindromes("a");
        assertEquals(1, result.size());
    }

    @Test
    void testNoSolution() {
        List<String> result = solution.generatePalindromes("abc");
        assertEquals(0, result.size());
    }

    @Test
    void testThreeSame() {
        List<String> result = solution.generatePalindromes("aaa");
        assertEquals(1, result.size());
    }

    @Test
    void testMultipleSolutions() {
        List<String> result = solution.generatePalindromes("aabbcc");
        assertTrue(result.size() >= 6);
    }

    @Test
    void testTwoCharsNoPalindrome() {
        List<String> result = solution.generatePalindromes("ab");
        assertEquals(0, result.size());
    }

    @Test
    void testTwoCharsSame() {
        List<String> result = solution.generatePalindromes("aa");
        assertEquals(1, result.size());
        assertEquals("aa", result.get(0));
    }

    @Test
    void testOddLengthPalindrome() {
        List<String> result = solution.generatePalindromes("aab");
        assertEquals(1, result.size());
        assertEquals("aba", result.get(0));
    }

    @Test
    void testAllResultsArePalindromes() {
        List<String> result = solution.generatePalindromes("aabbcc");
        for (String s : result) {
            String rev = new StringBuilder(s).reverse().toString();
            assertEquals(s, rev);
        }
    }

    @Test
    void testNoDuplicatesInResult() {
        List<String> result = solution.generatePalindromes("aabbcc");
        Set<String> unique = new HashSet<>(result);
        assertEquals(result.size(), unique.size());
    }

    @Test
    void testGiantInput() {
        // "aabbccdd" -> 4! / 1 = 24 palindromes
        List<String> result = solution.generatePalindromes("aabbccdd");
        assertEquals(24, result.size());
    }
}
