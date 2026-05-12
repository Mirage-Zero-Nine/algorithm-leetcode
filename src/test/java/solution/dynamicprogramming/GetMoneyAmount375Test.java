package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GetMoneyAmount375Test {

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
}
