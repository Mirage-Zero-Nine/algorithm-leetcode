package solution.backtracking;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GenerateParenthesis_22Test {
    private final GenerateParenthesis_22 solution = new GenerateParenthesis_22();

    @Test
    void testOne() {
        List<String> result = solution.generateParenthesis(1);
        assertEquals(1, result.size());
        assertTrue(result.contains("()"));
    }

    @Test
    void testThree() {
        List<String> result = solution.generateParenthesis(3);
        assertEquals(5, result.size());
    }

    @Test
    void testTwo() {
        List<String> result = solution.generateParenthesis(2);
        assertEquals(2, result.size());
    }

    @Test
    void testFour() {
        List<String> result = solution.generateParenthesis(4);
        assertEquals(14, result.size());
    }

    @Test
    void testZero() {
        List<String> result = solution.generateParenthesis(0);
        assertTrue(result.size() <= 1);
    }

    @Test
    void testZeroReturnsEmpty() {
        List<String> result = solution.generateParenthesis(0);
        assertEquals(0, result.size());
    }

    @Test
    void testFive() {
        // Catalan number C(5) = 42
        List<String> result = solution.generateParenthesis(5);
        assertEquals(42, result.size());
    }

    @Test
    void testSix() {
        // Catalan number C(6) = 132
        List<String> result = solution.generateParenthesis(6);
        assertEquals(132, result.size());
    }

    @Test
    void testAllResultsCorrectLength() {
        List<String> result = solution.generateParenthesis(4);
        for (String s : result) {
            assertEquals(8, s.length());
        }
    }

    @Test
    void testAllResultsBalanced() {
        List<String> result = solution.generateParenthesis(3);
        for (String s : result) {
            int count = 0;
            for (char c : s.toCharArray()) {
                if (c == '(') count++;
                else count--;
                assertTrue(count >= 0, "Unbalanced: " + s);
            }
            assertEquals(0, count);
        }
    }

    @Test
    void testNoDuplicates() {
        List<String> result = solution.generateParenthesis(4);
        Set<String> set = new HashSet<>(result);
        assertEquals(result.size(), set.size());
    }

    @Test
    void testThreeContainsExpected() {
        List<String> result = solution.generateParenthesis(3);
        assertTrue(result.contains("((()))"));
        assertTrue(result.contains("(()())"));
        assertTrue(result.contains("(())()"));
        assertTrue(result.contains("()(())"));
        assertTrue(result.contains("()()()"));
    }

    @Test
    void testGiantCase() {
        // Catalan number C(10) = 16796
        List<String> result = solution.generateParenthesis(10);
        assertEquals(16796, result.size());
    }

    @Test
    void testOneExactContent() {
        List<String> result = solution.generateParenthesis(1);
        assertEquals(Set.of("()"), new HashSet<>(result));
    }

    @Test
    void testTwoExactContent() {
        List<String> result = solution.generateParenthesis(2);
        assertEquals(Set.of("(())", "()()"), new HashSet<>(result));
    }

    @Test
    void testEightCatalanCount() {
        // Catalan number C(8) = 1430
        List<String> result = solution.generateParenthesis(8);
        assertEquals(1430, result.size());
    }

    @Test
    void testEightAllValidAndCorrectLength() {
        List<String> result = solution.generateParenthesis(8);
        for (String s : result) {
            assertEquals(16, s.length());
            int count = 0;
            for (char c : s.toCharArray()) {
                count += (c == '(') ? 1 : -1;
                assertTrue(count >= 0, "Unbalanced: " + s);
            }
            assertEquals(0, count, "Not closed: " + s);
        }
    }

    @Test
    void testEightNoDuplicates() {
        List<String> result = solution.generateParenthesis(8);
        assertEquals(result.size(), new HashSet<>(result).size());
    }

    @Test
    void testAllResultsOnlyContainParenChars() {
        List<String> result = solution.generateParenthesis(5);
        for (String s : result) {
            assertTrue(s.matches("[()]+"), "Unexpected chars in: " + s);
        }
    }

    @Test
    void testCatalanSequenceProperty() {
        // Verify Catalan number sequence: 1, 1, 2, 5, 14, 42, 132
        int[] expected = {0, 1, 2, 5, 14, 42, 132};
        for (int n = 1; n <= 6; n++) {
            assertEquals(expected[n], solution.generateParenthesis(n).size(),
                    "Catalan mismatch for n=" + n);
        }
    }

    @Test
    void testEveryResultStartsWithOpenAndEndsWithClose() {
        for (int n = 1; n <= 5; n++) {
            for (String s : solution.generateParenthesis(n)) {
                assertEquals('(', s.charAt(0), "Must start with '(': " + s);
                assertEquals(')', s.charAt(s.length() - 1), "Must end with ')': " + s);
            }
        }
    }
}
