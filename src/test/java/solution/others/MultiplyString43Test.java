package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MultiplyString43Test {

    private final MultiplyString_43 test = new MultiplyString_43();

    @Test
    public void testHappyCases() {
        assertEquals("6", test.multiply("2", "3"));
        assertEquals("56088", test.multiply("123", "456"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals("0", test.multiply("0", "123"));
        assertEquals("0", test.multiply("0", "0"));
    }

    @Test
    public void testLargeCase() {
        assertEquals("121932631112635269", test.multiply("123456789", "987654321"));
    }
}
