package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaximumSwap670Test {

    private final MaximumSwap_670 test = new MaximumSwap_670();

    @Test
    public void testHappyCases() {
        assertEquals(7236, test.maximumSwap(2736));
        assertEquals(9973, test.maximumSwap(9973));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(9, test.maximumSwap(9));
        assertEquals(10, test.maximumSwap(10));
    }

    @Test
    public void testLargeCase() {
        assertEquals(98368, test.maximumSwap(38968));
    }
}
