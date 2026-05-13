package solution.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AlienOrder_269Test {

    private final AlienOrder_269 test = new AlienOrder_269();

    @Test
    public void testHappyCases() {
        String result = test.alienOrder(new String[]{"wrt", "wrf", "er", "ett", "rftt"});
        assertTrue(result.contains("w") && result.contains("e") && result.contains("r"));
        assertEquals("zx", test.alienOrder(new String[]{"z", "x"}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals("", test.alienOrder(new String[]{"z", "x", "z"}));
        assertEquals("z", test.alienOrder(new String[]{"z"}));
        assertEquals("", test.alienOrder(new String[]{"abc", "ab"}));
    }

    @Test
    public void testLargeCase() {
        String result = test.alienOrder(new String[]{"za", "zb", "ca", "cb"});
        assertTrue(result.indexOf('z') < result.indexOf('c') || result.indexOf('a') < result.indexOf('b'));
    }

    @Test
    public void testEmptyInput() {
        assertEquals("", test.alienOrder(new String[]{}));
    }

    @Test
    public void testSingleCharWords() {
        String result = test.alienOrder(new String[]{"a", "b", "c"});
        assertEquals(3, result.length());
        assertTrue(result.indexOf('a') < result.indexOf('b'));
        assertTrue(result.indexOf('b') < result.indexOf('c'));
    }

    @Test
    public void testTwoIdenticalWords() {
        String result = test.alienOrder(new String[]{"z", "z"});
        assertEquals("z", result);
    }

    @Test
    public void testCycleDetection() {
        assertEquals("", test.alienOrder(new String[]{"a", "b", "a"}));
    }

    @Test
    public void testPrefixValid() {
        // "ab" before "abc" is valid (prefix comes first)
        String result = test.alienOrder(new String[]{"ab", "abc"});
        assertTrue(result.contains("a") && result.contains("b") && result.contains("c"));
    }

    @Test
    public void testPrefixInvalid() {
        // "abc" before "ab" is invalid (longer prefix before shorter)
        assertEquals("", test.alienOrder(new String[]{"abc", "ab"}));
    }

    @Test
    public void testMultipleValidOrders() {
        // Only constraint: t < f from "wrt" vs "wrf"
        String result = test.alienOrder(new String[]{"wrt", "wrf"});
        assertTrue(result.indexOf('t') < result.indexOf('f'));
    }

    @Test
    public void testGiantCase() {
        // Build a long chain: a < b < c < ... < z via 26 single-char words
        String[] words = new String[26];
        for (int i = 0; i < 26; i++) {
            words[i] = String.valueOf((char) ('a' + i));
        }
        String result = test.alienOrder(words);
        assertEquals(26, result.length());
        for (int i = 0; i < 25; i++) {
            assertTrue(result.indexOf((char) ('a' + i)) < result.indexOf((char) ('a' + i + 1)));
        }
    }

    @Test
    public void testDisconnectedChars() {
        // "ac", "bc" => a < b, c has no ordering constraint relative to a/b
        String result = test.alienOrder(new String[]{"ac", "bc"});
        assertTrue(result.indexOf('a') < result.indexOf('b'));
        assertEquals(3, result.length());
    }
}
