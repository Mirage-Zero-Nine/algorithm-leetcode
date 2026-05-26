package solutions.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2024/11/29 13:16
 * Created with IntelliJ IDEA
 */

public class MinWindow_727Test {

    private final MinWindow_727 test = new MinWindow_727();

    @Test
    public void test() {
        assertEquals("ccqouq", test.minWindow("cnhczmccqouqadqtmjjzl", "ccouq"));
        assertEquals("czm", test.minWindow("cnhczmccqouqadqtmjjzl", "cm"));
        assertEquals("bcde", test.minWindow("abcdebdde", "bde"));
    }

    @Test
    public void testEmpty() {
        assertEquals("", test.minWindow("", ""));
    }

    @Test
    public void testNoPossibleWindow() {
        assertEquals("", test.minWindow("abc", "d"));
    }

    @Test
    public void testTargetLongerThanSource() {
        assertEquals("", test.minWindow("ab", "abc"));
    }

    @Test
    public void testNullInputs() {
        assertEquals("", test.minWindow(null, "abc"));
        assertEquals("", test.minWindow("abc", null));
    }

    @Test
    public void testExactMatch() {
        assertEquals("abc", test.minWindow("abc", "abc"));
    }

    @Test
    public void testSingleCharacterTarget() {
        assertEquals("a", test.minWindow("zzzaazz", "a"));
    }

    @Test
    public void testRepeatedCharactersInTarget() {
        assertEquals("aab", test.minWindow("aaab", "aab"));
    }

    @Test
    public void testLeftMostWindowOnTie() {
        assertEquals("aby", test.minWindow("abxaby", "aby"));
    }

    @Test
    public void testGiantCase() {
        String s = "a".repeat(2000) + "b" + "c".repeat(2000) + "d";
        assertEquals("b" + "c".repeat(2000) + "d", test.minWindow(s, "bcd"));
    }
}
