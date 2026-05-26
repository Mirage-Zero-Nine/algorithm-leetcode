package solutions.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BalancedString1234Test {

    private final BalancedString_1234 test = new BalancedString_1234();

    @Test
    public void testHappyCases() {
        assertEquals(1, test.balancedString("QQWE"));
        assertEquals(2, test.balancedString("QQQW"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.balancedString("QWER"));
        assertEquals(3, test.balancedString("QQQQ"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(3, test.balancedString("WQWRQQQW"));
    }

    @Test
    public void testAlreadyBalanced() {
        assertEquals(0, test.balancedString("QWER"));
        assertEquals(0, test.balancedString("QWERQWER"));
    }

    @Test
    public void testAllSameChar() {
        assertEquals(3, test.balancedString("QQQQ"));
        assertEquals(6, test.balancedString("WWWWWWWW"));
        assertEquals(6, test.balancedString("EEEEEEEE"));
        assertEquals(6, test.balancedString("RRRRRRRR"));
    }

    @Test
    public void testTwoExcessChars() {
        assertEquals(2, test.balancedString("QQWW"));
        assertEquals(2, test.balancedString("QQEE"));
    }

    @Test
    public void testMinLengthString() {
        assertEquals(0, test.balancedString("QWER"));
    }

    @Test
    public void testLongerBalancedString() {
        assertEquals(0, test.balancedString("QWERQWERQWERQWER"));
    }

    @Test
    public void testExcessAtEnd() {
        assertEquals(1, test.balancedString("QWEQ"));
    }

    @Test
    public void testGiantCase() {
        // Build a string of length 10000 with 2500 each of Q, W, E, R (already balanced)
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2500; i++) {
            sb.append("QWER");
        }
        assertEquals(0, test.balancedString(sb.toString()));
    }
}
