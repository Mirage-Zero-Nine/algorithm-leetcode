package solutions.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CountHomogenous_1759Test {

    private final CountHomogenous_1759 test = new CountHomogenous_1759();

    @Test
    public void testHappyCase1() {
        assertEquals(13, test.countHomogenous("abbcccaa"));
    }

    @Test
    public void testHappyCase2() {
        assertEquals(2, test.countHomogenous("xy"));
    }

    @Test
    public void testHappyCase3() {
        assertEquals(15, test.countHomogenous("zzzzz"));
    }

    @Test
    public void testHappyCase4() {
        assertEquals(1, test.countHomogenous("a"));
    }

    @Test
    public void testHappyCase5() {
        // abc: a, b, c. Total 3.
        assertEquals(3, test.countHomogenous("abc"));
    }

    @Test
    public void testNegativeCase() {
        assertEquals(0, test.countHomogenous(""));
    }

    @Test
    public void testEdgeCaseAlternating() {
        // ababababa: 9 characters, each group size 1. Total 9.
        assertEquals(9, test.countHomogenous("ababababa"));
    }

    @Test
    public void testEdgeCaseAllSameLong() {
        assertEquals(10, test.countHomogenous("aaaa")); // 1+2+3+4 = 10
    }

    @Test
    public void testEdgeCaseTwoGroups() {
        // aaabb: (1+2+3) + (1+2) = 6+3 = 9.
        assertEquals(9, test.countHomogenous("aaabb"));
    }

    @Test
    public void testGiantCase() {
        int n = 100000;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append('a');
        }
        // sum 1 to n = n*(n+1)/2. 100000 * 100001 / 2 = 5000050000.
        // Mod 10^9 + 7: 5000050000 % 1000000007 = 5000050000 - 4*1000000007 - 1000000007? No.
        // 5000050000 / 1000000007 = 4.99999...
        // 5000050000 - 4 * 1000000007 = 5000050000 - 4000000028 = 1000049972.
        // 1000049972 % 1000000007 = 49965.
        // Actually the implementation adds count one by one with mod.
        long sum = 0;
        int mod = 1000000007;
        for (int i = 1; i <= n; i++) {
            sum = (sum + i) % mod;
        }
        assertEquals((int) sum, test.countHomogenous(sb.toString()));
    }

    @org.junit.jupiter.params.ParameterizedTest(name = "independent oracle seed={0}")
    @org.junit.jupiter.params.provider.ValueSource(ints = {7, 19, 43, 71, 101, 211, 509, 997, 2027, 4093, 8191, 16381})
    public void testSeededCasesAgainstIndependentOracle(int seed) {
        java.util.Random random = new java.util.Random(seed);
        for (int sample = 0; sample < 40; sample++) {

            String s = random.ints(40, 'a', 'e').collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
            int expected = 0;
            for (int left = 0; left < s.length(); left++)
                for (int right = left; right < s.length(); right++) {
                    boolean same = true;
                    for (int i = left; i <= right; i++) same &= s.charAt(i) == s.charAt(left);
                    if (same) expected++;
                }
            org.junit.jupiter.api.Assertions.assertEquals(expected, new CountHomogenous_1759().countHomogenous(s), s);
        }
    }
}
