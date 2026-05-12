package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsHappy202Test {

    private final IsHappy_202 test = new IsHappy_202();

    @Test
    public void testHappyCases() {
        assertTrue(test.isHappy(19));
        assertTrue(test.isHappy(1));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.isHappy(2));
        assertFalse(test.isHappy(4));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.isHappy(100));
        assertFalse(test.isHappy(99));
    }
}
