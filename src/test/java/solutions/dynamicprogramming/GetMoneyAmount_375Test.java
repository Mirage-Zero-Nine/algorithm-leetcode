package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class GetMoneyAmount_375Test {

    private final GetMoneyAmount_375 test = new GetMoneyAmount_375();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.bottomUp(4));
        assertEquals(16, test.bottomUp(10));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(0, test.bottomUp(1));
        assertEquals(1, test.bottomUp(2));
    }

    @Test
    public void testLargeCase() {
        assertEquals(49, test.bottomUp(20));
    }

    @Test
    public void testN3() {
        assertEquals(2, test.bottomUp(3));
    }

    @Test
    public void testN5() {
        assertEquals(6, test.bottomUp(5));
    }

    @Test
    public void testTopDownN10() {
        assertEquals(16, test.getMoneyAmount(10));
    }

    @Test
    public void testTopDownN1() {
        assertEquals(0, test.getMoneyAmount(1));
    }

    @Test
    public void testTopDownN2() {
        assertEquals(1, test.getMoneyAmount(2));
    }

    @Test
    public void testGiantCase() {
        int result = test.bottomUp(50);
        assertTrue(result > 0);
    }

    @Test
    public void testConsistency() {
        // Both implementations should return same result
        for (int n = 1; n <= 15; n++) {
            assertEquals(test.getMoneyAmount(n), test.bottomUp(n));
        }
    }
}
