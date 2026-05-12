package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CountAndSay38Test {

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
}
