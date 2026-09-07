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

    @org.junit.jupiter.params.ParameterizedTest(name = "independent oracle seed={0}")
    @org.junit.jupiter.params.provider.ValueSource(ints = {7, 19, 43, 71, 101, 211, 509, 997, 2027, 4093, 8191, 16381})
    public void testSeededCasesAgainstIndependentOracle(int seed) {
        java.util.Random random = new java.util.Random(seed);
        for (int sample = 0; sample < 40; sample++) {

            StringBuilder value = new StringBuilder();
            for (int i = 0, n = 4 * (1 + random.nextInt(5)); i < n; i++) value.append("QWER".charAt(random.nextInt(4)));
            String s = value.toString();
            int expected = s.length();
            for (int left = 0; left <= s.length(); left++)
                for (int right = left; right <= s.length(); right++) {
                    int[] outside = new int[4];
                    for (int i = 0; i < s.length(); i++)
                        if (i < left || i >= right) outside["QWER".indexOf(s.charAt(i))]++;
                    boolean valid = true;
                    for (int count : outside) valid &= count <= s.length() / 4;
                    if (valid) expected = Math.min(expected, right - left);
                }
            org.junit.jupiter.api.Assertions.assertEquals(expected, new BalancedString_1234().balancedString(s), s);
        }
    }
}
