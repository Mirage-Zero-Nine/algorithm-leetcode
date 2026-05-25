package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CountAndSay_38Test {

    private final CountAndSay_38 test = new CountAndSay_38();

    @Test
    public void testHappyCases() {
        assertEquals("1211", test.countAndSay(4));
        assertEquals("111221", test.countAndSay(5));
    }

    @Test
    public void testEdgeCases() {
        assertEquals("1", test.countAndSay(1));
        assertEquals("11", test.countAndSay(2));
    }

    @Test
    public void testLargeCase() {
        assertEquals("312211", test.countAndSay(6));
    }

    @Test
    public void testThird() {
        assertEquals("21", test.countAndSay(3));
    }

    @Test
    public void testSeventh() {
        assertEquals("13112221", test.countAndSay(7));
    }

    @Test
    public void testEighth() {
        assertEquals("1113213211", test.countAndSay(8));
    }

    @Test
    public void testNinth() {
        assertEquals("31131211131221", test.countAndSay(9));
    }

    @Test
    public void testTenth() {
        assertEquals("13211311123113112211", test.countAndSay(10));
    }

    @Test
    public void testNegativeInput() {
        // n < 1 falls through corner case: n==2 check is false, n==1 check is false, returns "1"
        assertEquals("1", test.countAndSay(0));
    }

    @Test
    public void testGiantCase() {
        // n=20 should produce a long string; just verify it starts correctly and has reasonable length
        String result = test.countAndSay(20);
        assertEquals('1', result.charAt(0));
        assertEquals(true, result.length() > 100);
    }

    @Test
    public void testNegativeNumber() {
        // Negative input should be handled gracefully (falls to else branch returning "1")
        assertEquals("1", test.countAndSay(-1));
    }

    @Test
    public void testN10KnownReference() {
        assertEquals("13211311123113112211", test.countAndSay(10));
    }

    @Test
    public void testN15LengthGrowth() {
        // n=15 result should be significantly longer than n=10
        String r10 = test.countAndSay(10);
        String r15 = test.countAndSay(15);
        assertTrue(r15.length() > r10.length(), "n=15 should be longer than n=10");
        assertTrue(r15.length() > 50, "n=15 should have length > 50");
    }

    @Test
    public void testPropertyOnlyDigits123() {
        // For the standard count-and-say sequence, terms only contain digits 1, 2, 3
        for (int n = 1; n <= 20; n++) {
            String result = test.countAndSay(n);
            assertTrue(result.matches("[123]+"),
                    "n=" + n + " contains digits other than 1,2,3: " + result);
        }
    }

    @Test
    public void testPropertyDescribesPrevious() {
        // countAndSay(n) should describe countAndSay(n-1) via run-length encoding
        for (int n = 2; n <= 15; n++) {
            String prev = test.countAndSay(n - 1);
            String curr = test.countAndSay(n);
            // Manually encode prev and compare to curr
            StringBuilder expected = new StringBuilder();
            int count = 1;
            for (int i = 1; i < prev.length(); i++) {
                if (prev.charAt(i) == prev.charAt(i - 1)) {
                    count++;
                } else {
                    expected.append(count).append(prev.charAt(i - 1));
                    count = 1;
                }
            }
            expected.append(count).append(prev.charAt(prev.length() - 1));
            assertEquals(expected.toString(), curr,
                    "countAndSay(" + n + ") should describe countAndSay(" + (n - 1) + ")");
        }
    }

    @Test
    public void testLengthMonotonicallyIncreasing() {
        // Length of count-and-say sequence is non-decreasing (actually strictly increasing for n>=2)
        int prevLen = test.countAndSay(1).length();
        for (int n = 2; n <= 20; n++) {
            int currLen = test.countAndSay(n).length();
            assertTrue(currLen >= prevLen,
                    "Length should be non-decreasing at n=" + n);
            prevLen = currLen;
        }
    }

    @Test
    public void testN30LargeInput() {
        // n=30 is the upper bound per problem constraints; verify it produces a non-empty result
        String result = test.countAndSay(30);
        assertTrue(result.length() > 1000, "n=30 should produce a very long string");
        assertTrue(result.matches("[123]+"), "n=30 should only contain digits 1,2,3");
    }
}
