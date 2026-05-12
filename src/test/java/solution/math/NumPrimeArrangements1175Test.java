package solution.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumPrimeArrangements1175Test {

    private final NumPrimeArrangements_1175 test = new NumPrimeArrangements_1175();

    @Test
    public void testHappyCases() {
        assertEquals(12, test.numPrimeArrangements(5));
        assertEquals(4, test.numPrimeArrangements(4));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.numPrimeArrangements(1));
        assertEquals(1, test.numPrimeArrangements(2));
    }

    @Test
    public void testLargeCase() {
        assertEquals(682289015, test.numPrimeArrangements(100));
    }
}
