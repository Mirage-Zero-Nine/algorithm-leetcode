package solution.slidingwindow;

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
}
