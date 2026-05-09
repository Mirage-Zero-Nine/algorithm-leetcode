package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DistinctSubseqII940Test {

    private final DistinctSubseqII_940 test = new DistinctSubseqII_940();

    @Test
    public void testHappyCases() {
        assertEquals(7, test.distinctSubseqII("abc"));
        assertEquals(6, test.distinctSubseqII("aba"));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.distinctSubseqII("a"));
        assertEquals(3, test.distinctSubseqII("aaa"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(15, test.distinctSubseqII("abcd"));
    }
}
