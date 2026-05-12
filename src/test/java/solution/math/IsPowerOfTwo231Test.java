package solution.math;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsPowerOfTwo231Test {

    private final IsPowerOfTwo_231 test = new IsPowerOfTwo_231();

    @Test
    public void testHappyCases() {
        assertTrue(test.isPowerOfTwo(1));
        assertTrue(test.isPowerOfTwo(16));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.isPowerOfTwo(0));
        assertFalse(test.isPowerOfTwo(-1));
        assertFalse(test.isPowerOfTwo(3));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.isPowerOfTwo(1073741824));
        assertFalse(test.isPowerOfTwo(1000000000));
    }
}
