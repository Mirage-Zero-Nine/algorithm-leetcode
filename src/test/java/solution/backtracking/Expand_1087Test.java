package solution.backtracking;

import org.junit.jupiter.api.Test;
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
}
