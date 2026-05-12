package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ZigZagConversion6Test {

    private final ZigZagConversion_6 test = new ZigZagConversion_6();

    @Test
    public void testHappyCases() {
        assertEquals("PAHNAPLSIIGYIR", test.convert("PAYPALISHIRING", 3));
        assertEquals("PINALSIGYAHRPI", test.convert("PAYPALISHIRING", 4));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals("A", test.convert("A", 1));
        assertEquals("AB", test.convert("AB", 1));
    }

    @Test
    public void testLargeCase() {
        assertEquals("ABCDEFGHIJK", test.convert("ABCDEFGHIJK", 1));
    }
}
