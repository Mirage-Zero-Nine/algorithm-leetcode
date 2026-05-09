package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumDecodings91Test {

    private final NumDecodings_91 test = new NumDecodings_91();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.numDecodings("12"));
        assertEquals(3, test.numDecodings("226"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.numDecodings("0"));
        assertEquals(0, test.numDecodings(""));
    }

    @Test
    public void testLargeCase() {
        assertEquals(2, test.numDecodings("1021"));
    }
}
