package solution.math;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CheckPerfectNumber507Test {

    private final CheckPerfectNumber_507 test = new CheckPerfectNumber_507();

    @Test
    public void testHappyCases() {
        assertTrue(test.checkPerfectNumber(28));
        assertTrue(test.checkPerfectNumber(6));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.checkPerfectNumber(1));
        assertFalse(test.checkPerfectNumber(2));
        assertFalse(test.checkPerfectNumber(12));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.checkPerfectNumber(496));
        assertFalse(test.checkPerfectNumber(1000));
    }
}
