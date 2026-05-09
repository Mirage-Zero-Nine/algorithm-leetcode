package solution.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinOperations1551Test {

    private final MinOperations_1551 test = new MinOperations_1551();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.minOperations(3));
        assertEquals(4, test.minOperations(4));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.minOperations(1));
        assertEquals(1, test.minOperations(2));
    }

    @Test
    public void testLargeCase() {
        assertEquals(25, test.minOperations(10));
        assertEquals(30, test.minOperations(11));
    }
}
