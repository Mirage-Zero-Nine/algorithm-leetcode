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
}
