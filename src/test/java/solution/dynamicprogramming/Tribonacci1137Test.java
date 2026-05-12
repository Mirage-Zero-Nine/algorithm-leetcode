package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Tribonacci1137Test {

    private final Tribonacci_1137 test = new Tribonacci_1137();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.tribonacci(4));
        assertEquals(7, test.tribonacci(5));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(0, test.tribonacci(0));
        assertEquals(1, test.tribonacci(1));
        assertEquals(1, test.tribonacci(2));
    }

    @Test
    public void testLargeCase() {
        assertEquals(149, test.tribonacci(10));
    }
}
