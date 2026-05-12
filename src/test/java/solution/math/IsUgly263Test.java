package solution.math;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsUgly263Test {

    private final IsUgly_263 test = new IsUgly_263();

    @Test
    public void testHappyCases() {
        assertTrue(test.isUgly(6));
        assertTrue(test.isUgly(8));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.isUgly(14));
        assertFalse(test.isUgly(0));
        assertFalse(test.isUgly(-1));
        assertTrue(test.isUgly(1));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.isUgly(2160));
        assertFalse(test.isUgly(2161));
    }
}
