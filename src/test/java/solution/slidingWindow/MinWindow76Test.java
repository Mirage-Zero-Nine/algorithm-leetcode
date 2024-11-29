package solution.slidingWindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import solution.slidingwindow.MinWindow_76;

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
}