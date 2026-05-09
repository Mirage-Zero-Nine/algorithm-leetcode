package solution.bitmanipulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaxProduct_318Test {
    private final MaxProduct_318 solver = new MaxProduct_318();

    @Test public void testBasic() {
        String[] words = {"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
        assertEquals(16, solver.maxProduct(words));  // "abcw" * "xtfn" = 4 * 4 = 16
    }

    @Test public void testAllShare() {
        String[] words = {"a", "ab", "abc"};
        assertEquals(0, solver.maxProduct(words));
    }

    @Test public void testTwoDistinct() {
        String[] words = {"a", "aa", "aaa", "aaaa"};
        assertEquals(0, solver.maxProduct(words));
    }

    @Test public void testSimple() {
        String[] words = {"ab", "cd"};
        assertEquals(4, solver.maxProduct(words));
    }

    @Test public void testEmpty() {
        String[] words = {};
        assertEquals(0, solver.maxProduct(words));
    }
}
