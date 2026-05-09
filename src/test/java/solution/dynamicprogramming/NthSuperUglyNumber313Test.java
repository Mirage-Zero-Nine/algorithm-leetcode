package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NthSuperUglyNumber313Test {

    private final NthSuperUglyNumber_313 test = new NthSuperUglyNumber_313();

    @Test
    public void testHappyCases() {
        assertEquals(32, test.nthSuperUglyNumber(12, new int[]{2, 7, 13, 19}));
        assertEquals(1, test.nthSuperUglyNumber(1, new int[]{2, 3, 5}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.nthSuperUglyNumber(1, new int[]{2}));
        assertEquals(2, test.nthSuperUglyNumber(2, new int[]{2}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(13, test.nthSuperUglyNumber(6, new int[]{2, 11, 13}));
    }
}
