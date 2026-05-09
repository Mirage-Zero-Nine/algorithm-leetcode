package solution.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumSub1513Test {

    private final NumSub_1513 test = new NumSub_1513();

    @Test
    public void testHappyCases() {
        assertEquals(9, test.numSub("0110111"));
        assertEquals(6, test.numSub("111"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.numSub("0"));
        assertEquals(1, test.numSub("1"));
        assertEquals(0, test.numSub("000"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(55, test.numSub("1111111111"));
    }
}
