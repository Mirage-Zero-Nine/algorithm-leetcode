package solution.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Sqrt69Test {

    private final Sqrt_69 test = new Sqrt_69();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.sqrt(4));
        assertEquals(2, test.sqrt(8));
        assertEquals(3, test.sqrt(9));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.sqrt(0));
        assertEquals(1, test.sqrt(1));
    }

    @Test
    public void testLargeCase() {
        assertEquals(100, test.sqrt(10000));
        assertEquals(46340, test.sqrt(2147395600));
    }
}
