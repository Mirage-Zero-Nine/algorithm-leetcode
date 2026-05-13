package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
