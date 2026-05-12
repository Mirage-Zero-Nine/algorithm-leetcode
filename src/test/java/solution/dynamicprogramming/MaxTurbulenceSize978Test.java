package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxTurbulenceSize978Test {

    private final MaxTurbulenceSize_978 test = new MaxTurbulenceSize_978();

    @Test
    public void testHappyCases() {
        assertEquals(5, test.maxTurbulenceSize(new int[]{9, 4, 2, 10, 7, 8, 8, 1, 9}));
        assertEquals(2, test.maxTurbulenceSize(new int[]{4, 8, 12, 16}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(1, test.maxTurbulenceSize(new int[]{100}));
        assertEquals(1, test.maxTurbulenceSize(new int[]{1, 1, 1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(5, test.maxTurbulenceSize(new int[]{0, 1, 1, 0, 1, 0, 1, 1, 0, 0}));
    }
}
