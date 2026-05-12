package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class ReorganizeString767Test {

    private final ReorganizeString_767 test = new ReorganizeString_767();

    @Test
    public void testHappyCases() {
        String result = test.reorganizeString("aab");
        assertNotEquals("", result);
        assertEquals(3, result.length());
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals("", test.reorganizeString("aaab"));
        assertEquals("a", test.reorganizeString("a"));
    }

    @Test
    public void testLargeCase() {
        String result = test.reorganizeString("aabbcc");
        assertNotEquals("", result);
        assertEquals(6, result.length());
    }
}
