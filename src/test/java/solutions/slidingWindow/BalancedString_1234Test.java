package solutions.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BalancedString_1234Test {

    private final BalancedString_1234 test = new BalancedString_1234();

    @Test
    public void testHappyCase1() {
        assertEquals(0, test.balancedString("QWER"));
    }

    @Test
    public void testHappyCase2() {
        assertEquals(1, test.balancedString("QQWE"));
    }

    @Test
    public void testHappyCase3() {
        assertEquals(2, test.balancedString("QQQW"));
    }

    @Test
    public void testHappyCase4() {
        assertEquals(3, test.balancedString("QQQQ"));
    }

    @Test
    public void testHappyCase5() {
        // WWEQERQW: W=3, E=2, Q=2, R=1. n=8, ave=2. W=3 > 2.
        // Replace 1 W to make it balanced.
        // Actually, replacing "WW" at index 0 and 1 with "ER" would make it: EREQERQW
        // E: 3, R: 2, Q: 2, W: 1. Still not balanced.
        // The problem is: "minimum length of the substring that can be replaced with ANY string".
        // WWEQERQW. We have W:3, E:2, Q:2, R:1. We need one more R and one less W.
        // If we replace one 'W' with 'R', we get 2 of each.
        // Substring "W" at index 0, 1, or 7. Length 1.
        assertEquals(1, test.balancedString("WWEQERQW"));
    }

    @Test
    public void testNegativeCase() {
        // String is already balanced
        assertEquals(0, test.balancedString("QWERRWEQ"));
    }

    @Test
    public void testEdgeCaseEmpty() {
        assertEquals(0, test.balancedString(""));
    }

    @Test
    public void testEdgeCaseAllSame() {
        assertEquals(3, test.balancedString("RRRR"));
    }

    @Test
    public void testEdgeCaseTwoEach() {
        assertEquals(0, test.balancedString("QQWWRREE"));
    }

    @Test
    public void testGiantCase() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2500; i++) sb.append("Q");
        for (int i = 0; i < 2500; i++) sb.append("W");
        for (int i = 0; i < 2500; i++) sb.append("E");
        for (int i = 0; i < 2500; i++) sb.append("R");
        assertEquals(0, test.balancedString(sb.toString()));

        // Add 10 more 'Q's at the beginning. n=10010. ave = 2502.
        // Q: 2510, W: 2500, E: 2500, R: 2500.
        // Need to replace 8 'Q's.
        // Wait, n must be a multiple of 4 for it to be truly balanced.
        // Let's use n=10000.
        sb = new StringBuilder();
        for (int i = 0; i < 4000; i++) sb.append("Q");
        for (int i = 0; i < 2000; i++) sb.append("W");
        for (int i = 0; i < 2000; i++) sb.append("E");
        for (int i = 0; i < 2000; i++) sb.append("R");
        // n=10000, ave=2500. Q has 4000. Need to replace 1500 'Q's.
        assertEquals(1500, test.balancedString(sb.toString()));
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
