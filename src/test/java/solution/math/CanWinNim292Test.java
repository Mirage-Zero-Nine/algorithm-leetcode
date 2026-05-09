package solution.math;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CanWinNim292Test {

    private final CanWinNim_292 test = new CanWinNim_292();

    @Test
    public void testHappyCases() {
        assertTrue(test.canWinNim(1));
        assertTrue(test.canWinNim(2));
        assertTrue(test.canWinNim(3));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.canWinNim(4));
        assertFalse(test.canWinNim(8));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.canWinNim(1000000001));
        assertFalse(test.canWinNim(1000000000));
    }
}
