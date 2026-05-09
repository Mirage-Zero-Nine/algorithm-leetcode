package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NthUglyNumber1201Test {

    private final NthUglyNumber_1201 test = new NthUglyNumber_1201();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.nthUglyNumber(3, 2, 3, 5));
        assertEquals(6, test.nthUglyNumber(4, 2, 3, 4));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(2, test.nthUglyNumber(1, 2, 3, 5));
        assertEquals(10, test.nthUglyNumber(5, 2, 11, 13));
    }

    @Test
    public void testLargeCase() {
        assertEquals(10, test.nthUglyNumber(8, 2, 3, 5));
    }
}
