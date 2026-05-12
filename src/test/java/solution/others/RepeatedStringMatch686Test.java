package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RepeatedStringMatch686Test {

    private final RepeatedStringMatch_686 test = new RepeatedStringMatch_686();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.repeatedStringMatch("abcd", "cdabcdab"));
        assertEquals(2, test.repeatedStringMatch("a", "aa"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(-1, test.repeatedStringMatch("abc", "wxyz"));
        assertEquals(1, test.repeatedStringMatch("abc", "abc"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(3, test.repeatedStringMatch("ab", "ababab"));
    }
}
