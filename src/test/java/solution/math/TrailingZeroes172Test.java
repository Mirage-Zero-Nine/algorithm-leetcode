package solution.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TrailingZeroes172Test {

    private final TrailingZeroes_172 test = new TrailingZeroes_172();

    @Test
    public void testHappyCases() {
        assertEquals(1, test.trailingZeroes(5));
        assertEquals(2, test.trailingZeroes(10));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.trailingZeroes(0));
        assertEquals(0, test.trailingZeroes(4));
    }

    @Test
    public void testLargeCase() {
        assertEquals(24, test.trailingZeroes(100));
        assertEquals(249, test.trailingZeroes(1000));
    }
}
