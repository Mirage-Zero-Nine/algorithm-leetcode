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

class FindStrobogrammatic_247Test {
    private final FindStrobogrammatic_247 solution = new FindStrobogrammatic_247();

    @Test
    void testOne() {
        List<String> result = solution.findStrobogrammatic(1);
        assertEquals(3, result.size());
    }

    @Test
    void testTwo() {
        List<String> result = solution.findStrobogrammatic(2);
        assertEquals(4, result.size());
    }

    @Test
    void testThree() {
        List<String> result = solution.findStrobogrammatic(3);
        assertTrue(result.size() >= 12);
    }

    @Test
    void testFour() {
        List<String> result = solution.findStrobogrammatic(4);
        assertTrue(result.size() >= 16);
    }

    @Test
    void testZero() {
        List<String> result = solution.findStrobogrammatic(0);
        assertTrue(result.size() <= 1);
    }

    @Test
    void testOneContainsExpectedValues() {
        List<String> result = solution.findStrobogrammatic(1);
        assertTrue(result.contains("0"));
        assertTrue(result.contains("1"));
        assertTrue(result.contains("8"));
    }

    @Test
    void testTwoContainsExpectedValues() {
        List<String> result = solution.findStrobogrammatic(2);
        assertTrue(result.contains("11"));
        assertTrue(result.contains("69"));
        assertTrue(result.contains("88"));
        assertTrue(result.contains("96"));
    }

    @Test
    void testTwoNoLeadingZero() {
        List<String> result = solution.findStrobogrammatic(2);
        for (String s : result) {
            assertFalse(s.startsWith("0"), "Should not have leading zero: " + s);
        }
    }

    @Test
    void testThreeNoLeadingZero() {
        List<String> result = solution.findStrobogrammatic(3);
        for (String s : result) {
            assertFalse(s.startsWith("0"), "Should not have leading zero: " + s);
        }
    }

    @Test
    void testFiveLength() {
        List<String> result = solution.findStrobogrammatic(5);
        for (String s : result) {
            assertEquals(5, s.length());
            assertFalse(s.startsWith("0"));
        }
    }

    @Test
    void testAllResultsCorrectLength() {
        for (int n = 1; n <= 4; n++) {
            List<String> result = solution.findStrobogrammatic(n);
            for (String s : result) {
                assertEquals(n, s.length());
            }
        }
    }

    @Test
    void testNoDuplicates() {
        List<String> result = solution.findStrobogrammatic(4);
        Set<String> set = new HashSet<>(result);
        assertEquals(result.size(), set.size());
    }

    @Test
    void testGiantCase() {
        List<String> result = solution.findStrobogrammatic(8);
        assertNotNull(result);
        assertTrue(result.size() > 100);
        for (String s : result) {
            assertEquals(8, s.length());
            assertFalse(s.startsWith("0"));
        }
    }

    @Test
    void testZeroReturnsEmptyString() {
        // n=0 base case returns list with one empty string
        List<String> result = solution.findStrobogrammatic(0);
        assertEquals(1, result.size());
        assertEquals("", result.get(0));
    }
}
