package solution.slidingWindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import solution.slidingwindow.MinWindow_727;

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
}