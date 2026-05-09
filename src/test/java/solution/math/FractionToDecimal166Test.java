package solution.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FractionToDecimal166Test {

    private final FractionToDecimal_166 test = new FractionToDecimal_166();

    @Test
    public void testHappyCases() {
        assertEquals("0.5", test.fractionToDecimal(1, 2));
        assertEquals("2", test.fractionToDecimal(2, 1));
        assertEquals("0.(6)", test.fractionToDecimal(2, 3));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals("0", test.fractionToDecimal(0, 1));
        assertEquals("-0.5", test.fractionToDecimal(1, -2));
    }

    @Test
    public void testLargeCase() {
        assertEquals("0.(142857)", test.fractionToDecimal(1, 7));
        assertEquals("0.1(6)", test.fractionToDecimal(1, 6));
    }
}
