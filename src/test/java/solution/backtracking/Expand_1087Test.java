package solution.backtracking;

import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Expand_1087Test {
    private final Expand_1087 solution = new Expand_1087();

    @Test
    void testBasic() {
        String[] result = solution.expand("{a,b}c{d,e}f");
        assertEquals(4, result.length);
    }

    @Test
    void testSingleOption() {
        String[] result = solution.expand("abcd");
        assertEquals(1, result.length);
    }

    @Test
    void testMultipleOptions() {
        String[] result = solution.expand("{a,b}{c,d}");
        assertEquals(4, result.length);
    }

    @Test
    void testThreeOptions() {
        String[] result = solution.expand("{a,b,c}");
        assertEquals(3, result.length);
    }

    @Test
    void testComplex() {
        String[] result = solution.expand("a{b,c}{d,e}f");
        assertEquals(4, result.length);
    }

    @Test
    void testLexicographicOrder() {
        String[] result = solution.expand("{b,a}c");
        assertEquals("ac", result[0]);
        assertEquals("bc", result[1]);
    }

    @Test
    void testSingleChar() {
        String[] result = solution.expand("a");
        assertEquals(1, result.length);
        assertEquals("a", result[0]);
    }

    @Test
    void testAllBraces() {
        String[] result = solution.expand("{a,b}{c,d}{e,f}");
        assertEquals(8, result.length);
    }

    @Test
    void testSingleOptionInBraces() {
        String[] result = solution.expand("{a}b{c}");
        assertEquals(1, result.length);
        assertEquals("abc", result[0]);
    }

    @Test
    void testResultContent() {
        String[] result = solution.expand("{a,b}c{d,e}f");
        Set<String> set = new HashSet<>(java.util.Arrays.asList(result));
        assertTrue(set.contains("acdf"));
        assertTrue(set.contains("acef"));
        assertTrue(set.contains("bcdf"));
        assertTrue(set.contains("bcef"));
    }

    @Test
    void testGiantExpansion() {
        // {a,b,c}{d,e,f}{g,h,i} = 27 combinations
        String[] result = solution.expand("{a,b,c}{d,e,f}{g,h,i}");
        assertEquals(27, result.length);
    }
}
