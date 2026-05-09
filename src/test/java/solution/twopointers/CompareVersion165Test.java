package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CompareVersion165Test {

    private final CompareVersion_165 test = new CompareVersion_165();

    @Test
    public void testHappyCases() {
        assertEquals(-1, test.compareVersion("0.1", "1.1"));
        assertEquals(1, test.compareVersion("1.0.1", "1"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.compareVersion("1.01", "1.001"));
        assertEquals(0, test.compareVersion("1.0", "1.0.0"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(-1, test.compareVersion("7.5.2.4", "7.5.3"));
        assertEquals(1, test.compareVersion("1.2.3", "1.2.2"));
    }
}
