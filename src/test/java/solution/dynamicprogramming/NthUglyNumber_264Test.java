package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NthUglyNumber_264Test {

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

    @Test
    public void testFourth() {
        assertEquals(4, test.nthUglyNumber(4));
    }

    @Test
    public void testFifth() {
        assertEquals(5, test.nthUglyNumber(5));
    }

    @Test
    public void testSixth() {
        assertEquals(6, test.nthUglyNumber(6));
    }

    @Test
    public void testSeventh() {
        assertEquals(8, test.nthUglyNumber(7));
    }

    @Test
    public void testFifteenth() {
        assertEquals(24, test.nthUglyNumber(15));
    }

    @Test
    public void testHundredth() {
        assertEquals(1536, test.nthUglyNumber(100));
    }

    @Test
    public void testGiantCase() {
        assertEquals(2123366400, test.nthUglyNumber(1690));
    }
}
