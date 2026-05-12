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
}
