package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumDecodings639Test {

    private final NumDecodings_639 test = new NumDecodings_639();

    @Test
    public void testHappyCases() {
        assertEquals(9, test.numDecodings("*"));
        assertEquals(18, test.numDecodings("1*"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.numDecodings("0"));
        assertEquals(1, test.numDecodings("1"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(96, test.numDecodings("**"));
    }
}
