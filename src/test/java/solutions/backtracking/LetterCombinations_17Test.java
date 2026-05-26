package solutions.backtracking;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LetterCombinations_17Test {
    private final LetterCombinations_17 solution = new LetterCombinations_17();

    @Test
    void testBasic() {
        List<String> result = solution.letterCombinations("23");
        assertEquals(9, result.size());
    }

    @Test
    void testSingleDigit() {
        List<String> result = solution.letterCombinations("2");
        assertEquals(3, result.size());
    }

    @Test
    void testEmpty() {
        List<String> result = solution.letterCombinations("");
        assertEquals(0, result.size());
    }

    @Test
    void testThreeDigits() {
        List<String> result = solution.letterCombinations("234");
        assertEquals(27, result.size());
    }

    @Test
    void testWithSeven() {
        List<String> result = solution.letterCombinations("7");
        assertEquals(4, result.size());
    }

    @Test
    void testWithNine() {
        List<String> result = solution.letterCombinations("9");
        assertEquals(4, result.size());
    }

    @Test
    void testContainsExpectedCombination() {
        List<String> result = solution.letterCombinations("23");
        assertTrue(result.contains("ad"));
        assertTrue(result.contains("cf"));
    }

    @Test
    void testDigitWithZero() {
        // '0' has no letters, should return empty
        List<String> result = solution.letterCombinations("20");
        assertEquals(0, result.size());
    }

    @Test
    void testDigitWithOne() {
        // '1' has no letters, should return empty
        List<String> result = solution.letterCombinations("12");
        assertEquals(0, result.size());
    }

    @Test
    void testFourDigits() {
        // 2(3) * 3(3) * 4(3) * 5(3) = 81
        List<String> result = solution.letterCombinations("2345");
        assertEquals(81, result.size());
    }

    @Test
    void testSevenAndNine() {
        // 7(4) * 9(4) = 16
        List<String> result = solution.letterCombinations("79");
        assertEquals(16, result.size());
    }

    @Test
    void testGiantInput() {
        // "2222222" -> 3^7 = 2187
        List<String> result = solution.letterCombinations("2222222");
        assertEquals(2187, result.size());
    }

    @Test
    void testSingleDigitTwoExactContent() {
        Set<String> expected = Set.of("a", "b", "c");
        Set<String> result = new HashSet<>(solution.letterCombinations("2"));
        assertEquals(expected, result);
    }

    @Test
    void testSevenExactContent() {
        Set<String> expected = Set.of("p", "q", "r", "s");
        Set<String> result = new HashSet<>(solution.letterCombinations("7"));
        assertEquals(expected, result);
    }

    @Test
    void testNineExactContent() {
        Set<String> expected = Set.of("w", "x", "y", "z");
        Set<String> result = new HashSet<>(solution.letterCombinations("9"));
        assertEquals(expected, result);
    }

    @Test
    void testTwoThreeExactContent() {
        Set<String> expected = Set.of("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf");
        Set<String> result = new HashSet<>(solution.letterCombinations("23"));
        assertEquals(expected, result);
    }

    @Test
    void testAllDigitsSize() {
        // "23456789" -> 3*3*3*3*3*4*3*4 = 11664
        List<String> result = solution.letterCombinations("23456789");
        assertEquals(11664, result.size());
    }

    @Test
    void testAllResultsHaveCorrectLength() {
        String digits = "2379";
        List<String> result = solution.letterCombinations(digits);
        for (String s : result) {
            assertEquals(digits.length(), s.length());
        }
    }

    @Test
    void testAllResultsUnique() {
        List<String> result = solution.letterCombinations("23456789");
        assertEquals(result.size(), new HashSet<>(result).size());
    }

    @Test
    void testProductPropertyForMultipleInputs() {
        Map<Character, Integer> letterCount = Map.of(
                '2', 3, '3', 3, '4', 3, '5', 3, '6', 3, '7', 4, '8', 3, '9', 4);
        String[] inputs = {"23", "79", "234", "789", "2379"};
        for (String digits : inputs) {
            int expectedSize = 1;
            for (char c : digits.toCharArray()) {
                expectedSize *= letterCount.get(c);
            }
            assertEquals(expectedSize, solution.letterCombinations(digits).size(),
                    "Failed for digits: " + digits);
        }
    }

    @Test
    void testNullInput() {
        // Verify behavior with null - implementation may throw NPE
        try {
            solution.letterCombinations(null);
        } catch (NullPointerException e) {
            // expected
        }
    }
}
