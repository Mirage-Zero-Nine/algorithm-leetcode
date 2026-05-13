package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ReorganizeString_767Test {

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

    @Test
    public void testEmptyString() {
        assertEquals("", test.reorganizeString(""));
    }

    @Test
    public void testTwoChars() {
        String result = test.reorganizeString("ab");
        assertNotEquals("", result);
        assertEquals(2, result.length());
    }

    @Test
    public void testAllSameChar() {
        assertEquals("", test.reorganizeString("aaaa"));
    }

    @Test
    public void testValidRearrangement() {
        String result = test.reorganizeString("aaabbc");
        assertNotEquals("", result);
        // Verify no two adjacent chars are the same
        for (int i = 1; i < result.length(); i++) {
            assertNotEquals(result.charAt(i), result.charAt(i - 1));
        }
    }

    @Test
    public void testOddLengthMaxFreq() {
        // "vvvlo" -> max freq 3, length 5, (5+1)/2=3, so possible
        String result = test.reorganizeString("vvvlo");
        assertNotEquals("", result);
        for (int i = 1; i < result.length(); i++) {
            assertNotEquals(result.charAt(i), result.charAt(i - 1));
        }
    }

    @Test
    public void testImpossibleCase() {
        // "aaaabc" -> max freq 4, length 6, (6+1)/2=3, 4>3 so impossible
        assertEquals("", test.reorganizeString("aaaabc"));
    }

    @Test
    public void testGiantCase() {
        // Build a string with many chars that is reorganizable
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 250; i++) sb.append('a');
        for (int i = 0; i < 250; i++) sb.append('b');
        String result = test.reorganizeString(sb.toString());
        assertNotEquals("", result);
        assertEquals(500, result.length());
        for (int i = 1; i < result.length(); i++) {
            assertNotEquals(result.charAt(i), result.charAt(i - 1));
        }
    }
}
