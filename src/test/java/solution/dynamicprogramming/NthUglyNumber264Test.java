package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NthUglyNumber264Test {

    private final NthUglyNumber_264 test = new NthUglyNumber_264();

    @Test
    public void testHappyCases() {
        assertEquals(12, test.nthUglyNumber(10));
        assertEquals(1, test.nthUglyNumber(1));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(2, test.nthUglyNumber(2));
        assertEquals(3, test.nthUglyNumber(3));
    }

    @Test
    public void testLargeCase() {
        assertEquals(36, test.nthUglyNumber(20));
    }
}
