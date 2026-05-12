package solution.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class KthFactor1492Test {

    private final KthFactor_1492 test = new KthFactor_1492();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.kthFactor(12, 3));
        assertEquals(7, test.kthFactor(7, 2));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(-1, test.kthFactor(4, 4));
        assertEquals(1, test.kthFactor(1, 1));
    }

    @Test
    public void testLargeCase() {
        assertEquals(-1, test.kthFactor(1000, 20));
        assertEquals(5, test.kthFactor(1000, 4));
    }
}
