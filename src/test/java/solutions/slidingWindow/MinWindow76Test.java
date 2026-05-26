package solutions.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2024/11/29 12:36
 * Created with IntelliJ IDEA
 */

public class MinWindow76Test {
    private final MinWindow_76 test = new MinWindow_76();


    @Test
    public void test() {
        assertEquals("BANC", test.minWindow("ADOBECODEBANC", "ABC"));
    }

    @Test
    public void test2() {
        assertEquals("ABC", test.minWindow("ABC", "ABC"));
    }

    @Test
    public void testEmpty() {
        assertEquals("", test.minWindow("", ""));
    }

    @Test
    public void testInvalid() {
        assertEquals("", test.minWindow("a", "aa"));
    }

    @Test
    public void testSingleCharMatch() {
        assertEquals("a", test.minWindow("a", "a"));
    }

    @Test
    public void testTargetAtEnd() {
        assertEquals("BA", test.minWindow("XXXXBA", "AB"));
    }

    @Test
    public void testDuplicateCharsInT() {
        assertEquals("BANC", test.minWindow("ADOBECODEBANC", "ABC"));
    }

    @Test
    public void testNoMatch() {
        assertEquals("", test.minWindow("abcdef", "z"));
    }

    @Test
    public void testTLongerThanS() {
        assertEquals("", test.minWindow("ab", "abc"));
    }

    @Test
    public void testGiantCase() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) sb.append('x');
        sb.append("ABC");
        for (int i = 0; i < 10000; i++) sb.append('x');
        String result = test.minWindow(sb.toString(), "ABC");
        assertEquals("ABC", result);
    }
}
