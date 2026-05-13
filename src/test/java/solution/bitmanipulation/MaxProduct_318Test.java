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

    @Test public void testSingleWord() {
        String[] words = {"hello"};
        assertEquals(0, solver.maxProduct(words));
    }

    @Test public void testLongerWords() {
        String[] words = {"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
        assertEquals(16, solver.maxProduct(words));
    }

    @Test public void testExample2() {
        String[] words = {"a", "ab", "abc", "d", "cd", "bcd", "abcd"};
        assertEquals(4, solver.maxProduct(words));  // "ab" and "cd" = 2*2
    }

    @Test public void testNoDisjoint() {
        String[] words = {"abc", "bca", "cab"};
        assertEquals(0, solver.maxProduct(words));
    }

    @Test public void testGiantCase() {
        String[] words = new String[100];
        for (int i = 0; i < 50; i++) words[i] = "a".repeat(i + 1);
        for (int i = 50; i < 100; i++) words[i] = "b".repeat(i - 49);
        // max product = "a"*50 * "b"*50 = 50*50 = 2500
        assertEquals(2500, solver.maxProduct(words));
    }

    @Test public void testTwoLongDisjoint() {
        String[] words = {"abcdefghij", "klmnopqrst"};
        assertEquals(100, solver.maxProduct(words));
    }
}
