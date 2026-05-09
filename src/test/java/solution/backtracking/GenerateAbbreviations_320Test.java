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

class GenerateAbbreviations_320Test {
    private final GenerateAbbreviations_320 solution = new GenerateAbbreviations_320();

    @Test
    void testWord() {
        List<String> result = solution.generateAbbreviations("word");
        assertEquals(16, result.size());
    }

    @Test
    void testSingleChar() {
        List<String> result = solution.generateAbbreviations("a");
        assertEquals(2, result.size());
    }

    @Test
    void testTwoChars() {
        List<String> result = solution.generateAbbreviations("ab");
        assertEquals(4, result.size());
    }

    @Test
    void testThreeChars() {
        List<String> result = solution.generateAbbreviations("abc");
        assertEquals(8, result.size());
    }

    @Test
    void testEmpty() {
        List<String> result = solution.generateAbbreviations("");
        assertTrue(result.size() <= 1);
    }
}
