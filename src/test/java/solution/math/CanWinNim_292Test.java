package solution.math;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CanWinNim_292Test {

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

    @Test
    public void testMultiplesOfFourLose() {
        assertFalse(test.canWinNim(12));
        assertFalse(test.canWinNim(16));
        assertFalse(test.canWinNim(20));
    }

    @Test
    public void testNonMultiplesOfFourWin() {
        assertTrue(test.canWinNim(5));
        assertTrue(test.canWinNim(6));
        assertTrue(test.canWinNim(7));
    }

    @Test
    public void testSmallValues() {
        assertTrue(test.canWinNim(9));
        assertTrue(test.canWinNim(10));
        assertTrue(test.canWinNim(11));
    }

    @Test
    public void testLargeMultipleOfFour() {
        assertFalse(test.canWinNim(100));
        assertFalse(test.canWinNim(400));
    }

    @Test
    public void testLargeNonMultipleOfFour() {
        assertTrue(test.canWinNim(101));
        assertTrue(test.canWinNim(999999999));
    }

    @Test
    public void testBoundaryValues() {
        assertFalse(test.canWinNim(4));
        assertTrue(test.canWinNim(3));
    }

    @Test
    public void testGiantValue() {
        assertTrue(test.canWinNim(Integer.MAX_VALUE - 2)); // 2147483645 % 4 = 1 => true
        assertTrue(test.canWinNim(Integer.MAX_VALUE)); // 2147483647 % 4 = 3 => true
        assertFalse(test.canWinNim(Integer.MAX_VALUE - 3)); // 2147483644 % 4 = 0 => false
    }
}
